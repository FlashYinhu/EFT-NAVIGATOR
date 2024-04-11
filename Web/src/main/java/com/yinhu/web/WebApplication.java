package com.yinhu.web;

import com.yinhu.web.bootprinciple.EnableHeaderConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *  SpringbootApplication 启动类进行包扫描只会扫描当前包及其子包
 *  可以加上注解 @ComponentScan("com.xxx", "com.xxx", "com.xxx")指定包扫描
 */

//@ComponentScan({"com.example", "com.yinhu.web"}) // 使用繁琐 性能低下
//@Import({TokenParser.class}) // 导入普通类 交给IOC管理
//@Import({HeaderConfig.class}) // 导入配置类
//@Import({MyImportSelector.class}) // 导入ImportSelector接口的实现类

@EnableHeaderConfig // @EnableXxxx注解 封装@Import注解
@SpringBootApplication
@ServletComponentScan // 开启对javaweb / servelet组件的支持
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
