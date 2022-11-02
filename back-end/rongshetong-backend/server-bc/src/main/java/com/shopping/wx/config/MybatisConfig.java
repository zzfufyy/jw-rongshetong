package com.shopping.wx.config;

import com.shopping.wx.interceptor.AuditFieldInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ljy
 * @date 2022-03-11 11:06
 */
@Configuration
public class MybatisConfig {
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.addInterceptor(new AuditFieldInterceptor());
            }
        };
    }
}
