# spring-boot-actuator

### 本项目知识点
1. spring-boot actuator 监控服务
2. spring-boot actuator 的作用
3. spring-boot actuator 的可视化


### 认知
- spring-boot-actuator是什么？=》spring-boot项目的监控。还是通过它的作用、应用场景去理解
- spring-boot actuator的作用？=》
  - 1.提供http接口去查看服务的各种状态，如具体语义见官网/百度咯/翻译一下
  - 2.既然1提供了服务的状态查询，那微服务/spring-cloud 就需要actuator
- spring-boot actuator的可视化=》spring-boot-actuator 提供HTTP接口（也有远程Shell和JMX方式，但我不用就不理了）来监控，
数据有了，就差可视化界面了；有很多可视化界面；详见 http://itmuch.com/spring-boot/actuator-prometheus-grafana/  
spring-boot-actuator基础你懂吧！=》spring-boot-actuator与micrometer的关系你懂吧！=》micrometer与prometheus的关系你懂吧！
本项目启动后，就要去启动prometheus界面的项目了（另一个进程），docker拉下来





