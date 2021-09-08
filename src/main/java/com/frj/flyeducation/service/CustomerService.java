package com.frj.flyeducation.service;


import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.customer.CustomerRegistryBean;

public interface CustomerService {
    /**
     * 注册客户信息
     * @param customerRegistryBean
     */
    void createCustomer(CustomerRegistryBean customerRegistryBean);
}
