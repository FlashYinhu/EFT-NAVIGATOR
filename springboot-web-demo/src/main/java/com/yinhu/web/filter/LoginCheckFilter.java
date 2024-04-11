package com.yinhu.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.yinhu.web.pojo.Result;
import com.yinhu.web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") // 对所有请求路径进行拦截
public class LoginCheckFilter implements Filter {

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//         获取请求路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();
        log.info("请求的url为: {}", url);
//        判断是否是登录请求 如果是登录请求则放行
        if(url.contains("login")) {
            log.info("登录操作 放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
//        获取请求头中的token
        String token = request.getHeader("token");
//        判断token是否存在 不存在则返回错误结果（未登录）
        if(!StringUtils.hasLength(token)) {
            log.info("请求头中token为空 返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            // 手将对象转换为json并返回
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return;
        }
//        解析token 如果解析失败则返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) { // 解析失败
            e.printStackTrace();
            log.info("解析令牌失败 返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            // 手将对象转换为json并返回
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return;
        }

        // 放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
