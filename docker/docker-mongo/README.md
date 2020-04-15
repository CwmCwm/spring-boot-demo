# docker安装mongo  

## 本项目知识点
1. mongo是啥  
2. docker安装mongo  
3. mongo操作  
4. springboot连接mongo  


## 内容
### 1. mongo是啥  
百度mongo，文件数据库  
自行丰富/理解概念，有些概念不理解就实操，认知都要有个过程  



### 2. docker安装mongo
docker有官方版本的mongo  
官方操作文档 https://hub.docker.com/_/mongo
这里用4.2.1-bionic版本  
拉取镜像
``` docker pull mongo:4.2.1-bionic ```  
[](./README-RESOURCES/docker-mongo1.jpg)

  
你看官网的使用文档  
[](./README-RESOURCES/docker-mongo2.jpg)  
mongo的配置文件 mongod.conf 看到了吗  
这个镜像的持久化目录是 /data  
记住创建 /container/mongo/log/mongod.log 和 权限
记住修改 /container/mongo/data 权限
``` docker run --name mongo -v /container/mongo/conf:/conf -v /container/mongo/data:/data -v /container/mongo/log:/log -p 27017:27017 --network diyNetwork -d mongo:4.2.1-bionic --config /conf/mongod.conf  ```  
[](./README-RESOURCES/docker-mongo3.jpg)  


https://docs.mongodb.com/manual/reference/configuration-options/#use-the-configuration-file  
文档好长，只能耐心看，需要时再找  
接下来就是你了解配置项，知道各种场景下配置项如何配置，数据持久化场景，主从备份场景，集群场景  
场景也就是经验积累咯  


## 3. mongo操作  
使用 mongo 客户端连接mongodb  
https://docs.mongodb.com/manual/mongo/#start-the-mongo-shell-and-connect-to-mongodb   

mongodb的crud  
https://docs.mongodb.com/manual/crud/  




## 4. springboot连接mongo  
看spring-boot-mongo项目



