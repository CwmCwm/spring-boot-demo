package com.demo.annotation.configuration;

import com.demo.common.Tiger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Conditional org.springframework.context.annotation.Conditional
 * 应用场景：根据项目运行的具体环境，实例化对应bean, 使用对应配置
 * 实际使用倒不是很大（操作系统基本是Linux，有什么是特殊又是全局影响的吗），开发时使用（我偏向修改application.properties）
 *
 *
 * 一大堆 @Conditional 开头的注解，spring-boot自动配置中大量用到，看名字注释，实验就知道作用了，感觉主要用于看源码/写公用组件
 * @ConditionalOnBean
 * @ConditionalOnMissingBean
 *
 * */
@Configuration
public class ConditionalConfiguration {

    @Conditional(value = LinuxCondition.class)
    @Bean
    public Tiger linuxTiger() {
        return new Tiger("linuxTiger", 18);
    }

    @Conditional(value = WindowsCondition.class)
    @Bean
    public Tiger windowsTiger() {
        return new Tiger("windowsTiger", 18);
    }

}
