# docker安装mariadb

## 本项目知识点
1. mariadb是啥  
2. docker安装maraidb  



## 内容
### 1. mariadb是啥  
百度看故事去咯  


### 2. docker安装maraidb  
这里使用  mariadb:10.3  这个版本镜像   
[](./README-RESOURCES/docker-mariadb1.jpg)
[](./README-RESOURCES/docker-mariadb2.jpg)
[](./README-RESOURCES/docker-mariadb3.jpg)
[](./README-RESOURCES/docker-mariadb4.jpg)
[](./README-RESOURCES/docker-mariadb5.jpg)
[](./README-RESOURCES/docker-mariadb6.jpg)
[](./README-RESOURCES/docker-mariadb7.jpg)

看看这个mariadb镜像的配置文件  
[](./README-RESOURCES/docker-mariadb8.jpg)

下面是启动mariadb容器的脚本  
```shell script
docker run --name mariadb -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mariadb:10.3
```
[](./README-RESOURCES/docker-mariadb9.jpg)
[](./README-RESOURCES/docker-mariadb10.jpg)


ok，既然要挂载指定目录，那就指定挂载目录呗  
```shell script
docker run --name mariadb -e MYSQL_ROOT_PASSWORD=root -v /container/mariadb/conf:/etc/mysql/conf.d -v /container/mariadb/data:/var/lib/mysql --network diyNetwork -p 3306:3306 -d mariadb:10.3
```
[](./README-RESOURCES/docker-mariadb11.jpg)
[](./README-RESOURCES/docker-mariadb12.jpg)


[](./README-RESOURCES/docker-mariadb13.jpg)
[](./README-RESOURCES/docker-mariadb14.jpg)
[](./README-RESOURCES/docker-mariadb15.jpg)

基本就是这样啦
其他镜像也是这样操作的，看镜像使用页面，自己验证一下，然后根据你自己的规范去创建容器


