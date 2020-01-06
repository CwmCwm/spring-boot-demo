# spring-boot-http

### 本项目知识点
1. 如何启动一个spring-boot http项目
2. spring-boot http的目录结构
3. springMVC的注解
4. springMVC的重定向
5. springMVC的统一异常处理器
6. springMVC上传文件
7. 多环境/多配置
8. 国际化文件支持（）
9. springMVC的HandlerInterceptor的使用
10. Servlet的Filter的使用
11. Listener的使用

### 认知细节
- Listener/监听器的概念有些大，spring有自己的监听器（监听器接口），Servlet容器也有监听器（监听器接口）
- 前后端完全分离的写法，基本上前后端通过HTTP API接口交互，数据格式Content-Type:"application/json"，所以不会用后端渲染HTML的技术（如freemarker/thymeleaf/jsp）
- url的写法请规定一下，一般使用 `http://127.0.0.1:8080//springMVCAnnotationDemo1Controller/get?name=cwm&age=18`  
不要使用 `http://127.0.0.1:8080//springMVCAnnotationDemo1Controller/get/name/cwm/age/18` 这种写法啊

### spring-boot-http的目录（以maven项目目录为例）
- resources/static/ 目录是spring-boot http服务默认的静态资源目录（你可以在浏览器能访问哪些静态资源，url该怎么写）
- resources/application.properties 文件是spring-boot默认加载的配置文件（可以命令行指定别的文件）



