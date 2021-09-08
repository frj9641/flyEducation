package com.frj.flyeducation.service;


import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.entity.order.OrderDateChangeBean;
import com.frj.flyeducation.domain.entity.order.OrderRegistryBean;

public interface OrderService {
    /**
     * 注册订单
     * @param orderRegistryBean
     */
    CommonResult createOrder(OrderRegistryBean orderRegistryBean);

    /**
     * 通过订单主键改变添加好友状态
     * @param orderId
     */
    void changeIsAddedByOrderId(Integer orderId);

    /**
     * 通过订单主键修改会话完成状态
     * @param orderId
     */
    void changeIsFinishedByOrderId(Integer orderId);

    /**
     * 通过订单主键修改会话完成状态与意向状态
     * @param orderId
     */
    void changeIsFinishedAndIsIntentByOrderId(Integer orderId);

    /**
     * 修改意愿时间
     * @param orderDateChangeBean
     */
    void changeDateByOrderId(OrderDateChangeBean orderDateChangeBean);

    /**
     * 通过订单主键修改意向状态
     * @param orderId
     */
    void changeIsIntentByOrderId(Integer orderId);

    /**
     * 注册自定义订单
     * @param order
     */
    void createCustomizeOrder(Order order);
}
