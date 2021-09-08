package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.employee.Employee;
import com.frj.flyeducation.domain.mapper.EmployeeMapper;
import com.frj.flyeducation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 创建员工
     * @param employee
     */
    @Override
    public void createEmployee(Employee employee) {
        employeeMapper.insertEmployee(employee);
    }
}
