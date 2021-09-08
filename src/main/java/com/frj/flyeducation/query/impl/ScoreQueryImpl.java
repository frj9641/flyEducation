package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.score.Score;
import com.frj.flyeducation.domain.entity.score.ScoreQueryResultBean;
import com.frj.flyeducation.domain.entity.score.ScoreResultBean;
import com.frj.flyeducation.domain.mapper.ScoreMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.query.ScoreQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreQueryImpl implements ScoreQuery {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 根据作业id查询返回作业提交情况
     * @param homeworkId
     * @return
     */
    @Override
    public CommonResult queryScoresByHomeworkId(Integer homeworkId) {
        List<Score> scores = scoreMapper.selectScoresByHomeworkId(homeworkId);
        List<ScoreResultBean> result=new ArrayList<>();
        for(int x=0;x<scores.size();x++){
            String studentName = studentMapper.selectStudentNameByStudentId(scores.get(x).getStudentId());
            ScoreResultBean scoreResultBean = ScoreResultBean.builder().scoreId(scores.get(x).getScoreId()).studentName(studentName)
                    .mark(scores.get(x).getMark()).build();
            result.add(scoreResultBean);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("result",result);
        CommonResult result1 = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result1;
    }

    /**
     * 根据考核主键返回作业内容
     * @param scoreId
     * @return
     */
    @Override
    public CommonResult queryScoreByScoreId(Integer scoreId) {
        Score score = scoreMapper.selectScoreByScoreId(scoreId);
        String photoUrl = score.getPhotoUrl();
        String[] urls = photoUrl.split(",");
        List<String> photoUrls=new ArrayList<>();
        photoUrls.addAll(Arrays.asList(urls));
        ScoreQueryResultBean scoreQueryResultBean = ScoreQueryResultBean.builder().scoreId(scoreId).classId(score.getClassId())
                .studentId(score.getStudentId()).homeworkId(score.getHomeworkId())
                .word(score.getWord()).photoUrl(photoUrls).mark(score.getMark()).build();
        Map<String,Object> map=new HashMap<>();
        map.put("score",scoreQueryResultBean);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
