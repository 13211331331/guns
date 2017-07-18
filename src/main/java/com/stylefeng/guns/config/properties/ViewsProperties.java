package com.stylefeng.guns.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author fengshuonan
 * @date 2017-05-24 20:37
 */
@Configuration
@PropertySource(value = {"classpath:/config/views.properties"},ignoreResourceNotFound = true,encoding = "utf-8")
public class ViewsProperties {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
