package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.common.EncodedAppUserInfo;

public interface UserInfoService {
    /**
     * 通过前端传入的jsCode返回进入小程序用户的信息
     * @param jsCode
     * @return
     */
    CommonResult queryUserInfoByJsCode(String jsCode);

    /**
     * 解密获取小程序用户的信息
     * @param encodedAppUserInfo
     * @return
     */
    CommonResult decodeAppUserInfoByEncodedAppUserInfo(EncodedAppUserInfo encodedAppUserInfo);
}
