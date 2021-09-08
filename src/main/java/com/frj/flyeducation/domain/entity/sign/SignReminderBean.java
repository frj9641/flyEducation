package com.frj.flyeducation.domain.entity.sign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignReminderBean {
    private String customerUnionid;
    private String studentName;
    private String className;
    private Date date;
    private Integer consume;
    private Integer last;
}
