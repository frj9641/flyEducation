package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.employee.Employee;
import com.frj.flyeducation.query.EmployeeQuery;
import com.frj.flyeducation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeQuery employeeQuery;

    /**
     * 创建员工
     * @param employee
     */
    @PostMapping
    public void createEmployee(Employee employee){
        employeeService.createEmployee(employee);
    }

    /**
     * 查询返回所有教务与老师
     * @return
     */
    @GetMapping
    public CommonResult queryEmployeesByIs(){
        return employeeQuery.queryEmployeesByIs();
    }
}
