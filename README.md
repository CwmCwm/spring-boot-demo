# spring-boot-demo
springboot的demo，方便学习，回忆，工作

### 工具
- idea：提示和代码关联性强，如spring-boot的application.properties有哪些配置项，配置项对应的源码在哪里等等
- idea: 如果某些项目在idea中无法添加依赖，可以在命令行运行（idea界面有命令行） `mvn idea:module` 查看构建maven项目时有哪些错误，这样修改pom.xml后重建.iml文件
- idea: 设置.properties 文件编码格式为UTF-8，默认是gbk，这样会中文乱码
- maven：在idea中打开项目后，第一次如果要打包（mvn package），要先 mvn install（因为这是多模块项目）

### spring-boot思想
1. 约定大于配置（标准化，统一化的思想）
2. 组件化思想（有标准化的基础后，就可以组件化）

### 个人习惯
1. 代码尽量写注释，原因，心路历程；有总比没有好。
2. 知识点串联，比较，实践去验证知识点
3. 每个项目都README.md文档，作为了解项目的入口
4. 命名规则
  4.1 参考spring命名规则，如 configuration目录，WebMvcConfiguration配置类
  4.2 参考注解命名，@Controller 所以controller目录，xxxController类；@Service 所以service目录，xxxService类；@Repository 所以repository目录，xxxRepository类
5. 目录规则
  5.1 按技术点/技术功能分类，不按业务功能划分目录（业务功能按类命名来确定划分，所以起名字规则要定好）
  5.2 目录层级少（一般就主类一层，技术功能一层，两层完事），微服务/服务化的思想，不是单应用的思想
  

### 查看顺序
1. spring-boot-banner  
2. spring-boot-http  
3. spring-boot-https  
  3.1 spring-boot-https-server  
  3.2 spring-boot-https-client  
4. spring-boot-lifecycle  
5. spring-boot-annotation  
6. spring-boot-test  
7. spring-boot-log  
8. spring-boot-actuator  
9. docker
  9.1  docker安装mariadb  
  9.2  docker安装svn-server  
10. spring-boot-mybatis  
11. spring-boot-jdbc  
12. spring-boot-security  
13. spring-boot-schedule  
14. spring-boot-websocket


### 不使用的一些技术，个人看法，熟练度，思想喜好
1. 不使用jsp，freemarker，thymeleaf 这些模板引擎；采用方案直接前后端分离，JavaScript，Vue，Jquery。原因见 TODO  
2. 不使用jpa；采用方案mybatis，JDBC。原因见 TODO  
3. 关系型数据，不使用Oracle，DB2；采用方法mysql，mariaDB，posterSQL。原因见 TODO  



