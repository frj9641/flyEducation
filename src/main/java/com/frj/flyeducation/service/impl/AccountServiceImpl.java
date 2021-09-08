package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.account.Account;
import com.frj.flyeducation.domain.entity.account.AccountCustomizeRegistryBean;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.entity.student.Student;
import com.frj.flyeducation.domain.mapper.AccountMapper;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.domain.mapper.OrderMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MarketMapper marketMapper;
    /**
     * 创建账单
     * @param account
     */
    @Transactional
    @Override
    public void createAccount(Account account) {
        Integer orderId=account.getOrderId();
        Order order = orderMapper.selectOrderByOrderId(orderId);
        Market market = marketMapper.selectMarketByMarketId(order.getMarketId());
        Account account1 = Account.builder().orderId(account.getOrderId()).isPeriod(market.getIsPeriod())
                .period(market.getPeriod()).price(account.getPrice()).build();
        accountMapper.insertAccount(account1);
        orderMapper.updateIsSuccessByOrderId(orderId);
        Student student = Student.builder().customerUnionid(order.getCustomerUnionid()).studentName(order.getStudentName())
                .parentName(order.getParentName()).school(order.getSchool()).grade(order.getGrade())
                .phone(order.getPhone()).orderId(orderId).marketName(market.getMarketName()).teacherName(market.getTeacherName())
                .registrarUnionid(order.getRegistrarUnionid()).build();
        studentMapper.insertStudent(student);
    }

    /**
     * 创建账单与学生
     * @param accountCustomizeRegistryBean
     */
    @Transactional
    @Override
    public void createCustomizeAccount(AccountCustomizeRegistryBean accountCustomizeRegistryBean) {
        Order order = orderMapper.selectOrderByOrderId(accountCustomizeRegistryBean.getOrderId());
        Market market = marketMapper.selectMarketByMarketId(order.getMarketId());
        Account account = Account.builder().orderId(accountCustomizeRegistryBean.getOrderId())
                .isPeriod(market.getIsPeriod()).period(market.getPeriod())
                .price(accountCustomizeRegistryBean.getPrice()).build();
        accountMapper.insertAccount(account);
        Student student = Student.builder().customerUnionid(order.getCustomerUnionid()).studentName(order.getStudentName())
                .parentName(order.getParentName()).school(order.getSchool()).grade(order.getGrade())
                .phone(order.getPhone()).orderId(order.getOrderId()).marketName(market.getMarketName()).teacherName(market.getTeacherName())
                .registrarUnionid(order.getRegistrarUnionid()).classId(accountCustomizeRegistryBean.getClassId())
                .className(accountCustomizeRegistryBean.getClassName()).build();
        studentMapper.insertStudent(student);
    }
}
