package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.classinfo.ClassInfo;
import com.frj.flyeducation.domain.mapper.ClassInfoMapper;
import com.frj.flyeducation.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {
    @Autowired
    private ClassInfoMapper classInfoMapper;
    /**
     * 创建正式班级
     * @param classInfo
     */
    @Override
    public void createClassInfo(ClassInfo classInfo) {
        classInfoMapper.insertClassInfo(classInfo);
    }
}
