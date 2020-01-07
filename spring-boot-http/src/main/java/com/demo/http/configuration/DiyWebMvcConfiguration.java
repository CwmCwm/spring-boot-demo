package com.demo.http.configuration;

import com.demo.http.interceptor.DiyHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter 被标记为过时@Deprecated，那该类就不继承WebMvcConfigurerAdapter
 * 反正继承WebMvcConfigurerAdapter 也只是用WebMvcConfigurerAdapter的一些默认实现（看了源码，WebMvcConfigurerAdapter也没有什么默认实现啊）
 * 这里就直接实现 org.springframework.web.servlet.config.annotation.WebMvcConfigurer接口
 *
 * 看WebMvcConfigurer接口注释，理解方法语义
 * */
@Configuration
public class DiyWebMvcConfiguration implements WebMvcConfigurer {

    // 注入拦截器Interceptor，下面就要往springMVC配置拦截器了
    @Autowired
    DiyHandlerInterceptor diyHandlerInterceptor;

    /**
     * InterceptorRegistry 就是拦截器Interceptor的注册中心，你有拦截器，你要注册到springMVC吧
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将diyHandlerInterceptor注册到springMVC，并配置该拦截器匹配的url
        registry.addInterceptor(diyHandlerInterceptor).addPathPatterns("/diyHandlerInterceptorController/test1");
    }

}
