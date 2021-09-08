package com.frj.flyeducation.domain.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    private Integer accountId;
    private Integer orderId;
    private Integer isPeriod;
    private Integer period;
    private String price;
}
