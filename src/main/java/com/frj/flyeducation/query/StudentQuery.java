package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface StudentQuery {
    /**
     * 通过班级序号查询返回班级学生
     * @param classId
     * @return
     */
    CommonResult queryStudentsByClassId(Integer classId);

    /**
     * 通过教务unionid查询返回未分配班级的学生
     * @param registrarUnionid
     * @return
     */
    CommonResult queryStudentsByRegistrarUnionid(String registrarUnionid);

    /**
     * 通过班级id返回班级学生与签到记录
     * @param classId
     * @return
     */
    CommonResult queryStudentsAndSignsByClassId(Integer classId);

    /**
     * 通过客户unionid查询返回所报班级
     * @param customerUnionid
     * @return
     */
    CommonResult queryClassesByCustomerUnionid(String customerUnionid);
}
