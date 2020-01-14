# docker

### 本项目知识点
1. docker的应用场景
2. docker的概念
3. docker的安装
4. docker的使用


### 认知
1. docker的应用场景，要知道docker能干嘛？用在哪些地方？做同样的事有什么优势？不然干嘛用它

   1.1 标准化安装各种软件
   
   如安装mysql，不用docker是怎么做的？=》到官网下载对应操作系统的二进制压缩包/yum安装/源码安装（找来找去，安装步骤多，安装细节多）。
  
   使用docker安装=》两个命令  
   拉取镜像 `docker pull mysql:lastest`
   启动容器 `docker run --name mysql -d mysql:lastest`

   1.2 统一安装方式
   
   安装mysql两个命令，安装mongodb，nginx，redis，等等都是两个命令完事
   
   1.3 部署
   项目启动 a个mysql（数据库），b个redis（缓存），c个spring-boot（服务），d个nginx(负载均衡)，启动顺序mysql>redis>spring-boot>nginx
   手工启动麻烦耗时，用docker-compose 启动简单不出错省时
   
   1.4 安利完成
   
2. docker的概念

   2.1 学习新东西总是一堆概念，学习跟读书时没区别，先记一下概念（不理解很正常），后面动手实践（实践后回去理解了一些概念），再动手实践（再理解概念），反复多次完事
   
   2.2 docker的概念：用maven来类比
   镜像（各种包/spring-core.5.2.2.RELEASE/spring-context.5.2.2.RELEASE，各种容器/mysql:5.6/mongodb:4.0）
   容器（容器就是进程，有代码/镜像了，你要运行啊）
   仓库（maven仓库/maven本地仓库/maven私有仓库/maven公共仓库，docker仓库/docker本地仓库/docker私有仓库/docker公共仓库）
   命令就是围绕这三个概念
   
3. docker的安装
   3.1 要安装docker先要安装CentOS7，Windows10下用Windows10自带的Hyper-V虚拟机工具（不用安装其他虚拟机工具）安装CentOS7
   =》https://blog.csdn.net/c15625012146/article/details/86636439
   =》注意点：1.Hyper-V和CentOS7的网络配置（推荐使用双网卡配置，一个网卡连接Hyper-V默认的Default-Switch虚拟交换机，用于访问外网；
   一个网卡连接自己创建的虚拟交换机，用于Windows10主机和CentOS7的交互；原因IP地址变化的问题）。
   2.CentOS7添加定时任务去更新网络时间（原因时间同步嘛，不然会有些麻烦，如docker拉取镜像）
   
   3.2 安装好CentOS7后，配置好网络后，使用yum安装docker
   =》https://blog.csdn.net/c15625012146/article/details/86654641
   =》注意点
   
   3.3 安装好docker后，就
   



