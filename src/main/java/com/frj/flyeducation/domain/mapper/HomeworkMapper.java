package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.homework.Homework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    /**
     * 上传作业
     * @param homework
     */
    void insertHomework(Homework homework);

    /**
     * 通过班级id查询返回所有作业
     * @param classId
     * @return
     */
    List<Homework> selectHomeworkByClassId(Integer classId);

    /**
     * 通过作业主键查询返回作业内容
     * @param homeworkId
     * @return
     */
    Homework selectHomeworkByHomeworkId(Integer homeworkId);

    /**
     * 通过班级id删除作业
     * @param classId
     */
    void deleteHomeworksByClassId(Integer classId);

    /**
     * 通过班级id和今日查询作业主键
     * @param classId
     * @return
     */
    Integer selectHomeworkIdByDate(Integer classId);

    /**
     * 通过作业主键删除作业
     * @param homeworkId
     */
    void deleteHomeworkByHomeworkId(Integer homeworkId);
}
