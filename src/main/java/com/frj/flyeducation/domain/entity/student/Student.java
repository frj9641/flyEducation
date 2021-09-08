package com.frj.flyeducation.domain.entity.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    private Integer studentId;
    private String customerUnionid;
    private String studentName;
    private String parentName;
    private String school;
    private Integer grade;
    private String phone;
    private Integer orderId;
    private String marketName;
    private String teacherName;
    private String registrarUnionid;
    private Integer classId;
    private String className;
}
