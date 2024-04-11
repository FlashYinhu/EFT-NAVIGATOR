package com.yinhu.web.config;

import com.yinhu.web.service.DeptService;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 配置类
public class CommonConfig {
    // 引入第三方依赖 将依赖中的对象交给IOC容器管理 jar包中的对象是只读的 无法用@Component及其衍生注解声明 解决办法
    // 声明第三方bean
    // 通过@Bean注解的name/value属性指定bean的名称
    @Bean(name = "saxxxx") // 将该方法的返回值交给IOC管理 一般不指定属性
          // 如果没有指定Bean的名称 默认是方法名 可以通过 name/value 属性指定bean的名称
    public SAXReader saxReader(DeptService deptService){ // 自动注入IOC中已有的bean对象
        System.out.println(deptService); // com.yinhu.web.service.impl.DeptServiceImpl@60f70249
        return new SAXReader();
    }
}
