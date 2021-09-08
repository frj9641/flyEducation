package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.student.StudentClassChangeBean;
import com.frj.flyeducation.domain.entity.student.StudentCustomizeRegistryBean;

public interface StudentService {
    /**
     * 通过学生主键设置班级序号或置空班级序号
     * @param studentClassChangeBean
     */
    void changeClassIdAndClassNameByStudentId(StudentClassChangeBean studentClassChangeBean);

    /**
     * 注册自定义学员
     * @param studentCustomizeRegistryBean
     */
    CommonResult createCustomizeStudent(StudentCustomizeRegistryBean studentCustomizeRegistryBean);
}
