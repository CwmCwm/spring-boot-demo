package com.demo.http.component;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) 多例模式，spring不会缓存，每次getBean方法时都会进行实例化

 使用场景：没想到
 * */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class ScopePrototypeComponent {

}
