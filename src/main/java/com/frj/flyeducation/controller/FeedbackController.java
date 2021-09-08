package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.feedback.Feedback;
import com.frj.flyeducation.query.FeedbackQuery;
import com.frj.flyeducation.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackQuery feedbackQuery;
    /**
     * 创建反馈
     * @param feedback
     */
    @PostMapping
    public void createFeedback(Feedback feedback){
        feedbackService.createFeedback(feedback);
    }

    /**
     * 根据班级id查询返回家长反馈
     * @param classId
     * @return
     */
    @GetMapping
    public CommonResult queryFeedbackByClassId(@RequestParam("classId") Integer classId){
        return feedbackQuery.queryFeedbackByClassId(classId);
    }
}
