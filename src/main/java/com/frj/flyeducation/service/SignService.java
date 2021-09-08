package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.sign.Sign;

public interface SignService {
    /**
     * 提交签到记录
     * @param sign
     */
    void createSign(Sign sign);
}
