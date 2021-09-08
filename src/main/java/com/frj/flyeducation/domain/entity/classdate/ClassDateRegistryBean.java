package com.frj.flyeducation.domain.entity.classdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClassDateRegistryBean {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<Date> dates;
    private Integer classId;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
}
