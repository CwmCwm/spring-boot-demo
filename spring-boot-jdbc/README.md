# spring-boot-jdbc


## 本项目知识点
1. 环境搭建  
2. 数据库和程序规范  
3. spring的事务


## 内容 
### 1. 环境搭建
docker启动 mariadb:10.3 容器，数据库就搭建完成  
前面spring-boot-mybatis项目已经搭建了数据库环境   
建表sql脚本见spring-boot-mybatis项目


### 2. 数据库和程序规范  
见spring-boot-mybatis项目的数据库和程序规范  

比较 springJDBC 和 mybatis  
|  | springJDBC | mybatis |  
|----|----|----|
| sql语句 | 都是写sql语句，会sql就轻松上手 | 都是写sql语句，会sql就轻松上手 |
| sql拼接 | 自己手动拼接，有点麻烦 | 看一下几个标签，拼接方便，轻松上手 |
| 表和记录映射 | 都可以使用Map和Entity | 都可以使用Map和Entity |

基本上两个工具使用起来没啥不同，会sql都轻松上手  


### 3. spring的事务
spring的事务，说到底就是数据库事务，这里就是@Transactional



