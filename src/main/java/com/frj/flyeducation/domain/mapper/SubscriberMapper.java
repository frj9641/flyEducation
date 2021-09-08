package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.subscriber.Subscriber;
import com.frj.flyeducation.domain.entity.subscriber.SubscriberRegistryBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubscriberMapper {
    /**
     * 新增小程序用户
     * @param subscriberRegistryBean
     */
    void insertAppUser(SubscriberRegistryBean subscriberRegistryBean);

    /**
     * 更新公众号关注者的小程序相关字段
     * @param subscriberRegistryBean
     */
    void updateAppOpenidByUnionid(SubscriberRegistryBean subscriberRegistryBean);

    /**
     * 通过unionid查询用户的公众号openid
     * @param unionid
     * @return
     */
    String selectOfficalOpenidByUnionid(String unionid);

    /**
     * 通过unionid查询用户的小程序openid
     * @param unionid
     * @return
     */
    String selectAppOpenidByUnionid(String unionid);

    /**
     * 新增公众号用户
     * @param subscriber
     */
    void insertOfficialUser(Subscriber subscriber);

    /**
     * 更新小程序使用者的公众号相关字段
     * @param subscriber
     */
    void updateOfficialOpenidByUnionid(Subscriber subscriber);
}
