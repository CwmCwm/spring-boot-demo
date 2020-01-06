# spring-boot-test


### 本项目知识点
1. spring-boot单元测试
2. maven目录结构

### 认知
- 为什么要单元测试？=》因为这是程序员开发代码后的自行校验
- 编写单元测试代码=》为了单元测试的自动化
- 开发理念=》TDD测试驱动开发（Test-Driven Development），你要知道要什么/明确理解细化需求，才能写出单元测试，
你单元测试都写出来了，基本所有细节都清晰了，不会有模糊地带，那就开发代码了

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


