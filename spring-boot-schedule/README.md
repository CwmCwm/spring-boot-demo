# spring-boot-schedule


## 本项目知识点
1. spring-schedule是什么  
2. 准备条件  
3. 



## 内容 
### 1. spring-schedule是什么  
定时任务日常开发中是很常见的，如生日定时发送消息，优惠券快到期提醒，花呗还款提醒    
spring-schedule 是一款java的定时任务框架  
参考：https://www.cnblogs.com/skychenjiajun/p/9057379.html?utm_source=tuicool&utm_medium=referral  
文章比较了几个java的定时任务框架  
本项目使用 spring-schedule，至于quartz框架我就没写，因为spring-schedule 能满足我的日常开发，看看有哪些场景quartz框架能支持而spring-schedule不能支持  


### 2. 准备条件  
MySQL/MariaDB数据库，之前docker 安装过了
数据库脚本见/resources/sql/ 目录  
这里要定时任务持久化，那定时任务的数据就保存到数据库  








