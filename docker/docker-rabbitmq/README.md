# docker安装rabbitmq  

## 本项目知识点
1. rabbitmq是啥  
2. docker安装rabbitmq    
3. springboot连接使用rabbitmq  


## 内容
### 1.rabbitmq是啥  
百度rabbitmq， 消息中间件  
自行丰富/理解概念，有些概念不理解就实操，认知都要有个过程  



### 2. docker安装rabbitmq
docker有官方版本的rabbitmq  
官方操作文档 https://hub.docker.com/_/rabbitmq
这里用  rabbitmq:3.7-management 版本  
拉取镜像
``` docker pull rabbitmq:3.7-management ```  
[](./README-RESOURCES/docker-rabbitmq1.jpg)

  
你看官网的使用文档  
[](./README-RESOURCES/docker-rabbitmq2.jpg)  
``` docker run --name rabbitmq -p 5672:5672 -p 15672:15672 --network diyNetwork -d rabbitmq:3.7-management   ```  
rabbitmq的端口  
4369 -- erlang发现口，不知道，不关心  
5672 -- client端通信口  
15672 -- 管理界面ui端口  
25672 -- server间内部通信口，那就是集群间的通信端口  


登录web管理页面  
[](./README-RESOURCES/docker-rabbitmq3.jpg)  
[](./README-RESOURCES/docker-rabbitmq4.jpg)  

[消息中间件和RabbitMQ.pdf](./README-RESOURCES/消息中间件和RabbitMQ.pdf)  



## 3. springboot连接使用rabbitmq   
见 spring-boot-rabbitmq 项目  




