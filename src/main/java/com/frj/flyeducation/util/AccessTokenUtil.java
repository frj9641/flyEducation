package com.frj.flyeducation.util;

import com.frj.flyeducation.domain.entity.common.AccessToken;
import com.frj.flyeducation.util.restTemplateUtil.RestTemplateUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AccessTokenUtil {
    /**
     * 微信公众号获取access_token接口
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=xxx&secret=xxx";

    /**
     * 获取access_token
     * @return
     */
    public static String getAccessToken(){
        RestTemplate restTemplate= RestTemplateUtil.getInstance();
        ResponseEntity<AccessToken> forEntity = restTemplate.getForEntity(ACCESS_TOKEN_URL, AccessToken.class);
        String access_token = forEntity.getBody().getAccess_token();
        return access_token;
    }
}
