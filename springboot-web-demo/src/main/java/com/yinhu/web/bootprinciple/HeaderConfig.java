package com.yinhu.web.bootprinciple;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {

    @Bean
    @ConditionalOnClass(name = "io.jsonwebtoken.Jwts") // 环境中存在指定的这个类才会将该Bean加入到IOC容器当中
    public HeaderParser headerParser(){
        return new HeaderParser();
    }

    @Bean
//    @ConditionalOnMissingBean // 不存在该类型的Bean才会将该Bean加入IOC容器中
    // 判断配置文件中有对应的属性和值 才注册Bean到IOC容器当中去
    // 常用于第三方
    @ConditionalOnProperty(name = "name", havingValue = "kangyinhu")
    public HeaderGenerator headerGenerator(){
        return new HeaderGenerator();
    }
}
