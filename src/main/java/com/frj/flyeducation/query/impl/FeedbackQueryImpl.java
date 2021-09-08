package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.feedback.Feedback;
import com.frj.flyeducation.domain.entity.feedback.FeedbackResultBean;
import com.frj.flyeducation.domain.mapper.FeedbackMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.query.FeedbackQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackQueryImpl implements FeedbackQuery {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 根据班级id查询返回家长反馈
     * @param classId
     * @return
     */
    @Override
    public CommonResult queryFeedbackByClassId(Integer classId) {
        List<Feedback> feedbacks = feedbackMapper.selectFeedbackByClassId(classId);
        List<FeedbackResultBean> feedbackResultBeans=new ArrayList<>();
        for(int x=0;x<feedbacks.size();x++){
            Feedback feedback = feedbacks.get(x);
            String studentName = studentMapper.selectStudentNameByStudentId(feedback.getStudentId());
            FeedbackResultBean feedbackResultBean = FeedbackResultBean.builder().feedbackId(feedback.getFeedbackId())
                    .studentName(studentName).mark(feedback.getMark())
                    .word(feedback.getWord()).date(feedback.getDate()).build();
            feedbackResultBeans.add(feedbackResultBean);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("feedbacks",feedbackResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
