package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.classinfo.ClassInfo;
import com.frj.flyeducation.domain.entity.classinfo.ClassInfoResultBean;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.mapper.*;
import com.frj.flyeducation.query.ClassInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassInfoQueryImpl implements ClassInfoQuery {
    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassDateMapper classDateMapper;
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    /**
     * 通过教务unionid查询返回教务的正式班级
     * @param registrarUnionid
     * @return
     */
    @Override
    public CommonResult queryClassInfoByRegistrarUnionid(String registrarUnionid) {
        List<ClassInfo> classInfos = classInfoMapper.selectClassInfosByRegistrarUnionid(registrarUnionid);
        Map<String,Object> map=new HashMap<>();
        map.put("classInfos",classInfos);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过班级主键删除班级信息，班级学员信息，课程时间表
     * @param classId
     */
    @Transactional
    @Override
    public void removeClassInfoByClassId(Integer classId) {
        classInfoMapper.deleteClassInfoByClassId(classId);
        studentMapper.deleteStudentsByClassId(classId);
        classDateMapper.deleteClassDatesByClassId(classId);
        signMapper.deleteSignsByclassId(classId);
        homeworkMapper.deleteHomeworksByClassId(classId);
        scoreMapper.deleteScoresByClassId(classId);
    }

    /**
     * 通过老师unionid查询返回老师今天的正式班级
     * @param teacherUnionid
     * @return
     */
    @Override
    public CommonResult queryTodayClassInfoByTeacherUnionid(String teacherUnionid) {
        List<ClassInfo> classInfos = classInfoMapper.selectClassInfosByTeacherUnionid(teacherUnionid);
        List<ClassInfoResultBean> classInfoResultBeans=new ArrayList<>();
        for(int x=0;x<classInfos.size();x++){
            ClassInfo classInfo = classInfos.get(x);
            ClassInfoResultBean classInfoResultBean = ClassInfoResultBean.builder().classId(classInfo.getClassId())
                    .className(classInfo.getClassName()).subject(classInfo.getSubject())
                    .teacherName(classInfo.getTeacherName()).registrarName(classInfo.getRegistrarName()).build();
            Integer classId = classInfo.getClassId();
            Integer classDateId = classDateMapper.selectClassDateIdByClassId(classId);
            if(classDateId==null){
                classInfoResultBean.setIsToday(0);
            }else{
                classInfoResultBean.setIsToday(1);
            }
            classInfoResultBeans.add(classInfoResultBean);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("classInfos",classInfoResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过老师unionid查询返回老师的正式班级
     * @param teacherUnionid
     * @return
     */
    @Override
    public CommonResult queryClassInfoByTeacherUnionid(String teacherUnionid) {
        List<ClassInfo> classInfos = classInfoMapper.selectClassInfosByTeacherUnionid(teacherUnionid);
        Map<String,Object> map=new HashMap<>();
        map.put("classInfos",classInfos);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过老师或教务的unionid查询返回其正式班级
     * @param unionid
     * @return
     */
    @Override
    public CommonResult queryClassInfosByUnionid(String unionid) {
        List<ClassInfo> classInfos = classInfoMapper.selectClassInfosByUnionid(unionid);
        Map<String,Object> map=new HashMap<>();
        map.put("classInfos",classInfos);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
