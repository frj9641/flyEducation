package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.customer.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /**
     * 通过用户unionid查询用户的主键
     * @param unionid
     * @return
     */
    Integer selectCustomerIdByUnionid(String unionid);

    /**
     * 注册客户信息
     * @param customer
     */
    void insertCustomer(Customer customer);

    /**
     * 通过客户主键查找返回客户信息
     * @param customerId
     * @return
     */
    Customer selectCustomerByCustomerId(Integer customerId);

    /**
     * 通过关键字模糊查询客户表
     * @param keyword
     * @return
     */
    List<Customer> selectCustomersByKeyword(String keyword);
}
