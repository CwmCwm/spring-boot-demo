# spring-boot-lifecycle

### 本项目知识点
1. @Bean， @Scope 的使用
2. org.springframework.beans.factory.FactoryBean接口的使用
3. org.springframework.context.ApplicationContextInitializer接口的使用
4. org.springframework.context.ApplicationListener接口的使用
5. org.springframework.beans.factory.Aware接口的使用
6. org.springframework.beans.factory.config.BeanPostProcessor接口的使用
7. org.springframework.beans.factory.SmartInitializingSingleton接口的使用
8. spring-boot生命周期，其实就是spring容器的生命周期，你看过spring源码就好说，这里画visio图，自己可以跟着debug打断点跟一下
10. org.springframework.boot.SpringApplicationRunListener接口的使用
11. org.springframework.boot.ApplicationRunner接口的使用
12. org.springframework.boot.CommandLineRunner接口的使用
13. spring在各个生命周期节点提供哪些接口给你扩展

### 认知细化
- spring中bean这个概念就是对象，bean从springIOC流程功能上可以分为两种，
第一种是业务bean（就是我们自己经常写@Controller, @Service, @Repository, @Component, @Bean）
第二种是处理第一种业务bean的bean，就是实现BeanPostProcessor接口的bean，所以BeanPostProcessor接口是关键

### springIOC流程图
[spring-boot和spring的IOC核心组件和流程](./README-RESOURCES/spring-boot和spring的IOC核心组件和流程.vsdx)


### springAOP流程图
[spring-boot和spring的AOP核心组件和流程](./README-RESOURCES/spring-boot和spring的AOP核心组件和流程.vsdx)


