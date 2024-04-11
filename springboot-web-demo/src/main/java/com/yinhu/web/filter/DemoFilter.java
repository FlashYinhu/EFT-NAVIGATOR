package com.yinhu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 注意 Filter 拦截链的顺序是按照类名进行先后顺序排布
 * 比如 ABCFilter 就会优先于DemoFilter对文件进行拦截
 */
//@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class DemoFilter implements Filter {
    @Override // 初始化方法 只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter 初始化");
    }

    @Override // 每一次拦截之后都会调用
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求 放行前的逻辑");
        // 放行访问对应的资源
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("已经放行 放行后的逻辑");
    }

    @Override // 销毁方法 只调用一次
    public void destroy() {
        System.out.println("filter 关闭");
    }
}
