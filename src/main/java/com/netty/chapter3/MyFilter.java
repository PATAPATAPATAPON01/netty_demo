package com.netty.chapter3;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/9 16:45
 * DESC:
 */
public class MyFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("测试过滤器1---请求开始");

        response.setCharacterEncoding("utf-8");
        response.getOutputStream().write("123456789".getBytes("utf-8"));
//        chain.doFilter(request, response);
        System.out.println("测试过滤器1---请求结束");
    }

    @Override
    public void destroy() {

    }
}
