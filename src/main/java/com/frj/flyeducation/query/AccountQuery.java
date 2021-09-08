package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface AccountQuery {
    /**
     * 通过订单id查询学生姓名与报名课程
     * @param orderId
     * @return
     */
    CommonResult queryOrderByOrderId(Integer orderId);
}
