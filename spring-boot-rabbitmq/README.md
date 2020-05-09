# spring-boot-rabbitmq

## 本项目知识点
1. 知识点补充  
2. docker安装启动rabbitmq
3. 启动spring-boot-rabbitmq-producer
4. 启动spring-boot-rabbitmq-consumer

## 内容
### 1. 知识点补充  
消息中间件和RabbitMQ，这个pdf很全面  
[消息中间件和RabbitMQ](./README-RESOURCES/消息中间件和RabbitMQ.pdf)  

这个 spring-boot-rabbitmq 项目的架构图，详解写在图上了  
[](./README-RESOURCES/spring-boot-rabbitmq.jpg)  



### 2. docker安装启动rabbitmq  
详见 /docker/docker-rabbitmq/ 目录  
这里就给个启动 rabbitmq容器的命令  
``` docker run --name rabbitmq -p 5672:5672 -p 15672:15672 --network diyNetwork -d rabbitmq:3.7-management ```  

这里场景就是完成下单后，向用户发送短信  
spring-boot-rabbitmq-producer 干的活：完成下单，向rabbitmq发送消息（消息就是给用户发送短信）  
spring-boot-rabbitmq-consumer 干的活：从rabbitmq获取消息，执行给用户发送短信  
rabbitmq 就是消息的搬运工  






### 3. 启动spring-boot-rabbitmq-producer
启动spring-boot-rabbitmq-producer后  
[](./README-RESOURCES/spring-boot-rabbitmq-producer1.jpg)  
[](./README-RESOURCES/spring-boot-rabbitmq-producer2.jpg)    




### 4. 启动spring-boot-rabbitmq-consumer
启动spring-boot-rabbitmq-consumer后  
[](./README-RESOURCES/spring-boot-rabbitmq-consumer1.jpg)  
[](./README-RESOURCES/spring-boot-rabbitmq-consumer2.jpg)  






