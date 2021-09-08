package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface FeedbackQuery {

    /**
     * 根据班级id查询返回家长反馈
     * @param classId
     * @return
     */
    CommonResult queryFeedbackByClassId(Integer classId);
}
