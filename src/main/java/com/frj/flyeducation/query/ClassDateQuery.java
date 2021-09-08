package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface ClassDateQuery {
    /**
     * 通过班级id查询返回班级上课时间
     * @param classId
     * @return
     */
    CommonResult queryClassDateByClassId(Integer classId);
}
