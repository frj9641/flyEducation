package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.entity.order.OrderResultBean;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.domain.mapper.OrderMapper;
import com.frj.flyeducation.query.OrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderQueryImpl implements OrderQuery {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MarketMapper marketMapper;
    /**
     * 通过教务unionid查询返回未完成的订单
     * @param registrarUnionid
     * @return
     */
    @Override
    public CommonResult queryOrdersByRegistrarUnionid(String registrarUnionid) {
        List<Order> orders = orderMapper.selectOrdersByRegistrarUnionid(registrarUnionid);
        List<OrderResultBean> orderResultBeans=new ArrayList<>();
        for(int x=0;x<orders.size();x++){
            Order order = orders.get(x);
            Market market = marketMapper.selectMarketByMarketId(order.getMarketId());
            OrderResultBean orderResultBean = OrderResultBean.builder().orderId(order.getOrderId())
                    .studentName(order.getStudentName()).phone(order.getPhone())
                    .marketName(market.getMarketName()).teacherName(market.getTeacherName())
                    .isAdded(order.getIsAdded()).isFinished(order.getIsFinished())
                    .isIntent(order.getIsIntent()).isSuccess(order.getIsSuccess()).build();
            orderResultBeans.add(orderResultBean);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("orders",orderResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过教务unionid查询返回有意向的订单
     * @param registrarUnionid
     * @return
     */
    @Override
    public CommonResult queryIntentOrdersByRegistrarUnionid(String registrarUnionid) {
        List<Order> orders = orderMapper.selectIntentOrdersByRegistrarUnionid(registrarUnionid);
        List<OrderResultBean> orderResultBeans=new ArrayList<>();
        for(int x=0;x<orders.size();x++){
            Order order = orders.get(x);
            Market market = marketMapper.selectMarketByMarketId(order.getMarketId());
            OrderResultBean orderResultBean = OrderResultBean.builder().orderId(order.getOrderId())
                    .studentName(order.getStudentName()).phone(order.getPhone())
                    .marketName(market.getMarketName()).teacherName(market.getTeacherName())
                    .isAdded(order.getIsAdded()).isFinished(order.getIsFinished())
                    .isIntent(order.getIsIntent()).isSuccess(order.getIsSuccess()).date(order.getDate()).build();
            orderResultBeans.add(orderResultBean);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("orders",orderResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
