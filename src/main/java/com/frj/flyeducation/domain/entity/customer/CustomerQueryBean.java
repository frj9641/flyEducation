package com.frj.flyeducation.domain.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerQueryBean {
    private String customerUnionid;
    private Integer marketId;
}
