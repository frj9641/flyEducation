package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.classdate.ClassDateChangeBean;
import com.frj.flyeducation.domain.entity.classdate.ClassDateRegistryBean;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.query.ClassDateQuery;
import com.frj.flyeducation.service.ClassDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/classDate")
public class ClassDateController {
    @Autowired
    private ClassDateService classDateService;
    @Autowired
    private ClassDateQuery classDateQuery;

    /**
     * 批量创建课程日期
     * @param classDateRegistryBean
     */
    @PostMapping
    public void createClassDate(ClassDateRegistryBean classDateRegistryBean){
        classDateService.createClassDate(classDateRegistryBean);
    }

    /**
     * 通过班级id查询返回班级上课时间
     * @param classId
     * @return
     */
    @GetMapping
    public CommonResult queryClassDateByClassId(@RequestParam("classId") Integer classId){
        return classDateQuery.queryClassDateByClassId(classId);
    }

    /**
     * 修改上课时间
     * @param classDateChangeBean
     * @return
     */
    @PutMapping
    public CommonResult changeDateByClassId(ClassDateChangeBean classDateChangeBean){
        return classDateService.changeDateByClassId(classDateChangeBean);
    }

    /**
     * 增加上课时间
     * @param classDateRegistryBean
     * @return
     */
    @PostMapping(value = "/reminder")
    public CommonResult addDateByClassId(ClassDateRegistryBean classDateRegistryBean){
        return classDateService.addDateByClassId(classDateRegistryBean);
    }

    /**
     * 每日上课提醒
     */
    @Scheduled(cron = "0 00 19 * * *")
    public void classDateReminder(){
        classDateService.classDateReminder();
    }
}
