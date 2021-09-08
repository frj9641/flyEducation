package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.homework.HomeworkMarkQueryBean;
import com.frj.flyeducation.domain.entity.score.Score;
import com.frj.flyeducation.domain.entity.score.ScoreRegistryBean;
import com.frj.flyeducation.domain.entity.score.ScoreResultBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {
    /**
     * 新增提交作业
     * @param score
     */
    void insertScore(Score score);

    /**
     * 根据作业id查询返回作业提交情况
     * @param homeworkId
     * @return
     */
    List<Score> selectScoresByHomeworkId(Integer homeworkId);

    /**
     * 根据考核主键返回作业内容
     * @param scoreId
     * @return
     */
    Score selectScoreByScoreId(Integer scoreId);

    /**
     * 根据考核主键修改作业评分
     * @param scoreRegistryBean
     */
    void updateMarkByScoreId(ScoreRegistryBean scoreRegistryBean);

    /**
     * 通过班级id删除作业评分
     * @param classId
     */
    void deleteScoresByClassId(Integer classId);

    /**
     * 通过作业id与学生id查询返回批改情况
     * @param homeworkMarkQueryBean
     * @return
     */
    ScoreResultBean selectScoreByHomeworkMarkQueryBean(HomeworkMarkQueryBean homeworkMarkQueryBean);

    /**
     * 通过作业id与学生id查询返回评分主键
     * @param score
     * @return
     */
    Integer selectScoreIdByScore(Score score);

    /**
     * 通过作业id删除作业评分
     * @param homeworkId
     */
    void deleteScoresByHomeworkId(Integer homeworkId);
}
