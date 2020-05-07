package com.demo.http.component;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



/**
 scope 是作用域的意思
 @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) 单例模式，spring会缓存到BeanFactory.singletonObjects 缓存中

 然而实际上都用 SCOPE_SINGLETON 单例模式，想想其他的scope的使用场景
 * */
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class ScopeSingletonComponent {

}
