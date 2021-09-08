package com.frj.flyeducation.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommonResult {
    private Integer code;
    private String msg;
    private Map<String,Object> ext;
}
