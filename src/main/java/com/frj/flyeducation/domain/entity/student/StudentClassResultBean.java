package com.frj.flyeducation.domain.entity.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentClassResultBean {
    private Integer studentId;
    private Integer classId;
    private String className;
    private String studentName;
}
