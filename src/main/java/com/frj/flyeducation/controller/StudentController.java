package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.student.StudentClassChangeBean;
import com.frj.flyeducation.domain.entity.student.StudentCustomizeRegistryBean;
import com.frj.flyeducation.query.StudentQuery;
import com.frj.flyeducation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentQuery studentQuery;
    @Autowired
    private StudentService studentService;
    /**
     * 通过班级序号查询返回班级学生
     * @param classId
     * @return
     */
    @GetMapping(value = "/query")
    public CommonResult queryStudentsByClassId(@RequestParam("classId") Integer classId){
        return studentQuery.queryStudentsByClassId(classId);
    }

    /**
     * 通过学生主键设置班级序号或置空班级序号
     * @param studentClassChangeBean
     */
    @PutMapping
    public void changeClassIdAndClassNameByStudentId(StudentClassChangeBean studentClassChangeBean){
        studentService.changeClassIdAndClassNameByStudentId(studentClassChangeBean);
    }

    /**
     * 通过教务unionid查询返回未分配班级的学生
     * @param registrarUnionid
     * @return
     */
    @GetMapping(value = "/add")
    public CommonResult queryStudentsByRegistrarUnionid(@RequestParam("registrarUnionid") String registrarUnionid){
        return studentQuery.queryStudentsByRegistrarUnionid(registrarUnionid);
    }

    /**
     * 注册自定义学员
     * @param studentCustomizeRegistryBean
     */
    @PostMapping(value = "/customize")
    public CommonResult createCustomizeStudent(StudentCustomizeRegistryBean studentCustomizeRegistryBean){
        return studentService.createCustomizeStudent(studentCustomizeRegistryBean);
    }

    /**
     * 通过班级id返回班级学生与签到记录
     * @param classId
     * @return
     */
    @GetMapping(value = "/sign")
    public CommonResult queryStudentsAndSignsByClassId(@RequestParam("classId") Integer classId){
        return studentQuery.queryStudentsAndSignsByClassId(classId);
    }

    /**
     * 通过客户unionid查询返回所报班级
     * @param customerUnionid
     * @return
     */
    @GetMapping(value = "/class")
    public CommonResult queryClassesByCustomerUnionid(@RequestParam("customerUnionid") String customerUnionid){
        return studentQuery.queryClassesByCustomerUnionid(customerUnionid);
    }
}
