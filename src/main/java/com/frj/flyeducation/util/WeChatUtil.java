package com.frj.flyeducation.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class WeChatUtil {
    /**
     * 获取公众号订阅者信息接口
     */
    public static final String USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";

    /**
     * 请求返回的json数据转字符串
     * @param responseEntity
     * @param key
     * @return
     */
    public static String res2str(ResponseEntity responseEntity, String key) {
        String resStr = responseEntity.getBody().toString();
        Map resMap = JSON.parseObject(resStr);
        return (String) resMap.get(key);
    }

    /**
     * 加解密方法
     * @param byteArray
     * @return
     */
    public static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 加解密方法
     * @param mByte
     * @return
     */
    public static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}
