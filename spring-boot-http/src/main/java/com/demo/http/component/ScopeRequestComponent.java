package com.demo.http.component;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 @Scope(value = WebApplicationContext.SCOPE_REQUEST) 这个是需要web应用的，就是Servlet，这里就是Tomcat
 每次http会创建一个 ScopeRequestComponent 实例，并缓存入 HttpServletRequest中，就是调用HttpServletRequest.setAttribute方法

 使用场景：同一个http请求共享同一个 Bean ，但其实 Bean 一般都是定义为线程安全的（即没有可变的属性，没有属性只有方法），
 如果只是作为同一个http线程的共享数据，那 HttpServletRequest.setAttribute 本来就是做这些事，一般把数据直接放入 HttpServletRequest中，
 干嘛要实例化一个 Bean 来放入，所以感觉用处不大
 * */
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Component
public class ScopeRequestComponent {

}
