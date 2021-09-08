package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.account.Account;
import com.frj.flyeducation.domain.entity.account.AccountCustomizeRegistryBean;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.query.AccountQuery;
import com.frj.flyeducation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountQuery accountQuery;
    @Autowired
    private AccountService accountService;

    /**
     * 通过订单id查询学生姓名与报名课程
     * @param orderId
     * @return
     */
    @GetMapping
    public CommonResult queryOrderByOrderId(@RequestParam("orderId") Integer orderId){
        return accountQuery.queryOrderByOrderId(orderId);
    }

    /**
     * 创建账单
     * @param account
     */
    @PostMapping
    public void createAccount(Account account){
        accountService.createAccount(account);
    }

    /**
     * 创建账单与学生
     * @param accountCustomizeRegistryBean
     */
    @PostMapping(value = "/student")
    public void createCustomizeAccount(AccountCustomizeRegistryBean accountCustomizeRegistryBean){
        accountService.createCustomizeAccount(accountCustomizeRegistryBean);
    }
}
