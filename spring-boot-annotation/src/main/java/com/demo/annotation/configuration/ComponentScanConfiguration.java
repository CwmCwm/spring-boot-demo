package com.demo.annotation.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ComponentScan org.springframework.context.annotation.ComponentScan 为spring添加其他扫描路径，
 * 应用场景：如果是公司公共组件级别，你按spring-boot的starter规范去写组件啊；项目级别（感觉不上不下），在spring-boot默认扫描路径多写个配置类就完事了
 *
 * 个人习惯的写法， com.demo.common.Human 只是定义类，不加任何注解，配置类这里用 @Bean 进行实例化注册
 * 原因：多个项目可以公用同一个类，至于本项目你想怎么用，本项目自己决定
 * */
@ComponentScan(basePackages = "com.demo.common")
@Configuration
public class ComponentScanConfiguration {
}
