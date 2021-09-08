package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.feedback.Feedback;
import com.frj.flyeducation.domain.mapper.FeedbackMapper;
import com.frj.flyeducation.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    /**
     * 创建反馈
     * @param feedback
     */
    @Override
    public void createFeedback(Feedback feedback) {
        feedbackMapper.insertFeedback(feedback);
    }
}
