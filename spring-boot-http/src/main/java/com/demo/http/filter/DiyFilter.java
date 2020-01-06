package com.demo.http.filter;

import com.demo.http.config.DiyWebMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;


/**
 * @WebFilter javax.servlet.annotation.WebFilter 表示这是个Filter的bean，需要添加@ServletComponentScan，这里写在HttpApplication上
 * @WebFilter 的属性配置看注解的注释
 * 因为Servlet容器都归spring管理了，所以可以注入spring容器中的bean
 * */
@WebFilter(filterName = "diyFilter", urlPatterns = "/diyFilterController/test1")
public class DiyFilter implements Filter {

    // 注入DiyWebMvcConfig
    @Autowired
    DiyWebMvcConfig diyWebMvcConfig;

    /**
     * Filter初始化时调用
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(Thread.currentThread() + "---->:init");
        System.out.println(Thread.currentThread() + "---->:" + diyWebMvcConfig);
    }

    /**
     * Filter做过滤时执行，这就是Filter的业务方法
     * 使用场景：感觉就是一些前置处理，拦截器HandlerInterceptor 也可以做，与HandlerInterceptor相比定位尴尬
     * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        System.out.println(Thread.currentThread() + "-------->:doFilter");
    }

    /**
     * Filter销毁调用
     * */
    @Override
    public void destroy() {
        System.out.println(Thread.currentThread() + "------------>:destroy");
    }

}
