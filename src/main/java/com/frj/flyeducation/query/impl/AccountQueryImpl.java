package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.domain.mapper.OrderMapper;
import com.frj.flyeducation.query.AccountQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountQueryImpl implements AccountQuery {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MarketMapper marketMapper;
    /**
     * 通过订单id查询学生姓名与报名课程
     * @param orderId
     * @return
     */
    @Override
    public CommonResult queryOrderByOrderId(Integer orderId) {
        Order order = orderMapper.selectOrderByOrderId(orderId);
        Market market = marketMapper.selectMarketByMarketId(order.getMarketId());
        Map<String,Object> map=new HashMap<>();
        map.put("studentName",order.getStudentName());
        map.put("marketName",market.getMarketName());
        map.put("price",market.getPrice());
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
