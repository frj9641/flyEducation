package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.common.EncodedAppUserInfo;
import com.frj.flyeducation.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 通过前端传入的jsCode返回进入小程序用户的信息
     * @param jsCode
     * @return
     */
    @GetMapping
    public CommonResult queryUserInfoByJsCode(@RequestParam("jsCode") String jsCode){
        return userInfoService.queryUserInfoByJsCode(jsCode);
    }

    /**
     * 解密获取小程序用户的信息
     * @param encodedAppUserInfo
     * @return
     */
    @PostMapping
    public CommonResult decodeAppUserInfoByEncodedAppUserInfo(EncodedAppUserInfo encodedAppUserInfo){
        return userInfoService.decodeAppUserInfoByEncodedAppUserInfo(encodedAppUserInfo);
    }
}
