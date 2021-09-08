package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.classinfo.ClassInfo;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.query.ClassInfoQuery;
import com.frj.flyeducation.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/classInfo")
public class ClassInfoController {
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private ClassInfoQuery classInfoQuery;
    /**
     * 创建正式班级
     * @param classInfo
     */
    @PostMapping
    public void createClassInfo(ClassInfo classInfo){
        classInfoService.createClassInfo(classInfo);
    }

    /**
     * 通过教务unionid查询返回教务的正式班级
     * @param registrarUnionid
     * @return
     */
    @GetMapping
    public CommonResult queryClassInfoByRegistrarUnionid(@RequestParam("registrarUnionid") String registrarUnionid){
        return classInfoQuery.queryClassInfoByRegistrarUnionid(registrarUnionid);
    }

    /**
     * 通过班级主键删除班级信息，班级学员信息，课程时间表
     * @param classId
     */
    @DeleteMapping
    public void removeClassInfoByClassId(Integer classId){
        classInfoQuery.removeClassInfoByClassId(classId);
    }

    /**
     * 通过老师unionid查询返回老师今天的正式班级
     * @param teacherUnionid
     * @return
     */
    @GetMapping(value = "/today")
    public CommonResult queryTodayClassInfoByTeacherUnionid(@RequestParam("teacherUnionid") String teacherUnionid){
        return classInfoQuery.queryTodayClassInfoByTeacherUnionid(teacherUnionid);
    }

    /**
     * 通过老师unionid查询返回老师的正式班级
     * @param teacherUnionid
     * @return
     */
    @GetMapping(value = "/teacher")
    public CommonResult queryClassInfoByTeacherUnionid(@RequestParam("teacherUnionid") String teacherUnionid){
        return classInfoQuery.queryClassInfoByTeacherUnionid(teacherUnionid);
    }

    /**
     * 通过老师或教务的unionid查询返回其正式班级
     * @param unionid
     * @return
     */
    @GetMapping(value = "/feedback")
    public CommonResult queryClassInfosByUnionid(@RequestParam("unionid") String unionid){
        return classInfoQuery.queryClassInfosByUnionid(unionid);
    }
}
