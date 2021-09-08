package com.frj.flyeducation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.common.TemplateMessageResult;
import com.frj.flyeducation.domain.entity.customer.Customer;
import com.frj.flyeducation.domain.entity.customer.CustomerRegistryBean;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.mapper.CustomerMapper;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.domain.mapper.OrderMapper;
import com.frj.flyeducation.domain.mapper.SubscriberMapper;
import com.frj.flyeducation.service.CustomerService;
import com.frj.flyeducation.util.AccessTokenUtil;
import com.frj.flyeducation.util.TemplateMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private SubscriberMapper subscriberMapper;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 注册客户信息
     * @param customerRegistryBean
     */
    @Transactional
    @Override
    public void createCustomer(CustomerRegistryBean customerRegistryBean) {
        Customer customer = Customer.builder().customerUnionid(customerRegistryBean.getCustomerUnionid())
                .studentName(customerRegistryBean.getStudentName()).parentName(customerRegistryBean.getParentName())
                .school(customerRegistryBean.getSchool()).grade(customerRegistryBean.getGrade())
                .phone(customerRegistryBean.getPhone()).build();
        customerMapper.insertCustomer(customer);
        Market market = marketMapper.selectMarketByMarketId(customerRegistryBean.getMarketId());
        Order order = Order.builder().customerUnionid(customerRegistryBean.getCustomerUnionid())
                .studentName(customerRegistryBean.getStudentName()).parentName(customerRegistryBean.getParentName())
                .school(customerRegistryBean.getSchool()).grade(customerRegistryBean.getGrade())
                .phone(customerRegistryBean.getPhone()).registrarUnionid(market.getRegistrarUnionid())
                .marketId(customerRegistryBean.getMarketId()).build();
        Integer i = orderMapper.selectIsAddedByUnionids(customerRegistryBean.getCustomerUnionid(), market.getRegistrarUnionid());
        if (i == null || i == 0) {
            order.setIsAdded(0);
        } else {
            order.setIsAdded(1);
        }
        order.setIsFinished(0);
        order.setIsIntent(0);
        order.setIsSuccess(0);
        orderMapper.insertOrder(order);
        adviseReminder(order,market);
    }

    /**
     * 咨询提醒
     * @param order
     */
    private void adviseReminder(Order order,Market market) {
        String accessToken = AccessTokenUtil.getAccessToken();
        String openid = subscriberMapper.selectOfficalOpenidByUnionid(market.getRegistrarUnionid());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openid);
        jsonObject.put("template_id", "xxx");
        JSONObject miniprogram = new JSONObject();
        miniprogram.put("appid","xxx");
        miniprogram.put("path","pages/employee/registrar/before/order/order");
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        JSONObject keyword1 = new JSONObject();
        JSONObject keyword2 = new JSONObject();
        JSONObject remark = new JSONObject();
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("remark", remark);
        first.put("value", "您有新的报名咨询");
        keyword1.put("value", order.getStudentName());
        keyword2.put("value", order.getPhone());
        remark.put("value", "课程名称：" + market.getMarketName() + "\n任课老师：" + market.getTeacherName()
                + "\n家长姓名：" + order.getParentName() + "\n学校：" + order.getSchool() + "\n年级：" + order.getGrade());
        jsonObject.put("data", data);
        jsonObject.put("miniprogram",miniprogram);
        String result = jsonObject.toJSONString();
        restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);
    }
}
