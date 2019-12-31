# spring-boot-demo
springboot的demo，方便学习，回忆，工作

### 工具
- idea：提示和代码关联性强，如spring-boot的application.properties有哪些配置项，配置项对应的源码在哪里等等
- idea: 如果某些项目在idea中无法添加依赖，可以在命令行运行（idea界面有命令行） `mvn idea:module` 查看构建maven项目时有哪些错误，这样修改pom.xml后重建.iml文件
- maven：在idea中打开项目后，第一次如果要打包（mvn package），要先 mvn install（因为这是多模块项目）

### spring-boot思想
1. 约定大于配置（标准化，统一化的思想）

### 个人习惯
1. 代码尽量写注释，原因，心路历程；有总比没有好。
2. 知识点串联，比较，实践去验证知识点

### 查看顺序
1. spring-boot-banner
2. spring-boot-http
3. spring-boot-https
 3.1 spring-boot-https-server
 3.2 spring-boot-https-client



