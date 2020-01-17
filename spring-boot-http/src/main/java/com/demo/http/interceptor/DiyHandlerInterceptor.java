package com.demo.http.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerInterceptor 是springMVC的拦截器
 * 注册为bean，然后再springMVC那边配置使用，这里见DiyWebMvcConfig的配置
 * 拦截器的使用场景：你都拿到HttpServletRequest 和 HttpServletResponse 了，通用/公用的场景
 * 打个断点就知道执行顺序了
 */
@Component
public class DiyHandlerInterceptor implements HandlerInterceptor {

    /**
     * 前置处理，就是在Controller方法前执行preHandle方法
     * 应用场景：可以提前做一些判断，如参数校验/登录状态校验（通用/公用），不合法的直接返回（直接写入输出流OutStream）
     * */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(Thread.currentThread() + "---->:DiyHandlerInterceptor.preHandle");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        return true;
    }

    /**
     * 后置处理，就是在Controller方法后执行postHandle方法
     * 注意点 1.后置处理设置返回头是不生效的  2.试一下HttpServletResponse的方法，基本没用
     * 应用场景：所以感觉这里最多就打个日志
     * */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println(Thread.currentThread() + "-------->:DiyHandlerInterceptor.执行postHandle");
        // 再后置处理上写response的header没有生效
        response.setHeader("postHandle", "postHandle");
        response.setStatus(404);
    }

    /**
     * 请求处理完成后
     * 应用场景：所以感觉这里最多就打个日志
     * */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println(Thread.currentThread() + "------------>:DiyHandlerInterceptor.afterCompletion");
    }

}
