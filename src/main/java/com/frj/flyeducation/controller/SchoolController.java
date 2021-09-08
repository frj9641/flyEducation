package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.school.School;
import com.frj.flyeducation.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    /**
     * 登记学生成绩
     * @param school
     */
    @PostMapping
    public void createSchool(School school){
        schoolService.createSchool(school);
    }
}
