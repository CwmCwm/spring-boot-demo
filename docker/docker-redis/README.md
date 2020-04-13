# docker安装redis  

## 本项目知识点
1. redis是啥  
2. docker安装redis  
3. redis操作  
4. springboot连接redis  


## 内容
### 1. redis是啥  
百度，键值数据库/缓存  
键值缓存现在都用redis，不用memcached  
自行丰富/理解概念，有些概念不理解就实操，认知都要有个过程  



### 2. docker安装redis
docker有官方版本的redis    
官方操作文档 https://hub.docker.com/_/redis  
这里用4.0版本  
拉取镜像
``` docker pull redis:4.0 ```  
[](./README-RESOURCES/docker-redis1.jpg)


启动svn-server容器
``` docker run --name redis -v /container/redis/conf:/conf -p 6379:6379 --network diyNetwork -d redis:4.0  ```  
--name redis                                         设置容器name为redis，名字自己起，要有语义 
-d                                                   指定这个容器后台运行  
-v /container/redis/conf:/conf                       挂载宿主目录到容器目录  
[](./README-RESOURCES/docker-redis2.jpg)
进入redis容器内部看看  
``` docker exec -it redis bash  ```  
[](./README-RESOURCES/docker-redis3.jpg)
你能看到容器内的 /conf 目录，其实我就是用来放redis的配置文件的，这个后面自定义redis配置文件再说  
[](./README-RESOURCES/docker-redis5.jpg)


你看官网的使用文档  
[](./README-RESOURCES/docker-redis4.jpg)  
你仿照上图的第二种方式，目录太深了，我就换个目录挂载。redis的配置文件 redis.conf 看到了吗？    
停止删除容器，这里演示指定配置文件启动redis容器   
``` docker run --name redis -v /container/redis/conf:/conf -p 6379:6379 --network diyNetwork -d redis:4.0 redis-server /conf/redis.conf  ```  
redis-server /conf/redis.conf                        redis-server启动，其实就是放在镜像名后面，表示使用该命令，而不是Dockerfile中的 ENTRYPOINT 指定的命令
[](./README-RESOURCES/docker-redis6.jpg)  


接下来就是你了解配置项，知道各种场景下配置项如何配置，数据持久化场景，主从备份场景，集群场景  
场景也就是经验积累咯  


## 3. redis操作  
上面使用 redis-cli 客户端工具  
然后就是敲命令，这基本是所有服务端软件的学习套路了  
http://redisdoc.com  
基本上这是程序员的使用方式，很少有程序员使用图形工具学习，图形工具只是为了提供工作效率  





## 4. springboot连接redis  
看spring-boot-redis 项目



