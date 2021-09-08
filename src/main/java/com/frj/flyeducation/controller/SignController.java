package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.sign.Sign;
import com.frj.flyeducation.query.SignQuery;
import com.frj.flyeducation.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sign")
public class SignController {
    @Autowired
    private SignService signService;
    @Autowired
    private SignQuery signQuery;
    /**
     * 提交签到记录
     * @param sign
     */
    @PostMapping
    public void createSign(Sign sign){
        signService.createSign(sign);
    }

    /**
     * 通过学生id与班级id返回考核统计
     * @param studentId
     * @return
     */
    @GetMapping(value = "/statistics")
    public CommonResult querySignsAndScoresByStudentId(@RequestParam("studentId") Integer studentId){
        return signQuery.querySignsAndScoresByStudentId(studentId);
    }
}
