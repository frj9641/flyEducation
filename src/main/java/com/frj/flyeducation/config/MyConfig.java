package com.frj.flyeducation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Value("${posterUrl}")
    private String posterUrl;
    @Value("${photoUrl}")
    private String photoUrl;
    @Value("${scoreUrl}")
    private String scoreUrl;
    /**
     * 映射主机文件夹至虚拟路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/poster/**","/photo/**","/score/**").addResourceLocations("file:"+posterUrl,"file:"+photoUrl,"file:"+scoreUrl);
    }

    /**
     * restTemplate
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
