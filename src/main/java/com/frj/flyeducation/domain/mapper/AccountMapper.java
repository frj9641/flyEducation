package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.account.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    /**
     * 创建账单
     * @param account
     */
    void insertAccount(Account account);

    /**
     * 通过订单id查询返回课程类型
     * @param orderId
     * @return
     */
    Integer selectIsPeriodByOrderId(Integer orderId);

    /**
     * 通过订单id查询返回课时数量
     * @param orderId
     * @return
     */
    Integer selectPeriodByOrderId(Integer orderId);

    /**
     * 通过订单id修改课时数量
     * @param orderId
     */
    void updatePeriodByOrderId(Integer orderId);
}
