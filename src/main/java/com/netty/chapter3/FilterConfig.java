package com.netty.chapter3;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/9 17:41
 * DESC:
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean newFilter1() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("MyFilter11");
        registrationBean.setFilter(new MyFilter());
        registrationBean.setOrder(100);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean newFilter2() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("MyFilter2");
        registrationBean.setFilter(new SecondFilter());
        registrationBean.setOrder(100);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));

        return registrationBean;
    }
}
