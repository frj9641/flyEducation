package com.frj.flyeducation.util.restTemplateUtil;

import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {
    public static RestTemplate getInstance() {
    	RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
