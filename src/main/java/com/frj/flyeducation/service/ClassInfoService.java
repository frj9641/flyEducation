package com.frj.flyeducation.service;


import com.frj.flyeducation.domain.entity.classinfo.ClassInfo;

public interface ClassInfoService {

    /**
     * 创建正式班级
     * @param classInfo
     */
    void createClassInfo(ClassInfo classInfo);
}
