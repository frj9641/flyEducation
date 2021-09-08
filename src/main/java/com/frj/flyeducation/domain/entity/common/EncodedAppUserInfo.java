package com.frj.flyeducation.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EncodedAppUserInfo {
    private String encryptedData;
    private String iv;
    private String session_key;
}
