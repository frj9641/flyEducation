package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.employee.Employee;
import com.frj.flyeducation.domain.mapper.EmployeeMapper;
import com.frj.flyeducation.query.EmployeeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 查询返回所有教务与老师
     * @return
     */
    @Override
    public CommonResult queryEmployeesByIs() {
        List<Employee> registrars = employeeMapper.selectEmployeesByIsRegistrar();
        List<Employee> teachers = employeeMapper.selectEmployeesByIsTeacher();
        Map<String,Object> map=new HashMap<>();
        map.put("registrars",registrars);
        map.put("teachers",teachers);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
