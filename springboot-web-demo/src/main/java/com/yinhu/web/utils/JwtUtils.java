package com.yinhu.web.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.Map;

/**
 * JWT令牌工具类
 */
public final class JwtUtils {

    private static final String SING_KEY = "kangyinhu"; // 密钥为kangyinhu
    private static final long EXPIRE_TIME = 43200000L; // 过期时间为12H

    /**
     * 生成jwt密钥
     * @param claims 接收负载集合
     * @return
     */
    public static String generateJWT(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SING_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .compact();
          return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt 接收令牌
     * @return
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(SING_KEY)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}
