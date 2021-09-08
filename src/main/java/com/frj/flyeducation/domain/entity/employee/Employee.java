package com.frj.flyeducation.domain.entity.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    private Integer employeeId;
    private String employeeName;
    private String employeeUnionid;
    private String phone;
    private String department;
    private String duty;
}
