package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.school.School;
import com.frj.flyeducation.domain.mapper.SchoolMapper;
import com.frj.flyeducation.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;
    /**
     * 登记学生成绩
     * @param school
     */
    @Override
    public void createSchool(School school) {
        schoolMapper.insertSchool(school);
    }
}
