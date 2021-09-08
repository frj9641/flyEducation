package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface ClassInfoQuery {
    /**
     * 通过教务unionid查询返回教务的正式班级
     * @param registrarUnionid
     * @return
     */
    CommonResult queryClassInfoByRegistrarUnionid(String registrarUnionid);

    /**
     * 通过班级主键删除班级信息，班级学员信息，课程时间表
     * @param classId
     */
    void removeClassInfoByClassId(Integer classId);

    /**
     * 通过老师unionid查询返回老师今天的正式班级
     * @param teacherUnionid
     * @return
     */
    CommonResult queryTodayClassInfoByTeacherUnionid(String teacherUnionid);

    /**
     * 通过老师unionid查询返回老师的正式班级
     * @param teacherUnionid
     * @return
     */
    CommonResult queryClassInfoByTeacherUnionid(String teacherUnionid);

    /**
     * 通过老师或教务的unionid查询返回其正式班级
     * @param unionid
     * @return
     */
    CommonResult queryClassInfosByUnionid(String unionid);
}
