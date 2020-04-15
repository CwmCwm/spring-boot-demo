# spring-boot-redis

## 本项目知识点
1. spring-boot连接redis  
2. spring-boot 和 redis 的规范定义  
3. 关于redis自身

## 内容
### 1. spring-boot连接redis  
1.docker安装启动redis容器  
2.见pom.xml 引入依赖，见RedisTest，自己测试一下  


### 2. spring-boot 和 redis 的规范定义  
前置条件：了解并在redis-cli上操作过 string, hash, list, set, zset 类型存储，就是多打命令，这样看RedisTemplate的方法才能联想到方法的作用  
http://redisdoc.com    
通过实践后理解 redis 的各种数据类型和使用场景  
规范大多数就是 java 和 redis 的数据类型的映射关系，具体就是序列化和反序列的具体实现，命名规范，规范自行积累    


### 3. 关于redis自身
这是关于redis自身的事了，面试会问到，都是理论的事  
理论这东西，看了忘，你又不是工具的发明者，贡献者，就了解过几遍，有个印象就好，这样遇到问题才好解决问题  
      



