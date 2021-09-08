package com.frj.flyeducation.domain.entity.classinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClassInfoResultBean {
    private Integer classId;
    private String className;
    private String subject;
    private String teacherName;
    private String registrarName;
    private Integer isToday;
}
