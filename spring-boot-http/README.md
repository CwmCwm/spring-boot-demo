# spring-boot-http

## 本项目知识点
1. 如何启动一个spring-boot http项目  
2. spring-boot http的目录结构  
3. springMVC的注解  
4. springMVC的重定向  
5. springMVC的统一异常处理器  
6. springMVC上传文件  
7. 多环境/多配置  
8. 国际化文件支持  
9. springMVC的HandlerInterceptor的使用  
10. Servlet的Filter的使用  
11. Listener的使用  
12. 前后端交互规范  
13. bean的多例模式，见

## 内容
### 1. 如何启动一个spring-boot http项目  
见pom.xml 引入依赖，启动运行 HTTPApplication.java  

### 2. spring-boot http的目录结构  
./src/main/resources/static  spring-boot web/http项目的静态资源目录，就是.html .css .js 图片等等  

### 3. springMVC的注解
见controller层的代码  

### 4. springMVC的重定向  
见controller层的代码  

### 5. springMVC的统一异常处理器  
springMVC的统一异常处理器就是Repository， Service， Component， Controller 抛异常，而又没有被处理，异常一直往外抛，
Controller也往外抛就由这个统一异常处理器来处理这个异常了  
见/configuration/GlobalExceptionHandler  
其中有些异常不是由统一异常处理器处理的，如404/NoHandlerFoundException，spring-boot-web 会将该请求转发到 /error  
见/controller/DiyErrorController 就可以自定义404如何返回了      


### 6. springMVC上传文件  
见/controller/UploadFileController 和 application.properties   

### 7. 多环境/多配置  
看实际工作和公司实际情况，一般下面几个够用了（开发环境->系统内部集成测试环境SIT->用户验收测试环境UAT->生产环境）   
开发环境：程序员的计算机（运行java程序），至于数据库mysql/缓存redis/网关nginx 等等一般用SIT的，也不会在程序员的计算机装这些啊  
系统内部集成测试环境/System Integration Testing SIT：开发人员，测试人员，项目人员测试的环境  
用户验收测试环境/User Acceptance Testing UAT：外包公司涉及到环境  
生产环境：就是生产环境啊  
实际工作根据公司情况和项目情况会有不同咯  

本项目就演示
SIT环境->UAT环境->生产环境  
基本用配置文件来控制，这里是 application-SIT.properties->application-UAT.properties->application.properties  
不用spring-boot profile 和 @Profile 这一套
原因：  
* application-SIT.properties和application-UAT.properties和application.properties完全互相对立
* 配置就一个文件，所有配置都写在一个文件，你的配置项能写多到难以搜索定位？层级不要太多，要素不要太多，真的找起来太麻烦/理解起来太累  

使用application-SIT.properties 见该文件，有写注释如何启动，其他同理  

### 8. 国际化文件支持  
国际化文件支持就是个文字/语言转换，见/controller/MessageSourceController 和 messages.properties, messages_en_US.properties, messages_zh_CN.properties  

### 9. springMVC的HandlerInterceptor的使用  
HandlerInterceptor 是SpringMVC拦截器（知道这个概念？）  
见/interceptor/DiyHandlerInterceptor 和/configuration/DiyWebMvcConfiguration  

### 10. Servlet的Filter的使用  
见/filter/DiyFilter

### 11. Listener的使用  
Listener监听器，这里演示Servlet的Listener，见/listener/DiyListener  
当然还有spring的监听器  
Listener监听器 和 Filter过滤器 都是概念，Servlet有定义javax.servlet.Filter接口和javax.servlet.Listener接口  
那其他框架也可以定义xxx.Filter接口和xxx.Listener接口  

### 12. 前后端交互规范  
* 数据交互格式：推荐使用前后端完全分离的写法，基本上前后端通过HTTP API接口交互，数据格式Content-Type:"application/json"，
所以不会用后端渲染HTML的技术（如freemarker/thymeleaf/jsp），至于前端使用什么技术（Vue, JQuery, React, Angular），后端不管咯。
思考前后端分离和后端渲染的优劣/百度一下也是可以，看看其他人的看法  

* 前后端数据字段定义：你看多第三方接口就知道，如微信开发文档，支付宝开发文档，百度地图开发文档，就知道了，看了一下，学习一下  
发现1：这些对外公共API都是字段命名都是全小写下划线分割，没有发现驼峰命名  
这里定义如下  
请求见接口的定义和示例  

返回统一格式如下  
``` json
{
    "errorCode": 0,
    "message": "success",
    "data": ...
}
```
| 字段名 | 类型 | 是否必填 | 最大长度 | 描述 | 示例值 |
| ---- | ---- | ---- | ---- | ---- | ---- |
| errorCode |  Integer/整数   | 是  | | 错误码，0表示成功，其他表示失败错误 | 0 |
| message   |  String/字符串  | 是  | | 返回消息，"success"与errorCode=0对应，其他错误这里有具体错误信息，方便提示/展示 | "success" |
| data      |  具体看接口     | 否  | | 该接口返回的业务数据 | 具体看接口 |

* url地址规范  
使用 ``` http://127.0.0.1:8080//springMVCAnnotationDemo1Controller/get?name=cwm&age=18``` 这种规则，可读性高  
不使用  ``` http://127.0.0.1:8080//springMVCAnnotationDemo1Controller/get/name/cwm/age/18``` 这种规则，可读性低  

* 请求方法，基本使用Restful风格  
GET请求 =》 select/查询  
POST请求 =》 insert/插入  
PUT请求 =》 update/更新  
DELETE请求 =》 delete/删除  
以上是针对资源的真正操作，而不是语义操作，如逻辑删除本质是更新，所以用PUT请求  



