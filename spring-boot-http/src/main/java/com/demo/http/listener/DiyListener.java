package com.demo.http.listener;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


/**
 * Servlet相关的监听器
 * ServletContextListener -- 监听servletContext对象的创建以及销毁
 *   contextInitialized(ServletContextEvent arg0)   -- 创建时执行
 *   contextDestroyed(ServletContextEvent arg0)  -- 销毁时执行
 *
 * HttpSessionListener  -- 监听session对象的创建以及销毁
 *   sessionCreated(HttpSessionEvent se)   -- 创建时执行
 *   sessionDestroyed(HttpSessionEvent se) -- 销毁时执行
 *
 * ServletRequestListener -- 监听request对象的创建以及销毁
 *   requestInitialized(ServletRequestEvent sre) -- 创建时执行
 *   requestDestroyed(ServletRequestEvent sre) -- 销毁时执行
 *
 * ServletContextAttributeListener  -- 监听servletContext对象中属性的改变
 *   attributeAdded(ServletContextAttributeEvent event) -- 添加属性时执行
 *   attributeReplaced(ServletContextAttributeEvent event) -- 修改属性时执行
 *   attributeRemoved(ServletContextAttributeEvent event) -- 删除属性时执行
 *
 * HttpSessionAttributeListener  --监听session对象中属性的改变
 *   attributeAdded(HttpSessionBindingEvent event) -- 添加属性时执行
 *   attributeReplaced(HttpSessionBindingEvent event) -- 修改属性时执行
 *   attributeRemoved(HttpSessionBindingEvent event) -- 删除属性时执行
 *
 * ServletRequestAttributeListener  --监听request对象中属性的改变
 *   attributeAdded(ServletRequestAttributeEvent srae) -- 添加属性时执行
 *   attributeReplaced(ServletRequestAttributeEvent srae) -- 修改属性时执行
 *   attributeRemoved(ServletRequestAttributeEvent srae) -- 删除属性时执行
 *
 * */

/**
 * 理解什么是监听器，就是监听某个事件，当某个事件发生时，回调对应的方法
 * 监听器是没有匹配url的，理解监听器的概念就知道为啥没有匹配url
 * */
@WebListener
public class DiyListener implements ServletRequestListener {

    /**
     * ServletRequest对象创建时调用，就是http请求时回调
     * 从ServletRequestEvent能得到什么，能做什么，自己看ServletRequestEvent的方法咯
     * */
    @Override
    public void requestInitialized (ServletRequestEvent sre) {
        System.out.println(Thread.currentThread() + "---->:requestInitialized");
    }

    /**
     * ServletRequest对象销毁时调用，就是http返回时回调
     * 从ServletRequestEvent能得到什么，能做什么，自己看ServletRequestEvent的方法咯
     * */
    @Override
    public void requestDestroyed (ServletRequestEvent sre) {
        System.out.println(Thread.currentThread() + "---->:requestDestroyed");
    }

}
