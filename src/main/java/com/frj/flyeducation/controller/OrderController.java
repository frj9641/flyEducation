package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.entity.order.OrderDateChangeBean;
import com.frj.flyeducation.domain.entity.order.OrderRegistryBean;
import com.frj.flyeducation.query.OrderQuery;
import com.frj.flyeducation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderQuery orderQuery;
    /**
     * 注册订单
     * @param orderRegistryBean
     */
    @PostMapping
    public CommonResult createOrder(OrderRegistryBean orderRegistryBean){
        return orderService.createOrder(orderRegistryBean);
    }

    /**
     * 通过教务unionid查询返回未完成的订单
     * @param registrarUnionid
     * @return
     */
    @GetMapping
    public CommonResult queryOrdersByRegistrarUnionid(@RequestParam("registrarUnionid") String registrarUnionid){
        return orderQuery.queryOrdersByRegistrarUnionid(registrarUnionid);
    }

    /**
     * 通过订单主键修改添加好友状态
     * @param orderId
     */
    @PutMapping(value="/add")
    public void changeIsAddedByOrderId(Integer orderId){
        orderService.changeIsAddedByOrderId(orderId);
    }

    /**
     * 通过订单主键修改会话完成状态
     * @param orderId
     */
    @PutMapping(value = "/finish")
    public void changeIsFinishedByOrderId(Integer orderId){
        orderService.changeIsFinishedByOrderId(orderId);
    }

    /**
     * 通过订单主键修改会话完成状态与意向状态
     * @param orderId
     */
    @PutMapping(value = "/finishAndIntent")
    public void changeIsFinishedAndIsIntentByOrderId(Integer orderId){
        orderService.changeIsFinishedAndIsIntentByOrderId(orderId);
    }

    /**
     * 通过教务unionid查询返回有意向的订单
     * @return
     */
    @GetMapping(value = "/schedule")
    public CommonResult queryIntentOrdersByRegistrarUnionid(@RequestParam("registrarUnionid") String registrarUnionid){
        return orderQuery.queryIntentOrdersByRegistrarUnionid(registrarUnionid);
    }

    /**
     * 修改意愿时间
     * @param orderDateChangeBean
     */
    @PutMapping(value = "/date")
    public void changeDateByOrderId(OrderDateChangeBean orderDateChangeBean){
        orderService.changeDateByOrderId(orderDateChangeBean);
    }

    /**
     * 通过订单主键修改意向状态
     * @param orderId
     */
    @PutMapping(value = "/intent")
    public void changeIsIntentByOrderId(Integer orderId){
        orderService.changeIsIntentByOrderId(orderId);
    }

    /**
     * 注册自定义订单
     * @param order
     */
    @PostMapping(value = "/customize")
    public void createCustomizeOrder(Order order){
        orderService.createCustomizeOrder(order);
    }
}
