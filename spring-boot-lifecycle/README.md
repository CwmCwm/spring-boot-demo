# spring-boot-lifecycle

## 本项目知识点
1. @Bean， @Scope 的使用  
2. org.springframework.beans.factory.FactoryBean接口的使用  
3. org.springframework.context.ApplicationContextInitializer接口的使用  
4. org.springframework.context.ApplicationListener接口的使用  
5. org.springframework.beans.factory.Aware接口的使用  
6. org.springframework.beans.factory.config.BeanPostProcessor接口的使用  
7. org.springframework.beans.factory.SmartInitializingSingleton接口的使用  
8. spring-boot生命周期，其实就是spring容器的生命周期，你看过spring源码就好说，这里画visio图，自己可以跟着debug打断点跟一下  
10. org.springframework.boot.SpringApplicationRunListener接口的使用  
11. org.springframework.boot.ApplicationRunner接口的使用  
12. org.springframework.boot.CommandLineRunner接口的使用  
13. spring在各个生命周期节点提供哪些接口给你扩展  
14. SpringIOC流程和springAOP流程
15. springboot内置tomcat容器和服务启动过程  


## 内容
### 其他见代码，看类名就知道了

### 14. SpringIOC主流程和springAOP流程
spring中bean这个概念就是对象，bean从springIOC流程功能上可以分为两种  
第一种是业务bean（就是我们自己经常写@Controller, @Service, @Repository, @Component, @Bean）  
第二种是处理第一种业务bean的bean，就是实现BeanPostProcessor接口的bean，所以BeanPostProcessor接口是关键  

springIOC流程图  
[spring-boot和spring的IOC核心组件和流程](./README-RESOURCES/spring-boot和spring的IOC核心组件和流程.vsdx)  

springAOP流程图  
[spring-boot和spring的AOP核心组件和流程](./README-RESOURCES/spring-boot和spring的AOP核心组件和流程.vsdx)  



### 15. springboot内置tomcat容器和服务启动过程   
14. SpringIOC主流程和springAOP流程   说了spring的主流程，这里来说tomcat（Servlet容器）的流程和规范  
这样前后衔接上，就可以从main方法开始，启动Servlet容器，启动spring容器，完成网络服务的启动   


https://blog.csdn.net/justloveyou_/article/details/60964714  

概念1：Web服务，什么是Web服务？ =》 Web服务是一种统称，实际是网络服务，如 http服务器是一种Web服务，ftp服务器是一种Web服务，smtp服务器也是一种Web服务，它们都在网络上对外提供某种服务    

概念2：Java Web编程 =》 就是Java提供网络编程的（其他语言C，C++等等），Java网络编程大家一开始都学到Socket编程，后面才学Servlet编程，Socket和Servlet有什么关系？  
Socket就是IP+端口，这足够提供Web服务了；Servlet是Java官方对Web服务开发的规范，大家开发Web服务就按照Servlet规范做；  
那使用Java不写Servlet，同样可以开发Web服务吗？=》当然可以了，Socket编程才是实现IP+端口，但是官方和大家开发Web服务都使用Servlet规范，你遵守想干嘛？    
      
概念3：Servlet容器，从实现角度说就是 Socket编程 + Servlet规范，  
如Tomcat（一种Servlet容器的具体实现）就是一个守护进程（一直监听IP+端口有没有请求/人来访问啊）  
和 请求来了，我该把请求交给哪个Servlet啊  
启动Servlet容器就是启动 main方法  

概念4：Servlet，注意 `Servlet容器` 和 `Servlet` 是两个不同的概念。  
你写Servlet的时候有写main方法吗？=》没有  
Servlet编程就是写一个个实现 javax.servlet.Servlet接口的Servlet类，运行时需要有线程去实例化这些Servlet类为实例，然后分派请求/工作给这些Servlet实例。  
你看 Servlet容器 就是那个实例化Servlet类和分派请求给Servlet实例的  
至于你Servlet写的多复杂就是你的事了  

概念5：Servlet规范，就看 https://blog.csdn.net/justloveyou_/article/details/60964714  ，文章详细说明了Servlet的生命周期，Servlet容器的加载流程，  
这些都是规范，别问为什么，官方定的规则      

上面说的这些是 `Web服务`, `Servlet容器`, `Servlet`, `Servlet规范`  理论的事，实践的话，你大学有学Java编程都写过这些代码    
你经历过 web.xml 和 springmvc.xml 的配置时代？？   



见包 Maven:org.springframework:spring-web:5.2.2.RELEASE spring-web5.2.2.RELEASE.jar/META-INF/service/javax.servlet.ServletContainerInitializer  
见类 org.springframework.web.SpringServletContainerInitializer   
见引入的接口 @HandlesTypes(WebApplicationInitializer.class)  ->  WebApplicationInitializer接口  
见WebApplicationInitializer接口的实现 -> org.springframework.web.context.AbstractContextLoaderInitializer 和 org.springframework.web.servlet.support.AbstractDispatcherServletInitializer         









