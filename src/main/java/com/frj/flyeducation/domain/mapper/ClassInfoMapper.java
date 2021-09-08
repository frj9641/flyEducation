package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.classinfo.ClassInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassInfoMapper {
    /**
     * 创建正式班级
     * @param classInfo
     */
    void insertClassInfo(ClassInfo classInfo);

    /**
     * 通过教务unionid查询返回教务的正式班级
     * @param registrarUnionid
     * @return
     */
    List<ClassInfo> selectClassInfosByRegistrarUnionid(String registrarUnionid);

    /**
     * 通过班级主键删除班级信息
     * @param classId
     */
    void deleteClassInfoByClassId(Integer classId);


    /**
     * 通过老师unionid查询返回老师的正式班级
     * @param teacherUnionid
     * @return
     */
    List<ClassInfo> selectClassInfosByTeacherUnionid(String teacherUnionid);

    /**
     * 通过老师或教务的unionid查询返回其正式班级
     * @param unionid
     * @return
     */
    List<ClassInfo> selectClassInfosByUnionid(String unionid);

    /**
     * 通过班级主键查询返回班级老师unionid
     * @param classId
     * @return
     */
    String selectTeacherUnionidByClassId(Integer classId);
}
