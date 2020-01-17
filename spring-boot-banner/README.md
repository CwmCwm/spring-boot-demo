# spring-boot-banner

## 本项目知识点
1. spring-boot启动时加载图标  
2. spring-boot的目录结构（maven）  

## 内容
### 1. spring-boot启动时加载图标  
使用场景：公司，项目图标/logo（品牌意识）  

### 2. spring-boot的目录结构（maven）  
[maven的知识图谱](https://www.cnblogs.com/daiwei1981/p/9338097.html)  
./src/main/java/  java源代码，就是各种.java文件  
./src/main/resources/  资源文件，各种资源，如配置， 图片， .html, .js, .css 等等，开发时用，至于生产发布资源文件可能根据公司公共资源去管  
./src/test/java/  就是对上面./src/main/java/ java代码的单元测试  
./src/test/resources/  单元测试用到的资源，其实就是为了与上面./src/main/resources/ 分开，实际我经常没有创建这个目录，因为没必要区分  
基本spring-boot项目目录结构就这四个目录  
  

