package com.dayz.database.Dotawiki.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan(basePackages = {"com.dayz.database.Dotawiki.repository"})
public class ApplicationConfig {

    @Bean
    public ApplicationContext applicationContext() {
        return new ClassPathXmlApplicationContext();
    }

}
