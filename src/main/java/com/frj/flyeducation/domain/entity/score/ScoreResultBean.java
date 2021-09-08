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
public class ScoreResultBean {
    private Integer scoreId;
    private String studentName;
    private String mark;
    private String photoUrl;
}
