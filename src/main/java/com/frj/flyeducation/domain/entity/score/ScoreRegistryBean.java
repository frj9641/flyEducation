package com.frj.flyeducation.domain.entity.score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScoreRegistryBean {
    private Integer scoreId;
    private String mark;
}
