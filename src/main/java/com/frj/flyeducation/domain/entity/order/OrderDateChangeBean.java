package com.frj.flyeducation.domain.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDateChangeBean {
    private Integer orderId;
    private String date;
}
