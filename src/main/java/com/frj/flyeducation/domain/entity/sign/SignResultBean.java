package com.frj.flyeducation.domain.entity.sign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignResultBean {
    private Integer studentId;
    private String studentName;
    private Integer mark;
}
