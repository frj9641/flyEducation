package com.frj.flyeducation.domain.entity.classdate;

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
public class ClassDateChangeBean {
    private Integer classId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date newDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date oldDay;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
}
