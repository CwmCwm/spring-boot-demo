# docker使用规范
以下是我自己的定义的规范，写出来自己看，自己遵守，不要太随意  
你可以自己定义规范，然后不断优化规范（这也是积累，方法论）  

## 本项目知识点
1. docker的安装目录    
2. docker的容器目录规划  
3. docker的网络规划  
4. docker的其他规范  


## 内容
### 1. docker的安装目录  
Linux上程序的安装目录和Windows上不一样  
Windows上程序安装后，该程序的所有文件（配置文件、二进制）都在同一个目录  
Linux上程序安装后，按照[Linux的目录规划](https://www.cnblogs.com/pcideas/articles/4982661.html)，程序的相关文件会分散在各个目录  
所以还是熟悉一下Linux的规划    
CentOS7 yum安装docker后，查看docker的安装目录
[](./README-RESOURCES/docker1.jpg)  
官方的daemon.json配置：https://docs.docker.com/engine/reference/commandline/dockerd/#options  

[](./README-RESOURCES/docker3.jpg)  

基本就这样，够用了，你要添加docker远程仓库就配置下daemon.json文件  
启动docker， 关闭docker， 重启docker  


### 2. docker的容器目录规划  
启动容器，如果想自定义配置文件，想将容器中的持久化数据存储起来等等怎么办？  
如启动mariadb容器后，想要mariadb容器使用宿主机的本地的配置文件（因为配置文件就放在宿主机上啊）；
想要mariadb容器的数据持久化后，不会随容器删除而删除（那肯定持久化到宿主机啊）  
所以就要把容器中对应目录挂载到本地目录    
具体示例见docker-maraidb  

/container/  目录用于存放所有容器  
/container/mariadb/  目录用于存放mariadb容器，容器名是啥，这里目录名就是啥  
/container/mariadb/conf/  目录用于挂载容器中的配置目录  
/container/mariadb/data/  目录用于挂载容器中的持久化数据目录  
/container/mariadb/xxx/   根据需要创建对应的挂载目录  
其他容器同理，先起好容器名，docker命令写好就完事了，对应目录docker命令写好后就会自动创建  
然后就是看对应容器中相关目录是什么，挂载上去就行了  


### 3. docker的网络规划  
[Docker网络详解——原理篇](https://blog.csdn.net/meltsnow/article/details/94490994)  
安装docker后，它会自动创建三个网络，bridge（创建容器默认连接到此网络）、 none 、host  
| 网络模式 | 简介 |  
| --- | --- |  
| host | 容器将不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口。也不常用 |  
| bridge | 此模式会为每一个容器分配、设置IP等，并将容器连接到一个docker0虚拟网桥，通过docker0网桥以及Iptables nat表配置与宿主机通信。经常使用 |  
| none | 该模式关闭了容器的网络功能，我还没有遇到过对应的使用场景 |  
[](./README-RESOURCES/docker4.jpg)  
[](./README-RESOURCES/docker5.jpg)  

一般将相关几个容器放在同一网络中，如写个商城项目，要mariadb数据库，springboot应用，redis缓存  
那么创建一个网络，三个应用都放入这个网络中，这个三个应用就可互相通信了，也好理解和管理    

经常用的就是创建bridge模式的网络，一般学习和工作都创建一个网络，然后容器都在这个网络中  
不推荐使用docker自带的命名为bridge的bridge模式的网络，因为这个网络没有DNS功能，同个网络中的应用通信就麻烦。所以推荐自己创建一个bridge模式网络      
[](./README-RESOURCES/docker6.jpg)  
[](./README-RESOURCES/docker7.jpg)  
[](./README-RESOURCES/docker8.jpg)  



### 4. docker的其他规范 
TODO






