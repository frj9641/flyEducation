package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.subscriber.SubscriberRegistryBean;
import com.frj.flyeducation.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subscriber")
public class SubscriberController {
    @Autowired
    private SubscriberService subscriberService;

    /**
     * 自动上传更新小程序用户的信息
     * @param subscriberRegistryBean
     */
    @PostMapping
    public void createAppUser(SubscriberRegistryBean subscriberRegistryBean){
        subscriberService.createAppUser(subscriberRegistryBean);
    }
}
