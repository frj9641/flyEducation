package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface OrderQuery {
    /**
     * 通过教务unionid查询返回未完成的订单
     * @param registrarUnionid
     * @return
     */
    CommonResult queryOrdersByRegistrarUnionid(String registrarUnionid);

    /**
     * 通过教务unionid查询返回有意向的订单
     * @return
     */
    CommonResult queryIntentOrdersByRegistrarUnionid(String registrarUnionid);
}
