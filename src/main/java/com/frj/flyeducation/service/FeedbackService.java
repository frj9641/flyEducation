package com.frj.flyeducation.service;


import com.frj.flyeducation.domain.entity.feedback.Feedback;

public interface FeedbackService {
    /**
     * 创建反馈
     * @param feedback
     */
    void createFeedback(Feedback feedback);
}
