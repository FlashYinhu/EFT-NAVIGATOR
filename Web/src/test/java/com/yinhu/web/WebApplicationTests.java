package com.yinhu.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest // 加载springboottest上下文
class WebApplicationTests {

    @Test
    /**
     * 生成JWT令牌
     */
    public void generateJWT() {
        // 自定义载荷
        Map<String, Object> testData = new HashMap<>();
        testData.put("id", 1);
        testData.put("name", "kangyinhu");
        testData.put("age", 24);
        testData.put("gender", '男');

        // 生成JWT令牌
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "kangyinhu") // 签名算法
                .setClaims(testData) // 载荷
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }

    @Test
    /**
     * 解析JWT令牌
     */
    public void parseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("kangyinhu")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJnZW5kZXIiOiLnlLciLCJuYW1lIjoia2FuZ3lpbmh1IiwiaWQiOjEsImV4cCI6MTcxMjcxMzkxMCwiYWdlIjoyNH0.dO6WA7vedcG8o17GAX4xst8ictmtcbmSr23ReqWRWUM")
                .getBody();// 拿到自定义的内容
        System.out.println(claims);
        /**
         * {gender=男, name=kangyinhu, id=1, exp=1712546245, age=24}
         * 其中exp为过期时间
         */
    }


    @Autowired
    private SAXReader saxReader;

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void testSAXReaderInjection() {
        // Object getBean = applicationContext.getBean("saxReader"); 不指定bean的名称 默认是方法名
        Object getBean = applicationContext.getBean("saxxxx"); // 成功拿到org.dom4j.io.SAXReader@62b3a2f6
        System.out.println(getBean);
    }
}
