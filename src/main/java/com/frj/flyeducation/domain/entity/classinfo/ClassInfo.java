package com.frj.flyeducation.domain.entity.classinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClassInfo {
    private Integer classId;
    private String className;
    private String subject;
    private String teacherName;
    private String teacherUnionid;
    private String registrarName;
    private String registrarUnionid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
