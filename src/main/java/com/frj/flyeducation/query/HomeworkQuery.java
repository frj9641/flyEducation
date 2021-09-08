package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.student.StudentClassChangeBean;

public interface HomeworkQuery {
    /**
     * 通过班级id查询返回所有作业
     * @param classId
     * @return
     */
    CommonResult queryHomeworkByClassId(Integer classId);

    /**
     * 通过作业主键查询返回作业内容
     * @param homeworkId
     * @return
     */
    CommonResult queryHomeworkByHomeworkId(Integer homeworkId);

    /**
     * 通过学生id与班级id查询返回所有作业与评价
     * @param studentClassChangeBean
     * @return
     */
    CommonResult queryHomeworksAndMarksByStudentBean(StudentClassChangeBean studentClassChangeBean);
}
