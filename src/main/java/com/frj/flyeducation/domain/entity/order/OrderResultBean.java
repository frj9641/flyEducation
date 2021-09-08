package com.frj.flyeducation.domain.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResultBean {
    private Integer orderId;
    private String studentName;
    private String phone;
    private String marketName;
    private String teacherName;
    private Integer isAdded;
    private Integer isFinished;
    private Integer isIntent;
    private Integer isSuccess;
    private String date;
}
