package com.demo.lifecycle.config;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;


/**
 * org.springframework.context.ApplicationListener ApplicationContext事件机制是观察者设计模式的实现，通过ApplicationEvent类和ApplicationListener接口，可以实现ApplicationContext事件处理
 * 其中spring有一些内置的事件，当完成某种操作时会发出某些事件动作。看ApplicationEvent的实现类就知道有哪些事件
 * 同样事件可以自定义（继承ApplicationEvent类）、监听也可以自定义（实现ApplicationListener接口），完全根据自己的业务逻辑来处理。
 *
 * 关于事件的语义/生命周期，真要使用才去看吧
 */
@Component
public class DiyApplicationListener implements ApplicationListener<ApplicationContextEvent> {

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        System.out.println(DiyApplicationListener.class.getName() + ".onApplicationEvent(ApplicationContextEvent event) => 在对应事件触发节点被回调");
    }

}
