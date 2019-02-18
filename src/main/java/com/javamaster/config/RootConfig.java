package com.javamaster.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "com.javamaster.controller"}, excludeFilters={
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
@Import({SecurityConfig.class, WebSocketConfig.class})
public class RootConfig {
    //Import({SecurityConfig.class, WebSocketConfig.class, HibernateConfig.class})

}
