package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface ScoreQuery {
    /**
     * 根据作业id查询返回作业提交情况
     * @param homeworkId
     * @return
     */
    CommonResult queryScoresByHomeworkId(Integer homeworkId);

    /**
     * 根据考核主键返回作业内容
     * @param scoreId
     * @return
     */
    CommonResult queryScoreByScoreId(Integer scoreId);
}
