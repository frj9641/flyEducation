package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.student.Student;
import com.frj.flyeducation.domain.entity.student.StudentClassChangeBean;
import com.frj.flyeducation.domain.entity.student.StudentClassResultBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StudentMapper {
    /**
     * 新增正式学员
     * @param student
     */
    void insertStudent(Student student);

    /**
     * 通过班级序号查询返回班级学生
     * @param classId
     * @return
     */
    List<Student> selectStudentsByClassId(Integer classId);

    /**
     * 通过学生主键设置班级序号或置空班级序号
     * @param studentClassChangeBean
     */
    void updateClassIdAndClassNameByStudentId(StudentClassChangeBean studentClassChangeBean);

    /**
     * 通过教务unionid查询返回未分配班级的学生
     * @param registrarUnionid
     * @return
     */
    List<Student> selectStudentsByRegistrarUnionid(String registrarUnionid);

    /**
     * 通过班级主键删除班级学员信息
     * @param classId
     */
    void deleteStudentsByClassId(Integer classId);

    /**
     * 通过客户unionid查询返回所报班级
     * @param customerUnionid
     * @return
     */
    List<StudentClassResultBean> selectClassesByCustomerUnionid(String customerUnionid);

    /**
     * 通过学生主键查询返回学生姓名
     * @param studentId
     * @return
     */
    String selectStudentNameByStudentId(Integer studentId);

    /**
     * 通过学生主键查询返回订单id
     * @param studentId
     * @return
     */
    Integer selectOrderIdByStudentId(Integer studentId);

    /**
     * 通过学生主键查询返回学生信息
     * @param studentId
     * @return
     */
    Student selectStudentByStudentId(Integer studentId);
}
