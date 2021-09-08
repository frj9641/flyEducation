package com.frj.flyeducation.domain.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private Integer orderId;
    private String customerUnionid;
    private String studentName;
    private String parentName;
    private String school;
    private Integer grade;
    private String phone;
    private String registrarUnionid;
    private Integer marketId;
    private Integer isAdded;
    private Integer isFinished;
    private Integer isIntent;
    private Integer isSuccess;
    private String date;
}
