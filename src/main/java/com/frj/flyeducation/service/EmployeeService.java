package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.employee.Employee;

public interface EmployeeService {
    /**
     * 创建员工
     * @param employee
     */
    void createEmployee(Employee employee);
}
