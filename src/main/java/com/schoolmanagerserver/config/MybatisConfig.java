package com.schoolmanagerserver.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean //pageHelper拦截器
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
