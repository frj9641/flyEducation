package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.customer.Customer;
import com.frj.flyeducation.domain.entity.market.MarketResultBean;
import com.frj.flyeducation.domain.entity.order.OrderCustomizeRegistryBean;
import com.frj.flyeducation.domain.mapper.CustomerMapper;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.query.CustomerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerQueryImpl implements CustomerQuery {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private MarketMapper marketMapper;
    /**
     * 通过用户unionid查询用户的主键
     * @param unionid
     * @return
     */
    @Override
    public CommonResult queryCustomerIdByUnionid(String unionid) {
        Integer i = customerMapper.selectCustomerIdByUnionid(unionid);
        Map<String,Object> map=new HashMap<>();
        map.put("id",i);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过关键字模糊查询客户表
     * @param keyword
     * @return
     */
    @Override
    public CommonResult queryCustomersByKeyword(String keyword) {
        List<Customer> customers = customerMapper.selectCustomersByKeyword(keyword);
        Map<String,Object> map=new HashMap<>();
        map.put("customers",customers);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过教务unionid和客户主键查询返回教务的营销课程与客户信息
     * @param orderCustomizeRegistryBean
     * @return
     */
    @Override
    public CommonResult queryCustomerAndMarketsByOrderBean(OrderCustomizeRegistryBean orderCustomizeRegistryBean) {
        Customer customer = customerMapper.selectCustomerByCustomerId(orderCustomizeRegistryBean.getCustomerId());
        List<MarketResultBean> markets = marketMapper.selectMarketsByRegistrarUnionid(orderCustomizeRegistryBean.getRegistrarUnionid());
        Map<String,Object> map=new HashMap<>();
        map.put("customer",customer);
        map.put("markets",markets);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
