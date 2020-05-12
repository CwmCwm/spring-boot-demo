# spring-boot-cache


## 本项目知识点
1. spring-boot-cache作用/使用场景   
2. spring-boot 默认使用的cache


## 内容
### 1. spring-boot-cache作用/使用场景   
理解缓存在计算机系统中的意义   
如从mysql数据库中查找到数据后，将数据缓存到缓存中，下次就从缓存中获取数据，不用在查数据库  


### 2. spring-boot 默认使用的cache  
示例1，pom文件只添加   
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```
不使用任何nosql和java提供的缓存组件（如Ehcache，Caffine 等等），默认就使用 ConcurrentMap 来缓存数据，ConcurrentMap本来就是JDK自带的类，根本无需第三方包  



示例2，pom文件添加   
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```
添加redis依赖，并且添加cache依赖，配置好redis后，spring-boot会默认使用redis作为缓存，  
这里redis也是用spring-boot默认注册的RedisTemplate，用redis-cli命令行看了存入到redis中的数据，默认的RedisTemplate和cache使用redis的String类型，  
value是将java对象使用JDK序列化后存入的，使用默认的请记住spring-boot的规则并记下来，
如 @Cacheable(cacheNames = "redis", key = "'user' + #userId")  存入redis后，缓存在redis中使用的数据类型（String, Hash, List, Set, ZSet）,  
redis中key的命名规则是啥，redis中value的数据序列化规则  



示例3，pom文件添加，pom文件跟示例2一样   
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```
跟示例2区别是，控制使用什么缓存，本项目代码采用示例3为最终代码，毕竟是通用写法    
https://blog.csdn.net/qq_26440803/article/details/90145543  
https://blog.csdn.net/justry_deng/article/details/89283664  
思路就是：
1.自己实例化对应的CacheManager，如这里的 RedisCacheManager, CurrentMapCacheManager  
2.缓存注解上 @Cacheable, @CachePut, @CacheEvict, @Caching  指定cacheManager属性值  
思路感觉就像多数据源配置（mysql）







