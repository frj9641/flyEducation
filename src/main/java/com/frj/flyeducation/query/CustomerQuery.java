package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.order.OrderCustomizeRegistryBean;

public interface CustomerQuery {
    /**
     * 通过用户unionid查询用户的主键
     * @param unionid
     * @return
     */
    CommonResult queryCustomerIdByUnionid(String unionid);

    /**
     * 通过关键字模糊查询客户表
     * @param keyword
     * @return
     */
    CommonResult queryCustomersByKeyword(String keyword);

    /**
     * 通过教务unionid和客户主键查询返回教务的营销课程与客户信息
     * @param orderCustomizeRegistryBean
     * @return
     */
    CommonResult queryCustomerAndMarketsByOrderBean(OrderCustomizeRegistryBean orderCustomizeRegistryBean);
}
