package com.frj.flyeducation.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
用于restTemplate请求Access_token封装的bean
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccessToken {
    private String access_token;
    private String expires_in;
}
