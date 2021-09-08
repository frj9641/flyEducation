package com.frj.flyeducation.domain.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {
    private Integer customerId;
    private String customerUnionid;
    private String studentName;
    private String parentName;
    private String school;
    private Integer grade;
    private String phone;
}
