package com.frj.flyeducation.domain.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderCustomizeRegistryBean {
    private String registrarUnionid;
    private Integer customerId;
}
