package com.frj.flyeducation.domain.entity.score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Score {
    private Integer scoreId;
    private Integer homeworkId;
    private Integer classId;
    private Integer studentId;
    private String word;
    private String photoUrl;
    private String mark;
}
