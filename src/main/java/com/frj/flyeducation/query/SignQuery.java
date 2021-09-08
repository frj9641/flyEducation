package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface SignQuery {
    /**
     * 通过学生id与班级id返回考核统计
     * @param studentId
     * @return
     */
    CommonResult querySignsAndScoresByStudentId(Integer studentId);
}
