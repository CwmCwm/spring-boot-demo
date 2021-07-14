# Linux-CentOS7命令    
这里记录Linux-CentOS7命令（根据我自己的实践的常用命令）    
这里是给自己看的，不会详细介绍概念   




## 公共
//立即关机
`shutdown -h now`     

下面是更换yum源，更新系统软件包，安装docker等一系列命令    
//因为最小化安装CentOS，所以没有wget，那就安装啊    
`yum install wget`    
//将默认的/etc/yum.repos.d/CentOS-Base.repo换个名字/etc/yum.repos.d/CentOS-Base.repo_default，备份嘛   
`mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_default`   
//下载阿里云的CentOS7的yum源，国内源快，还有163等其他国内源   
`wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo`   
//下载阿里云的yum源后，要更新一下系统的yum源（用新的yum源啦）     
`yum makecache`    
//更新系统（更新软件包，依赖包），不填 -y 会一直提示你是否确认（你就要输入y）     
`yum -y update`     
//因为最小化安装CentOS，那就安装啊，都是一些工具/命令    
`yum install -y yum-utils device-mapper-persistent-data lvm2`     
//yum中添加docker源    
`yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo`    
//查看docker版本   
`yum list docker-ce --showduplicates`    
//我这里使用docker-ce-18.06.3.ce-3.el7这一版本     
`yum install -y docker-ce-18.06.3.ce-3.el7`    

//查看系统版本      
`cat /etc/redhat-release `      

//查看内核版本      
`cat /proc/version`    
`uname -a`    

//查看系统是32位或者64位     
`file /bin/ls`     







## 文件/文件夹操作  
//创建 /container 目录
`mkdir /container`

//将/etc/yum.repos.d/CentOS-Base.repo复制到/etc/yum.repos.d/CentOS-Base.repo_default  
`mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_default`

//将/etc/yum.repos.d/CentOS-Base.repo移动到/etc/yum.repos.d/CentOS-Base.repo_default
`mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_default`

//强制删除/index.html
`rm -f /index.html`





## 进程
//ps是查看进程，|是管道，grep是过滤，上面就是查看进程中有那些进程启动命令包含了java字符串，大概就是java进程   
`ps -ef | grep java`   
     



## 防火墙
//查询有哪些端口是开启的
`firewall-cmd --list-port`  

//防火墙开放80端口，永久生效
`firewall-cmd --zone=public --add-port=80/tcp --permanent`   
      





## 操作系统信息   
//top命令经常用来监控linux的系统状况，比如cpu、内存的使用,按 q 退出     
`top`   






















