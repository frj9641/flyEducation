package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface EmployeeQuery {
    /**
     * 查询返回所有教务与老师
     * @return
     */
    CommonResult queryEmployeesByIs();
}
