# spring-boot-log
spring-boot 日志

### 本项目知识点
1. spring-boot默认使用的日志门面/接口slf4j
2. spring-boot默认使用的日志组件logback


### 认知细节
- 日志门面/接口是什么概念？=》就是接口，定义了接口，然后啥都没实现
- 日志组件是什么概念？=》实现日志门面/接口，真正干事的，日志门面和日志组件联系好理解，百度一下，多认识些日志门面和日志组件就明白了
- 看maven依赖包，spring-boot默认使用的日志门面/接口slf4j，spring-boot默认使用的日志组件logback
- 为啥要分日志门面和日志组件？=》面向接口编程，面向接口编程的好处
- slf4j的日志级别有5个，由低到高，trace->debug->info->warn->error
- 一般日志通过AOP来统一实现日志记录，就不用在每个bean中写日志输出代码






