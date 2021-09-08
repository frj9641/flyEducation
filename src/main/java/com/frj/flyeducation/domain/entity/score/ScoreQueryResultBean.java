package com.frj.flyeducation.domain.entity.score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScoreQueryResultBean {
    private Integer scoreId;
    private Integer homeworkId;
    private Integer classId;
    private Integer studentId;
    private String word;
    private List<String> photoUrl;
    private String mark;
}
