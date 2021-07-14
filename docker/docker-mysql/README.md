# docker安装mysql  

## 本项目知识点
1. mysql是啥  
2. docker安装mysql  
3. mysql操作  
4. springboot连接mysql  


## 内容
### 1. mysql是啥  
百度mysql  
自行丰富/理解概念，有些概念不理解就实操，认知都要有个过程  



### 2. docker安装mysql
docker有官方版本的mysql   
https://hub.docker.com/_/mysql
这里用5.7版本  
拉取镜像
``` docker pull mongo:5.7 ```  
[](./README-RESOURCES/docker-mysql1.png)

看如何使用该mysql镜像，下面挂载目录你看了就知道    
``` docker run --name mysql -v /container/mysql/conf:/etc/mysql/conf.d -v /container/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=Huoshitou@0521 -p 3306:3306 --network diyNetwork -d mysql:5.7  ```  
[](./README-RESOURCES/docker-mongo3.jpg)  




## 3. mysql操作  





## 4. springboot连接mysql  
看spring-boot-mybatis项目   



