package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.subscriber.Subscriber;
import com.frj.flyeducation.domain.entity.subscriber.SubscriberRegistryBean;
import com.frj.flyeducation.domain.mapper.SubscriberMapper;
import com.frj.flyeducation.service.SubscriberService;
import com.frj.flyeducation.util.AccessTokenUtil;
import com.frj.flyeducation.util.WeChatUtil;
import com.frj.flyeducation.util.XmlUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private static final String TOKEN="xxx";
    @Autowired
    private SubscriberMapper subscriberMapper;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 自动上传更新小程序用户的信息
     * @param subscriberRegistryBean
     */
    @Override
    public void createAppUser(SubscriberRegistryBean subscriberRegistryBean) {
        String openid = subscriberMapper.selectAppOpenidByUnionid(subscriberRegistryBean.getSubscriberUnionid());
        if (openid == null) {
//            没有注册过或者没有注册过小程序
            openid = subscriberMapper.selectOfficalOpenidByUnionid(subscriberRegistryBean.getSubscriberUnionid());
            if (openid == null) {
//                没有注册过
                subscriberMapper.insertAppUser(subscriberRegistryBean);
            } else {
//                没有注册过小程序
                subscriberMapper.updateAppOpenidByUnionid(subscriberRegistryBean);
            }
        }
    }

    /**
     * 微信验证token
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @Override
    public String checkToken(String signature, String timestamp, String nonce, String echostr) {
        //排序
        String[] arr = {TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //sha1Hex 加密
        MessageDigest md = null;
        String temp = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            temp = WeChatUtil.byteToStr(digest);
//            logger.info("加密后的token:"+temp);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if ((temp.toLowerCase()).equals(signature)) {
            return echostr;
        }
        return null;
    }

    /**
     * 监听公众号关注事件
     * @param req
     */
    @Override
    public void createOfficialUser(HttpServletRequest req) {
        Map<String, String> map = null;
        try {
            map = XmlUtil.xmlToMap(req);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        String openid = map.get("FromUserName");
        String eventType = map.get("Event");
        if (eventType.equals("subscribe")) {
            String accessToken = AccessTokenUtil.getAccessToken();
            ResponseEntity<String> res = restTemplate.getForEntity(WeChatUtil.USERINFO_URL + accessToken + "&openid=" + openid + "&lang=zh_CN", String.class);
            String unionid = WeChatUtil.res2str(res, "unionid");
            String nickname = WeChatUtil.res2str(res, "nickname");
            Subscriber subscriber = Subscriber.builder().subscriberOfficialOpenid(openid)
                    .subscriberUnionid(unionid).subscriberNickname(nickname).build();
            openid = subscriberMapper.selectOfficalOpenidByUnionid(unionid);
            if(openid==null){
                openid = subscriberMapper.selectAppOpenidByUnionid(unionid);
                if(openid==null){
                    subscriberMapper.insertOfficialUser(subscriber);
                }else{
                    subscriberMapper.updateOfficialOpenidByUnionid(subscriber);
                }
            }
        }
    }
}
