package com.yinhu.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.yinhu.web.pojo.Result;
import com.yinhu.web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    /**
     * 目标资源方法运行前运行 返回true放行 返回flase不放行
     * 返回true放行 返回false不放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURL().toString();
        log.info("请求路径为: {}",requestURL);
        log.info("preHandle.....请求准备通过拦截器");
//        System.out.println("preHandle.....请求准备通过拦截器");
        if (requestURL.contains("login")) {
            return true; // 包含login 登录界面 无需校验JWT 直接放行
        }

        String jwtStr = request.getHeader("token"); // 获取请求头中taken字段的值

        // 校验jwtStr的合法性
        if(jwtStr == null || jwtStr.isEmpty()) {
            log.info("请求头中的token为空，返回未登录错误信息");
            Result result = Result.error("NOT_LOGIN");
            // 手动对象 -> JSON的转换
            String noLogin = JSON.toJSONString(result);
            response.getWriter().write(noLogin);
            return false;
        }
        try {
            JwtUtils.parseJWT(jwtStr);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result result = Result.error("NOT_LOGIN");
            // 手动对象 -> JSON的转换
            String noLogin = JSON.toJSONString(result);
            response.getWriter().write(noLogin);
            return false;
        }

        log.info("令牌有效 允许通过拦截器");
        return true;
    }

    /**
     *  目标方法运行过后运行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle.....请求成功通过拦截器");
//        System.out.println("postHandle.....请求成功通过拦截器");
    }

    /**
     * 视图渲染完毕后运行 最后运行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterComplention.....请求响应完成 视图渲染完毕");
        log.info("");
        log.info("");
//        System.out.println("afterComplention.....请求响应完成 视图渲染完毕");
    }
}
