package com.demo.http.component;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


/**
 @Scope(value = WebApplicationContext.SCOPE_SESSION) 这个是需要web应用的，就是Servlet，这里就是Tomcat
 同一个session的第一次http请求会创建 ScopeSessionComponent 实例，并缓存入session中，就是 HttpSession.setAttribute方法
 之后同一个session的第n次http请求会共用这个 ScopeSessionComponent 实例

 使用场景：同一个session请求共享同一个 Bean ，同SCOPE_REQUEST 的感想一样，所以感觉用处不大
 * */
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Component
public class ScopeSessionComponent {

}
