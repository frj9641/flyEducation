package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.account.Account;
import com.frj.flyeducation.domain.entity.account.AccountCustomizeRegistryBean;

public interface AccountService {

    /**
     * 创建账单
     * @param account
     */
    void createAccount(Account account);

    /**
     * 创建账单与学生
     * @param accountCustomizeRegistryBean
     */
    void createCustomizeAccount(AccountCustomizeRegistryBean accountCustomizeRegistryBean);
}
