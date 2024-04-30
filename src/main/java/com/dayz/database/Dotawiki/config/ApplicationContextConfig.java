package com.dayz.database.Dotawiki.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath*:applicationContext.xml"})
public class ApplicationContextConfig {
    @Bean
    public ApplicationContext applicationContext() {
        return new org.springframework.context.support.ClassPathXmlApplicationContext();
    }
}
