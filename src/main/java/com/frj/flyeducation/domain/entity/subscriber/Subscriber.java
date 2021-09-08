package com.frj.flyeducation.domain.entity.subscriber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Subscriber {
    private Integer subscriberId;
    private String subscriberOfficialOpenid;
    private String subscriberAppOpenid;
    private String subscriberUnionid;
    private String subscriberNickname;
}
