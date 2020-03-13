# docker安装svn-server

## 本项目知识点
1. svn是啥  
2. docker安装svn-server  
3. svn-server的高级操作  


## 内容
### 1. svn是啥  
百度，代码管理  
svn 和 git 的比较，百度，有故事的哦，了解git的故事  
| svn | git |  
|----|----| 
| 简单，所以学习和使用都很简单 | 难，所以学习和使用都很简单 | 
| 功能简单 | 功能复杂，命令真的多 |
| 因为简单，所以规范简单 | 功能复杂，所以很多种玩法，规范定好，不然不知道要怎么操作 |
| 适用小团队小项目，5人 | 适用大团队大项目 |
| 外包，接私活推荐，占用硬件资源少 | 推荐git，反正你要注册github账号，经常使用，常用命令和操作就记住了，现在公司都是用git | 




### 2. docker安装svn-server
docker没有官方版本的svn-server镜像  
所以这里选择garethflowers/svn-server 这个镜像，简单完事，网上教程
参考文章 https://blog.csdn.net/xu12387/article/details/93050196    

拉取镜像
``` docker pull garethflowers/svn-server:1.3 ```  
[](./README-RESOURCES/docker-svn-server1.jpg)


启动svn-server容器
``` docker run --privileged=true --restart always --name svn-server -v /container/svn-server:/var/opt/svn -p 3690:3690 --network diyNetwork -d garethflowers/svn-server:1.3  ```
--privileged=true                                    授予容器管理员权限，预防创建svn创库时提示权限不足    
--restart always                                     设置容器随宿主机开机自启  
--name svn-server                                    设置容器name为svn-server，名字语义 
-d                                                   指定这个容器后台运行  
-v /container/svn-server:/var/opt/svn                挂载宿主目录到容器目录  
[](./README-RESOURCES/docker-svn-server2.jpg)


进入容器，要用svn的命令创建svn库，各种配置  
``` docker exec -it svn-server /bin/sh ```  
[](./README-RESOURCES/docker-svn-server3.jpg)
在容器内的 /var/opt/svn 创建svn库，你项目名称是什么svn库的名称就是什么，这里项目名称是 repo 
查看 repo 
README.txt  conf        db          format      hooks       locks   
就是svn库的目录，也就关心 conf目录
[](./README-RESOURCES/docker-svn-server4.jpg)
authz              是配置权限控制，可以设置哪些用户可以访问哪些目录
passwd             是配置用户和密码的
svnserve.conf      是配置svn相关的操作

(1)配置passwd文件
[](./README-RESOURCES/docker-svn-server5.jpg)
在[users]这个节点下我们增加了账户为 cwm 且密码为 123 ，注意#后面内容是注释掉的，不用管。且注意等号两边有要有空格  

(2)配置authz
直接配置账号的权限，不引入角色的概念，反正人不多采用svn  
[](./README-RESOURCES/docker-svn-server6.jpg)
这里配置账号 cwm 拥有 repo库的读写权限
[/] 代表所有仓库， [repo:/] 代表用户在repo 仓库的所有目录有相应权限，根据自己需求设计

(3)编辑svnserve.conf，找到 [general] 节点，加入以下内容
[](./README-RESOURCES/docker-svn-server7.jpg)
|----|----|
| anon-access | 控制非鉴权用户访问版本库的权限。取值范围为"write"、"read"和"none"。即"write"为可读可写，"read"为只读，"none"表示无访问权限。缺省值：read |
| auth-access | 控制鉴权用户访问版本库的权限。取值范围为"write"、"read"和"none"。即"write"为可读可写，"read"为只读，"none"表示无访问权限。缺省值：write |
| password-db | 指定用户名口令文件名。除非指定绝对路径，否则文件位置为相对conf目录的相对路径。缺省值：passwd |
| authz-db  | 指定权限配置文件名，通过该文件可以实现以路径为基础的访问控制。除非指定绝对路径，否则文件位置为相对conf目录的相对路径。缺省值：authz |
| realm | 指定版本库的认证域，即在登录时提示的认证域名称。若两个版本库的 认证域相同，建议使用相同的用户名口令数据文件。缺省值：一个UUID(Universal Unique IDentifier，全局唯一标示)。 |

退出容器，重启容器
[](./README-RESOURCES/docker-svn-server8.jpg)



svn-server搭建好了  
那本地下载svn客户端  
下载地址  https://tortoisesvn.net/downloads.html 
[](./README-RESOURCES/docker-svn-client1.jpg)
[](./README-RESOURCES/docker-svn-client2.jpg)
[](./README-RESOURCES/docker-svn-client3.jpg)
[](./README-RESOURCES/docker-svn-client4.jpg)
[](./README-RESOURCES/docker-svn-client5.jpg)
[](./README-RESOURCES/docker-svn-client6.jpg)
[](./README-RESOURCES/docker-svn-client7.jpg)
[](./README-RESOURCES/docker-svn-client8.jpg)

svn客户端的操作基本就这些，因为svn简单，遇到问题百度就能解决  



## 3. svn-server的高级操作  
就是备份之类的，算了，就svn的使用场景也没什么必要    



