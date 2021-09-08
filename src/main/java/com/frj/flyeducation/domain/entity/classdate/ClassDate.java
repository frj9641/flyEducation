package com.frj.flyeducation.domain.entity.classdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClassDate {
    private Integer classDateId;
    private Integer classId;
    private Date date;
    private String time;
}
