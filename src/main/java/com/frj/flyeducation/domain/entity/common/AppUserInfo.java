package com.frj.flyeducation.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUserInfo {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
