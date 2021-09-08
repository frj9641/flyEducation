package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.feedback.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackMapper {

    /**
     * 创建反馈
     * @param feedback
     */
    void insertFeedback(Feedback feedback);

    /**
     * 根据班级id查询返回家长反馈
     * @param classId
     * @return
     */
    List<Feedback> selectFeedbackByClassId(Integer classId);
}
