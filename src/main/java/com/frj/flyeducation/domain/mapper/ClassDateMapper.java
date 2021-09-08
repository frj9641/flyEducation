package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.classdate.ClassDate;
import com.frj.flyeducation.domain.entity.classdate.ClassDateChangeBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassDateMapper {

    /**
     * 批量创建课程日期
     * @param classDate
     */
    void insertClassDate(ClassDate classDate);

    /**
     * 通过班级id查询返回班级上课时间
     * @param classId
     * @return
     */
    List<ClassDate> selectClassDateByClassId(Integer classId);

    /**
     * 修改上课时间
     * @param classDateChangeBean
     */
    void updateDateByClassId(ClassDateChangeBean classDateChangeBean);

    /**
     * 通过班级主键删除课程时间表
     * @param classId
     */
    void deleteClassDatesByClassId(Integer classId);

    /**
     * 通过日期查找返回明天有课的班级信息
     * @return
     */
    List<ClassDate> selectClassDatesByDate();

    /**
     * 通过班级序号查询班级今天是否上课
     * @param classId
     * @return
     */
    Integer selectClassDateIdByClassId(Integer classId);

    /**
     * 通过班级序号与原定上课日期查询返回原定上课时间
     * @param classDateChangeBean
     * @return
     */
    String selectTimeByDate(ClassDateChangeBean classDateChangeBean);

}
