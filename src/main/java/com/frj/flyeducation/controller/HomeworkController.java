package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.homework.Homework;
import com.frj.flyeducation.domain.entity.student.StudentClassChangeBean;
import com.frj.flyeducation.query.HomeworkQuery;
import com.frj.flyeducation.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private HomeworkQuery homeworkQuery;

    /**
     * 上传作业
     */
    @PostMapping
    public CommonResult createHomework(Homework homework){
        return homeworkService.createHomework(homework);
    }

    /**
     * 上传作业照片
     * @param file
     */
    @PostMapping(value = "/photo")
    public void uploadPhoto(@RequestParam("photo") MultipartFile file){
        homeworkService.uploadPhoto(file);
    }

    /**
     * 通过班级id查询返回所有作业
     * @param classId
     * @return
     */
    @GetMapping
    public CommonResult queryHomeworkByClassId(@RequestParam("classId") Integer classId){
        return homeworkQuery.queryHomeworkByClassId(classId);
    }

    /**
     * 通过学生id与班级id查询返回所有作业与评价
     * @param studentClassChangeBean
     * @return
     */
    @PostMapping(value = "/mark")
    public CommonResult queryHomeworksAndMarksByStudentBean(StudentClassChangeBean studentClassChangeBean){
        return homeworkQuery.queryHomeworksAndMarksByStudentBean(studentClassChangeBean);
    }

    /**
     * 通过作业主键查询返回作业内容
     * @param homeworkId
     * @return
     */
    @GetMapping(value = "/question")
    public CommonResult queryHomeworkByHomeworkId(@RequestParam("homeworkId") Integer homeworkId){
        return homeworkQuery.queryHomeworkByHomeworkId(homeworkId);
    }

    /**
     * 通过作业主键删除作业
     * @param homeworkId
     */
    @DeleteMapping
    public void removeHomeworkByHomeworkId(Integer homeworkId){
        homeworkService.removeHomeworkByHomeworkId(homeworkId);
    }
}
