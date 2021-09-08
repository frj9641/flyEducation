package com.frj.flyeducation.domain.entity.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MarketResultBean {
    private Integer marketId;
    private String marketName;
    private String registrarUnionid;
}
