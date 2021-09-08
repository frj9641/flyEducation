package com.frj.flyeducation.service;


import com.frj.flyeducation.domain.entity.school.School;

public interface SchoolService {
    /**
     * 登记学生成绩
     * @param school
     */
    void createSchool(School school);
}
