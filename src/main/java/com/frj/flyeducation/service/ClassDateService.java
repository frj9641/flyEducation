package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.classdate.ClassDateChangeBean;
import com.frj.flyeducation.domain.entity.classdate.ClassDateRegistryBean;
import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface ClassDateService {
    /**
     * 批量创建课程日期
     * @param classDateRegistryBean
     */
    void createClassDate(ClassDateRegistryBean classDateRegistryBean);

    /**
     * 修改上课时间
     * @param classDateChangeBean
     * @return
     */
    CommonResult changeDateByClassId(ClassDateChangeBean classDateChangeBean);

    /**
     * 增加上课时间
     * @param classDateRegistryBean
     * @return
     */
    CommonResult addDateByClassId(ClassDateRegistryBean classDateRegistryBean);

    /**
     * 每日上课提醒
     */
    void classDateReminder();
}
