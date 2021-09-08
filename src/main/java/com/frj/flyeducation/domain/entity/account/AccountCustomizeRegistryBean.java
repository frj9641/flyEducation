package com.frj.flyeducation.domain.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountCustomizeRegistryBean {
    private Integer orderId;
    private String price;
    private Integer classId;
    private String className;
}
