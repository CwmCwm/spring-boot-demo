# spring-boot-test


### 本项目知识点
1. spring-boot单元测试
2. maven目录结构


### 2. maven目录结构
- src/main/ java源码目录
  - src/main/java/ java源码目录
  - src/main/resources/ java资源目录
- src/test/ java单元测试目录
  - src/test/java/ java单元测试源码目录
  - src/test/resources/ java单元测试资源目录

多打几次包，多运行几次就知道上面语义


### 可能出现问题
- src/test/ java单元测试目录
```
<sourceFolder url="file://$MODULE_DIR$/src/main/java" isTestSource="false" />
下面加一行，你都猜得出吧，不然百度“idea添加test目录”
<sourceFolder url="file://$MODULE_DIR$/src/test/java" isTestSource="true" />
```


