package com.demo.lifecycle.config;


import com.demo.lifecycle.bean.Tiger;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
public class DiyConfig {

    /**
     * @Scope org.springframework.context.annotation.Scope
     * 看@Scope注释 value属性可以选
     *   prototype:多实例: IOC容器启动的时候,IOC容器启动并不会去调用方法创建对象, 而是每次获取的时候才会调用方法创建对象，可以在下面打个断点实验与singleton 进行对比
     * 	 singleton:单实例(默认):IOC容器启动的时候会调用方法创建对象并放到IOC容器中,以后每次获取的就是直接从容器中拿同一个bean
     * 	 request:主要针对web应用, 递交一次请求创建一个实例，request只能用在@Controller 上
     * 	 session:同一个session创建一个实例，session只能用在@Controller 上
     *
     * @Bean org.springframework.context.annotation.Bean
     * 看@Scope注释
     *   initMethod 表示bean初始化时调用指定方法
     *   destroyMethod 表示bean销毁时调用指定方法
     * */
    @Scope(value = "singleton")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Tiger tiger() {
        return new Tiger(new Random().nextInt(60));
    }

}
