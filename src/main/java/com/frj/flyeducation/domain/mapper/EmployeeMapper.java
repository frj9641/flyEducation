package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.employee.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 创建员工
     * @param employee
     */
    void insertEmployee(Employee employee);

    /**
     * 查询返回所有教务
     */
    List<Employee> selectEmployeesByIsRegistrar();

    /**
     * 查询返回所有老师
     */
    List<Employee> selectEmployeesByIsTeacher();

    /**
     * 通过unionid查询用户职责
     * @param employeeUnionid
     * @return
     */
    String selectDutyByEmployeeUnionid(String employeeUnionid);
}
