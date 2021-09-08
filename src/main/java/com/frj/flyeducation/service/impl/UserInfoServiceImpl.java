package com.frj.flyeducation.service.impl;

import com.alibaba.fastjson.JSON;
import com.frj.flyeducation.domain.entity.common.AppUserInfo;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.common.EncodedAppUserInfo;
import com.frj.flyeducation.domain.entity.employee.Employee;
import com.frj.flyeducation.domain.mapper.CustomerMapper;
import com.frj.flyeducation.domain.mapper.EmployeeMapper;
import com.frj.flyeducation.service.UserInfoService;
import com.frj.flyeducation.util.MiniAESUtil;
import com.frj.flyeducation.util.restTemplateUtil.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CustomerMapper customerMapper;
    /**
     * 微信小程序登录凭证接口
     */
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=xxx&secret=xxx&js_code=";
    private static final String USER_INFO_TAIL = "&grant_type=authorization_code";

    /**
     * 通过前端传入的jsCode返回进入小程序用户的信息
     * @param jsCode
     * @return
     */
    @Override
    public CommonResult queryUserInfoByJsCode(String jsCode) {
        RestTemplate instance = RestTemplateUtil.getInstance();
        ResponseEntity<AppUserInfo> forEntity = instance.getForEntity(USER_INFO_URL + jsCode + USER_INFO_TAIL, AppUserInfo.class);
        String unionid = forEntity.getBody().getUnionid();
        String duty=employeeMapper.selectDutyByEmployeeUnionid(unionid);
        if(duty==null){
            duty="0";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("info", forEntity.getBody());
        map.put("duty",duty);
        CommonResult result = CommonResult.builder().code(200).msg("获取成功").ext(map).build();
        return result;
    }

    /**
     * 解密获取小程序用户的信息
     * @param encodedAppUserInfo
     * @return
     */
    @Override
    public CommonResult decodeAppUserInfoByEncodedAppUserInfo(EncodedAppUserInfo encodedAppUserInfo) {
        String encryptedData = encodedAppUserInfo.getEncryptedData();
        String iv = encodedAppUserInfo.getIv();
        String session_key = encodedAppUserInfo.getSession_key();
        String json = MiniAESUtil.getUserInfo(encryptedData, session_key, iv);
        Map<String, Object> resMap = JSON.parseObject(json);
        String openid = (String) resMap.get("openId");
        String unionid = (String) resMap.get("unionId");
        AppUserInfo appUserInfo = AppUserInfo.builder().openid(openid).unionid(unionid).build();
        Map<String, Object> map = new HashMap<>();
        map.put("info", appUserInfo);
        CommonResult result = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
        return result;
    }
}
