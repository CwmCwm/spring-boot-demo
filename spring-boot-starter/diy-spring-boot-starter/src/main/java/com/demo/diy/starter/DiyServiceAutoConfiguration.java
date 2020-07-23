package com.demo.diy.starter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 这个就是配置类了，命名和写法 可以参考 mybatis-spring-boot-starter 的写法
 配置类什么作用不用多说了

 @Configuration 标记当前类是配置类。没啥好说
 @EnableConfigurationProperties(DiyServiceProperties.class)  @EnableConfigurationProperties 加载applicant.properties相应配置信息的类
 @ConditionalOnClass(DiyService.class)  当存在DiyService.class时，此配置类DiyServiceAutoConfiguration才生效
 @ConditionalOnProperty(prefix = "diy", value = "enable", matchIfMissing = true)  如果applicant.properties配置diy.enable=false，此配置类DiyServiceAutoConfiguration不生效

 各种@ConditionalXXX 注解，百度和了解语义，这个很简单
 还可以自定义实现org.springframework.context.annotation.Condition接口，实现自定义的判断规则

 所以写法也就这样
 DiyService  是功能实现类
 DiyServiceProperties  是配置读取和默认配置
 DiyServiceAutoConfiguration  是是否启用该组件，是否实例化该组件需要的Bean
 其实写法和经常用springboot构建项目一样，只是这里是组件，使用SPI想想，所以多加一个 META-INF/spring.factories文件

 * */


@Configuration
@EnableConfigurationProperties(DiyServiceProperties.class)
@ConditionalOnClass(DiyService.class)
@ConditionalOnProperty(prefix = "diy", value = "enable", matchIfMissing = true)
public class DiyServiceAutoConfiguration {

    @Autowired
    private DiyServiceProperties diyServiceProperties;

    /**
     @Bean  没啥好说
     @ConditionalOnMissingBean(DiyService.class)  存在DiyService.class时，才实例化DiyService的实例

     这里就是自定义让spring实例化哪些Bean
     */
    @Bean
    @ConditionalOnMissingBean(DiyService.class)
    public DiyService diyService() {
        DiyService diyService = new DiyService();
        diyService.setName(diyServiceProperties.getName());
        diyService.setAge(diyServiceProperties.getAge());
        return diyService;
    }



}
