package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.customer.CustomerQueryBean;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.entity.order.OrderDateChangeBean;
import com.frj.flyeducation.domain.entity.order.OrderRegistryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 新增订单表条目
     * @param order
     */
    Integer insertOrder(Order order);

    /**
     * 通过客户unionid和教务unionid判断是否加过好友
     * @param registrarUnionid
     * @param customerUnionid
     * @return
     */
    Integer selectIsAddedByUnionids(String registrarUnionid, String customerUnionid);

    /**
     * 通过教务unionid查询返回未完成的订单
     * @param registrarUnionid
     * @return
     */
    List<Order> selectOrdersByRegistrarUnionid(String registrarUnionid);

    /**
     * 通过订单主键改变添加好友状态
     * @param orderId
     */
    void updateIsAddedByOrderId(Integer orderId);

    /**
     * 通过订单主键修改会话完成状态
     * @param orderId
     */
    void updateIsFinishedByOrderId(Integer orderId);

    /**
     * 通过订单主键修改意向状态至0
     * @param orderId
     */
    void updateIsIntentByOrderId0(Integer orderId);

    /**
     * 通过订单主键修改意向状态至1
     * @param orderId
     */
    void updateIsIntentByOrderId1(Integer orderId);

    /**
     * 通过教务unionid查询返回有意向的订单
     * @param registrarUnionid
     * @return
     */
    List<Order> selectIntentOrdersByRegistrarUnionid(String registrarUnionid);

    /**
     * 修改意愿时间
     * @param orderDateChangeBean
     */
    void updateDateByOrderId(OrderDateChangeBean orderDateChangeBean);

    /**
     * 通过订单主键修改订单成功状态
     * @param orderId
     */
    void updateIsSuccessByOrderId(Integer orderId);

    /**
     * 通过订单主键查询返回订单信息
     * @param orderId
     * @return
     */
    Order selectOrderByOrderId(Integer orderId);

    /**
     * 通过用户unionid与营销课id查询订单主键
     * @param customerQueryBean
     * @return
     */
    Integer selectOrderIdByCustomerUnionid(CustomerQueryBean customerQueryBean);
}
