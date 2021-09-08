package com.frj.flyeducation.domain.entity.homework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HomeworkMarkQueryBean {
    private Integer homeworkId;
    private Integer studentId;
}
