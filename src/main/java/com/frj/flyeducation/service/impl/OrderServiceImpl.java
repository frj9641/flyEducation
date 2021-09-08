package com.frj.flyeducation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.common.TemplateMessageResult;
import com.frj.flyeducation.domain.entity.customer.Customer;
import com.frj.flyeducation.domain.entity.customer.CustomerQueryBean;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.entity.order.OrderDateChangeBean;
import com.frj.flyeducation.domain.entity.order.OrderRegistryBean;
import com.frj.flyeducation.domain.mapper.*;
import com.frj.flyeducation.service.OrderService;
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
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SubscriberMapper subscriberMapper;


    /**
     * 注册订单
     * @param orderRegistryBean
     */
    @Override
    public CommonResult createOrder(OrderRegistryBean orderRegistryBean) {
        Customer customer = customerMapper.selectCustomerByCustomerId(orderRegistryBean.getCustomerId());
        CustomerQueryBean customerQueryBean = CustomerQueryBean.builder().customerUnionid(customer.getCustomerUnionid()).marketId(orderRegistryBean.getMarketId()).build();
        Integer orderId = orderMapper.selectOrderIdByCustomerUnionid(customerQueryBean);
        Map<String,Object> map=new HashMap<>();
        if(orderId==null){
            Market market = marketMapper.selectMarketByMarketId(orderRegistryBean.getMarketId());
            Order order = Order.builder().customerUnionid(customer.getCustomerUnionid())
                    .studentName(customer.getStudentName()).parentName(customer.getParentName())
                    .school(customer.getSchool()).grade(customer.getGrade())
                    .phone(customer.getPhone()).registrarUnionid(market.getRegistrarUnionid())
                    .marketId(orderRegistryBean.getMarketId()).build();
            Integer i = orderMapper.selectIsAddedByUnionids(customer.getCustomerUnionid(), market.getRegistrarUnionid());
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
            map.put("msg","提交成功，稍后会有工作人员联系您！");
            CommonResult commonResult = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
            return commonResult;
        }else {
            map.put("msg","您已咨询过该门课程，请耐心等待工作人员联系您！");
            CommonResult commonResult = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
            return commonResult;
        }
    }

    /**
     * 通过订单主键改变添加好友状态
     * @param orderId
     */
    @Override
    public void changeIsAddedByOrderId(Integer orderId) {
        orderMapper.updateIsAddedByOrderId(orderId);
    }

    /**
     * 通过订单主键修改会话完成状态
     * @param orderId
     */
    @Override
    public void changeIsFinishedByOrderId(Integer orderId) {
        orderMapper.updateIsFinishedByOrderId(orderId);
    }

    /**
     * 通过订单主键修改会话完成状态与意向状态
     * @param orderId
     */
    @Transactional
    @Override
    public void changeIsFinishedAndIsIntentByOrderId(Integer orderId) {
        orderMapper.updateIsFinishedByOrderId(orderId);
        orderMapper.updateIsIntentByOrderId1(orderId);
    }

    /**
     * 修改意愿时间
     * @param orderDateChangeBean
     */
    @Override
    public void changeDateByOrderId(OrderDateChangeBean orderDateChangeBean) {
        orderMapper.updateDateByOrderId(orderDateChangeBean);
    }

    /**
     * 通过订单主键修改意向状态
     * @param orderId
     */
    @Override
    public void changeIsIntentByOrderId(Integer orderId) {
        orderMapper.updateIsIntentByOrderId0(orderId);
    }

    /**
     * 注册自定义订单
     * @param order
     */
    @Override
    public void createCustomizeOrder(Order order) {
        order.setIsAdded(1);
        order.setIsFinished(1);
        order.setIsIntent(1);
        order.setIsSuccess(0);
        orderMapper.insertOrder(order);
    }

    /**
     * 咨询提醒
     * @param order
     */
    private void adviseReminder(Order order,Market market) {
        String accessToken = AccessTokenUtil.getAccessToken();
        String openid = subscriberMapper.selectOfficalOpenidByUnionid(order.getRegistrarUnionid());
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
