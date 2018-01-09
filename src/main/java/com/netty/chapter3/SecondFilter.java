package com.netty.chapter3;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/9 16:45
 * DESC:
 */
public class SecondFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("测试过滤器2---请求开始");
        chain.doFilter(request, response);
        System.out.println("测试过滤器2---请求结束");
    }

    @Override
    public void destroy() {

    }
}
