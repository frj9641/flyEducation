package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.subscriber.SubscriberRegistryBean;

import javax.servlet.http.HttpServletRequest;

public interface SubscriberService {
    /**
     * 自动上传更新小程序用户的信息
     * @param subscriberRegistryBean
     */
    void createAppUser(SubscriberRegistryBean subscriberRegistryBean);

    /**
     * 微信验证token
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    String checkToken(String signature, String timestamp, String nonce, String echostr);

    /**
     * 监听公众号关注事件
     * @param req
     */
    void createOfficialUser(HttpServletRequest req);
}
