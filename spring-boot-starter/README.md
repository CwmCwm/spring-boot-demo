# spring-boot-starter

## 本项目知识点
1. 知识点补充  
2. 创建自定义的diy-spring-boot-starter 和 启动spring-boot-starter-main    
3. 原理，流程

## 内容
### 1. 知识点补充  
spring-boot-starter，就是你经常在pom中引入的各种 starter  
如   
```
<!-- spring-boot-starter-web 是spring-boot 启动web，这里默认使用tomcat服务 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- mybatis-spring-boot-starter 是mybatis官方提供的starter，就是提供自动注册mybatis组件。 因为spring-boot官方没有提供mybatis的starter -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>
```  
从上面两个示例，mybatis就是mybatis官方自定义的starter   

springboot的starter原理就是SPI，就是会加载某个目录下某个配置文件，并对其中的配置做解析  
SPI是一个概念（百度SPI），dubbo也有实现自己的SPI，tomcat也有SPI思想和实现      
这里说springboot的SPI实现，springboot会加载 classpath路径下所有的 META-INF/spring.factories文件（springboot规定好的，代码中写，别问为什么）  
这里说了 classpath路径下所有的META-INF/spring.factories文件 就是说会有很多个spring.factories文件，你自己看maven的引入包中各种starter相关包的目录结构  
看完maven的引入包中各种starter相关包的目录结构，会发现写法有些不一样          





### 2. 创建自定义的diy-spring-boot-starter 和 启动spring-boot-starter-main 
创建自定义的diy-spring-boot-starter（命名规范 xxx-spring-boot-starter， 你看人家mybatis的命名，你照着做就完事，虽然仅仅就是起个名字而已）
这个diy-spring-boot-starter 就是你/组织/公司自定义的starter  
在diy-spring-boot-starter 写代码DiyService, DiyServiceProperties, DiyServiceAutoConfiguration  ，写spring.factories  
然后maven install  （因为其他项目要引用diy-spring-boot-starter） 


spring-boot-starter-main 的pom引用了 diy-spring-boot-starter包  
然后 spring-boot-starter-main 写代码，直接注入DiyService （因为 diy-spring-boot-starter 中已经实例化DiyService并注入到springIOC容器了）  
启动 spring-boot-starter-main 测试一下，debug一下  



### 3. 原理，流程
spring-boot 启动时扫描所有 META-INF/spring.factories文件 （其实就多加这一步，SPI） 
然后实例化对应 BeanDefinition 
然后过滤这些 BeanDefinition  
然后根据 BeanDefinition 实例化对应Bean  






