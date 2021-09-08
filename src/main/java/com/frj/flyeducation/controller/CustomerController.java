package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.customer.CustomerRegistryBean;
import com.frj.flyeducation.domain.entity.order.OrderCustomizeRegistryBean;
import com.frj.flyeducation.query.CustomerQuery;
import com.frj.flyeducation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerQuery customerQuery;
    @Autowired
    private CustomerService customerService;

    /**
     * 通过用户unionid查询用户的主键
     * @param unionid
     * @return
     */
    @GetMapping
    public CommonResult queryCustomerIdByUnionid(@RequestParam("unionid") String unionid){
        return customerQuery.queryCustomerIdByUnionid(unionid);
    }

    /**
     * 注册客户信息
     * @param customerRegistryBean
     */
    @PostMapping
    public void createCustomer(CustomerRegistryBean customerRegistryBean){
        customerService.createCustomer(customerRegistryBean);
    }

    /**
     * 通过关键字模糊查询客户表
     * @param keyword
     * @return
     */
    @GetMapping(value = "/search")
    public CommonResult queryCustomersByKeyword(@RequestParam("keyword") String keyword){
        return customerQuery.queryCustomersByKeyword(keyword);
    }

    /**
     * 通过教务unionid和客户主键查询返回教务的营销课程与客户信息
     * @param orderCustomizeRegistryBean
     * @return
     */
    @PostMapping(value = "/customize")
    public CommonResult queryCustomerAndMarketsByOrderBean(OrderCustomizeRegistryBean orderCustomizeRegistryBean){
        return customerQuery.queryCustomerAndMarketsByOrderBean(orderCustomizeRegistryBean);
    }
}
