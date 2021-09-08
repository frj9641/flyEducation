package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.school.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolMapper {
    /**
     * 等级学生成绩
     * @param school
     */
    void insertSchool(School school);

    /**
     * 通过学生id查询返回学生在校成绩
     * @param studentId
     * @return
     */
    List<School> selectSchoolsByStudentId(Integer studentId);
}
