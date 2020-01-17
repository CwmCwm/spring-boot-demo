# spring-boot-actuator

## 本项目知识点
1. spring-boot-actuator的应用场景
2. spring-boot-actuator的可视化


## 内容
### 1. spring-boot-actuator的应用场景
spring-boot项目的监控，对外暴露spring-boot项目的信息，提供HTTP接口（也有远程Shell和JMX方式，但我不用就不理了），如以下
1. 浏览器能看到spring容器有哪些bean  
2. 浏览器能查询spring-boot项目的健康状态（什么叫健康？）  
3. 微服务/spring-cloud需要用到（想想为什么？）  
4. 等等  
具体还是通过项目演示，写代码，应用场景去理解，如何扩展spring-boot-actuator  

### 2. spring-boot-actuator的可视化
spring-boot-actuator提供HTTP接口给外部查询项目信息，数据有了，为了数据可视化，加个可视化界面咯；  
有很多可视化界面；详见 http://itmuch.com/spring-boot/actuator-prometheus-grafana/  



