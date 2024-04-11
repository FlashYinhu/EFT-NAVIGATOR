package com.yinhu.web;

import com.google.gson.Gson;
import com.yinhu.web.bootprinciple.HeaderGenerator;
import com.yinhu.web.pojo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpringAutoConfig {

    @Autowired // springboot自动配置
    private Gson gson;
    @Test
    public void testJson(){
        String json = gson.toJson(Result.success());
        System.out.println(json);
    }

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void testThirdPartyBeanInject(){
//        System.out.println(applicationContext.getBean(TokenParser.class));
//        System.out.println(applicationContext.getBean(HeaderParser.class));
        System.out.println(applicationContext.getBean(HeaderGenerator.class));
    }

}
