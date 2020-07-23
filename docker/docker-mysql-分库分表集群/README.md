# docker-mysql-分库分表集群

## 本项目知识点
1. 前提，mysql 和 docker
2. 一些mysql的集群方案和利弊
3. 两主两从的数据库架构




## 内容
### 1. 前提，mysql 和 docker
docker只要你经常使用，就足够了，因为这里就是将mysql的分库分表集群，使用docker只是方便部署mysql    
mysql使用（应该没问图），mysql的原理（理论实践，我是看 丁奇的《mysql实战45讲》，你随意，反正你要知道binlog，事务的实现原理，也不用多深入，知道概念术语作用  ）   
所以，mysql要深入一些     





### 2. 一些mysql的集群方案和利弊  
画图展示了一些mysql的集群方案和利弊，一张图片写上了所有信息，不用翻来翻去    
下面方案是有顺序的，从简单到复杂（其实复杂就是融合几个简单的，使复杂的方案同时具备几个简单的优点，尽量避免缺点）   
重要提示：没有最好的架构，只有合适的架构，选择方案要以实际情场景为基础，选择合适的        
至于实际场景是什么？你就要自己衡量了，比如人才，用户规模，行业，公司流程。。。  ，哪些是你要考虑的，哪些不是你考虑的      
 
[一主多从](./README-RESOURCES/一主多从--1Master-nSalve.vsdx)  
[两主](./README-RESOURCES/两主--2Master.vsdx)  
[两主多从](./README-RESOURCES/两主多从--2Master-nSlave.vsdx)  





### 3. 两主两从的数据库架构
[两主两从架构图](./README-RESOURCES/两主两从--2Master-2Slave.vsdx)    

#### docker创建Master1, Master2, Slave1, Slave2
```
docker run --name Master1 --privileged=true -p 3311:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -e MYSQL_USER=replication -e MYSQL_PASSWORD=replication -v /container/Master1/conf:/etc/mysql/conf.d -v /container/Master1/data/:/var/lib/mysql -v /container/Master1/logs/:/var/log/mysql --network diyNetwork -d mysql:5.7
docker run --name Master2 --privileged=true -p 3312:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -e MYSQL_USER=replication -e MYSQL_PASSWORD=replication -v /container/Master2/conf:/etc/mysql/conf.d -v /container/Master2/data/:/var/lib/mysql -v /container/Master2/logs/:/var/log/mysql --network diyNetwork -d mysql:5.7
docker run --name Slave1 --privileged=true -p 3321:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -v /container/Slave1/conf:/etc/mysql/conf.d -v /container/Slave1/data/:/var/lib/mysql -v /container/Slave1/logs/:/var/log/mysql --network diyNetwork -d mysql:5.7
docker run --name Slave2 --privileged=true -p 3322:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -v /container/Slave2/conf:/etc/mysql/conf.d -v /container/Slave2/data/:/var/lib/mysql -v /container/Slave2/logs/:/var/log/mysql --network diyNetwork -d mysql:5.7
```
这里Master1和Master2 都创建了replication用户，是用于主从复制时的用户，当然，你直接用root也行（实际项目能不用root就不用root，懂得都懂）
我的目录规划和网络规划      
[](./README-RESOURCES/两主两从-目录规划.jpg)  
[](./README-RESOURCES/两主两从-网络规划.jpg)  

#### centos7配置防火墙，一般自己实验都是将centos7的防火墙直接关闭，我就没有关闭防火墙，所以要配置
```
firewall-cmd --add-port=3311/tcp --permanent
firewall-cmd --add-port=3312/tcp --permanent
firewall-cmd --add-port=3321/tcp --permanent
firewall-cmd --add-port=3322/tcp --permanent
firewall-cmd --reload
```


#### 配置文件和重启容器    
这里准备好了四个配置文件，分别是my-Master1.cnf, my-Master2.cnf, my-Slave1.cnf, my-Slave2.cnf  当然命名只是为了区分，上传到对应目录还是要改文件名为 my.cnf      
Master1的配置文件  my-Master1.cnf  
[my-Master1.cnf](./my-Master1.cnf)    

Master2的配置文件  my-Master2.cnf  
[my-Master2.cnf](./my-Master2.cnf)   

Slave1的配置文件  my-Slave1.cnf  
[my-Slave1.cnf](./my-Slave1.cnf)  

Slave2的配置文件  my-Slave2.cnf  
[my-Slave2.cnf](./my-Slave2.cnf)  

上传配置文件后，重命名文件为my.cnf，配置完后重启容器  
[](./README-RESOURCES/两主两从-配置和重启容器.jpg) 



#### 连接各个mysql，进行主从复制的配置  
连接Master1，配置权限，因为我创建的用户是replication，所以就给replication赋予权限    
```
GRANT REPLICATION SLAVE,FILE,REPLICATION CLIENT ON *.* TO 'replication'@'%' IDENTIFIED BY 'replication';
FLUSH PRIVILEGES;
```
[](./README-RESOURCES/两主两从-主从复制1.jpg)   

同理连接Master2，配置权限，同样执行上面一模一样的sql语句

在Master1上执行，查看Master1当前的日志文件和位置，因为Master2要复制时要这些信息
``` show master status; ```
[](./README-RESOURCES/两主两从-主从复制2.jpg)  
在Master2上执行，因为Master2要复制Master1，所以要指定此时的Master1的日志文件和位置
``` change master to master_host='172.18.0.2',master_port=3306,master_user='replication',master_password='replication',master_log_file='mysql-bin.000001',master_log_pos=622; ```
[](./README-RESOURCES/两主两从-主从复制3.jpg)  
在Master2上开启复制线程，就是 IO线程和SQL线程  
``` start slave; ```
[](./README-RESOURCES/两主两从-主从复制4.jpg)  
查看Master1 和 Master2 上的线程状态  
``` show processlist; ```
[](./README-RESOURCES/两主两从-主从复制5.jpg)  
[](./README-RESOURCES/两主两从-主从复制6.jpg)  
这样完成了 Master2（从） 复制Master1（主）   

接下来反过来做 Master1（从）复制Master2（主）的配置，就完成了Master1和Master2的互主    
SQL语句跟上面一样，该哪些值你自己懂得   
配置后，查看线程状态验证一下  
  
接下来做 Slave1（从）复制Master2（主）的配置  和 Slave2（从）复制Master2（主）的配置   
SQL语句跟上面一样，该哪些值你自己懂得   
配置后，查看线程状态验证一下  

ok，看完线程状态没有问题  
[](./README-RESOURCES/两主两从-主从复制7.jpg)  

在Master1上创建表
```
CREATE TABLE `user` (
  `userId` bigint(20) unsigned NOT NULL COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
```
[](./README-RESOURCES/两主两从-主从复制8.jpg)  

增删改   
[](./README-RESOURCES/两主两从-主从复制9.jpg)  




总结，
以上的复制模式是 异步复制，（半同步复制要启动插件，也就是多执行几个sql语句），（还有mysql cluster复制模式）   
架构直接看架构图   
















