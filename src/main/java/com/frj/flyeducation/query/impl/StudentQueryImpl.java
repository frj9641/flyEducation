package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.sign.SignResultBean;
import com.frj.flyeducation.domain.entity.student.Student;
import com.frj.flyeducation.domain.entity.student.StudentClassResultBean;
import com.frj.flyeducation.domain.mapper.SignMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.query.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentQueryImpl implements StudentQuery {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SignMapper signMapper;
    /**
     * 通过班级序号查询返回班级学生
     * @param classId
     * @return
     */
    @Override
    public CommonResult queryStudentsByClassId(Integer classId) {
        List<Student> students = studentMapper.selectStudentsByClassId(classId);
        Map<String,Object> map=new HashMap<>();
        map.put("students",students);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过教务unionid查询返回未分配班级的学生
     * @param registrarUnionid
     * @return
     */
    @Override
    public CommonResult queryStudentsByRegistrarUnionid(String registrarUnionid) {
        List<Student> students = studentMapper.selectStudentsByRegistrarUnionid(registrarUnionid);
        Map<String,Object> map=new HashMap<>();
        map.put("students",students);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过班级id返回班级学生与签到记录
     * @param classId
     * @return
     */
    @Override
    public CommonResult queryStudentsAndSignsByClassId(Integer classId) {
        List<Student> students = studentMapper.selectStudentsByClassId(classId);
        List<SignResultBean> signResultBeans=new ArrayList<>();
        for(int x=0;x<students.size();x++){
            Integer mark = signMapper.selectMarkByStudentId(students.get(x).getStudentId());
            SignResultBean signResultBean = SignResultBean.builder().studentId(students.get(x).getStudentId())
                    .studentName(students.get(x).getStudentName()).mark(mark).build();
            signResultBeans.add(signResultBean);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("signs",signResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过客户unionid查询返回所报班级
     * @param customerUnionid
     * @return
     */
    @Override
    public CommonResult queryClassesByCustomerUnionid(String customerUnionid) {
        List<StudentClassResultBean> studentClassResultBeans = studentMapper.selectClassesByCustomerUnionid(customerUnionid);
        Map<String,Object> map=new HashMap<>();
        map.put("classes",studentClassResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
