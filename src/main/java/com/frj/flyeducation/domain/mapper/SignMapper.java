package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.sign.Sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignMapper {
    /**
     * 通过班级id与学生id查询返回今天的签到记录
     * @param studentId
     * @return
     */
    Integer selectMarkByStudentId(Integer studentId);

    /**
     * 提交签到记录
     * @param sign
     */
    void insertSign(Sign sign);

    /**
     * 通过学生id与班级id返回出勤统计
     * @param studentId
     */
    List<Sign> selectSignsByStudentId(Integer studentId);

    /**
     * 通过班级id删除签到记录
     * @param classId
     */
    void deleteSignsByclassId(Integer classId);

}
