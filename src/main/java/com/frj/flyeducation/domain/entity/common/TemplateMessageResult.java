package com.frj.flyeducation.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TemplateMessageResult {
    private String errcode;
    private String errmsg;
    private String msgid;
}
