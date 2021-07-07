# spring-boot-mybatis


## 本项目知识点
1. 环境搭建  
2. 数据库和程序规范  
3. spring的事务，spring事务源码


## 内容 
### 1. 环境搭建
docker启动 mariadb:10.3 容器，数据库就搭建完成   
建表sql脚本见/resources/sql/ 目录  


### 2. 数据库和程序规范  
关于MySQL/MariaDB的数据库规范，我基本参照阿里巴巴Java开发手册  
我自己做了简化处理（个人思想就是简单）  

* 命名规则首字母小写的驼峰法命名，更多规则我讲不清就直接看sql脚本和代码，我按照统一规范写（不会出现有多种规范混淆）  
* 数据库命名使用缩写，因为缩写减少字母啊且这不是无意义/乱来的缩写，如 crm 是 Customer Relationship Management 客户关系管理，具体见sql脚本  
* 数据表命名加上数据库名作为前缀，加个前缀来标识，如 crmCustomer，具体见sql脚本  
* 表字段命名，主键命名如 crmCustomer表的主键 customerId， omsOrder表的主键 orderId  
* 表字段，每个表基本必有 单主键，createTime，modifiedTime  3个字段，具体数据类型，数据生成规则，语义规则 见sql脚本  
* 索引命名 TODO  
* 存储过程命名，TODO 基本没写存储过程  
* 触发器命名，TODO 基本没写触发器    
* 命名规则基本上前端，后端程序，数据库，同一个语义的数据，命名完全相同；因为首字母小写的驼峰法命名在几种命名规范里面是很好的；从字符数，可读性；所以大家用  
* Java中我没有创建对应的Entity/POJO类与表对应，使用java.util.Map与表对应，通过Map List的变量命名来确定使用的表，进而确定有哪些字段   
使用 java.util.Map 和 创建Entity/POJO 与数据表映射比较，自己根据自己的想法/思想进行选择  
|  | 使用java.util.Map | 创建Entity/POJO |  
|----|----|----|
| 业界常规做法 | 个人偏向 | 这个现在是正统的做法 |
| 复杂性 | 相当简单 | 创建一堆类 |
| 开发人员业务关心点 | 开发人员需要关心业务吗？需要关心表设计吗？需要关心前后端接口吗？ | 开发人员需要关心业务吗？需要关心表设计吗？需要关心前后端接口吗？ |
| 能实现业务吗 | 可以 | 可以 |
| 技术原则和要求 | 自行权衡 | 自行权衡 |


### 3. spring的事务
spring的事务，说到底就是数据库事务，这里就是@Transactional

spring事务源码
前置条件：
1.知道数据库事务，spring事务其实就是数据库事务，数据库事务是个大章节，这里具体指mysql的innodb引擎  
2.知道jdbc控制事务，就是最原始的连接数据库方式（这种方式是以前操作数据库的写法，因为那时就只有数据库驱动，没有mybatis这些框架）  
3.spring的AOP源码，spring的事务源码其实就是一种AOP的场景  


先看 CrmJDBCTest 这个测试类，里面就是JDBC操作数据库的api，mybatis/spring事务都只是封装提供更多功能而已  
然后看 CrmServiceTest.crm1Service_insert  debug跟spring事务流程源码   
然后看 CrmServiceTest.crm1Service_transaction  debug跟spring事务传播源码   


spring事务传播和数据库事务  

其他spring传播就不用看，看网上表格就知道怎么用，然而有不常用    

| PROPAGATION_REQUIRED | PROPAGATION_REQUIRES_NEW | PROPAGATION_NESTED |
| ---- | ---- | ---- |
| 使用上一个事务，在同一个数据库连接中 | 创建新的事务，从数据源中再获取新的数据库连接，在这个新的数据库连接中创建新的事务 | 使用上一次事务，在同一个数据库连接中，创建数据库保存点 |  



这里写个spring事务传播伪代码/spring事务传播流程，方便理解和排查spring事务传播中的问题，其实就是 TransactionAspectSupport.invokeWithinTransaction方法的主流程  
这里以Crm1Service.transaction (Map<String, Object> crm1, Map<String, Object> crm2)  方法为例   

```
createTransactionIfNecessary =》开启事务
try {
  执行目标方法 =》 Crm1Service.transaction {
    
     createTransactionIfNecessary =》开启事务
     try {
       执行目标方法 =》 Crm1Service.insert {}
     } catch (Throwable ex) {
        //这里会进行异常异常会回滚吗？看下代码就知道不会在这里进行回滚，还是在最外层的
        completeTransactionAfterThrowing(txInfo, ex);
        throw ex;
     } 
     commitTransactionAfterReturning(txInfo);

     createTransactionIfNecessary =》开启事务
     try {
       执行目标方法 =》 Crm2Service.insert {}
     } catch (Throwable ex) {
        completeTransactionAfterThrowing(txInfo, ex);
        throw ex;
     } 
     commitTransactionAfterReturning(txInfo);
 
  }
} catch (Throwable ex) {
  // 注意这里处理异常进行回滚后，也向外抛异常
  completeTransactionAfterThrowing(txInfo, ex);
  throw ex;
} 
commitTransactionAfterReturning(txInfo);
```
就按着上面的流程捋一捋，在哪里抛异常导致整个流程，这样就知道spring事务源码中各种状态的语义，设置为true，设置为false    



几个与直觉不同的示例  
都以是Crm1Service.transaction为示例  
第一个示例   
@Transactional(propagation = Propagation.REQUIRED)  
Crm1Service.transaction  
@Transactional(propagation = Propagation.REQUIRED)  
Crm1Service.insert  
@Transactional(propagation = Propagation.NESTED)  
Crm2Service.insert -> Crm2Service.insert抛异常
```
createTransactionIfNecessary =》开启事务
try {
  执行目标方法 =》 Crm1Service.transaction {
    
     createTransactionIfNecessary =》开启事务
     try {
        执行目标方法 =》 Crm1Service.insert {
          //如果Crm1Service.insert方法抛出异常
        }
     } catch (Throwable ex) {
        completeTransactionAfterThrowing(txInfo, ex);
        throw ex;
     } 
     commitTransactionAfterReturning(txInfo);

     createTransactionIfNecessary =》开启事务，建立保存点，没错，是在Crm2Service.insert方法前建立保存点的
     try {
        执行目标方法 =》 Crm2Service.insert {
           //如果Crm2Service.insert方法抛出异常
        }
     } catch (Throwable ex) {
        //会执行回滚到保存点，并抛出异常，但是仍向外抛异常，解决方法是手动加 try-catch捕获吞掉Crm2Service.insert抛出的异常
        completeTransactionAfterThrowing(txInfo, ex);
        throw ex;
     } 
     commitTransactionAfterReturning(txInfo);
 
  }
} catch (Throwable ex) {
  // 注意这里处理异常进行回滚后，也向外抛异常
  completeTransactionAfterThrowing(txInfo, ex);
  throw ex;
} 
commitTransactionAfterReturning(txInfo);
```




第一个示例   
@Transactional(propagation = Propagation.REQUIRED)  
Crm1Service.transaction  
@Transactional(propagation = Propagation.REQUIRED_NEW)  
Crm1Service.insert  
@Transactional(propagation = Propagation.REQUIRED)  
Crm2Service.insert -> Crm2Service.insert抛异常
```
createTransactionIfNecessary =》开启事务
try {
  执行目标方法 =》 Crm1Service.transaction {
    
     createTransactionIfNecessary =》开启事务，因为是REQUIRED_NEW，所以会那新的数据库连接，这就是另一个数据库事务
     try {
        执行目标方法 =》 Crm1Service.insert {}
     } catch (Throwable ex) {
        completeTransactionAfterThrowing(txInfo, ex);
        throw ex;
     } 
     //这里会提交事务，因为它是新的数据库连接，恢复旧的数据库连接绑定到ThreadLocal，释放新的数据库连接
     commitTransactionAfterReturning(txInfo);

     createTransactionIfNecessary =》开启事务，所以这里获取的数据库连接是Crm1Service.transaction的
     try {
        执行目标方法 =》 Crm2Service.insert {}
     } catch (Throwable ex) {
        completeTransactionAfterThrowing(txInfo, ex);
        throw ex;
     } 
     commitTransactionAfterReturning(txInfo);
 
  }
} catch (Throwable ex) {
  // 注意这里处理异常进行回滚后，也向外抛异常
  completeTransactionAfterThrowing(txInfo, ex);
  throw ex;
} 
commitTransactionAfterReturning(txInfo);
```




重要点，spring事务什么时候释放数据库连接  
以Crm1Service.transaction为示例  
想想都知道肯定是在 Crm1Service.transaction 完成后释放数据库连接    
TransactionAspectSupport->385:commitTransactionAfterReturning(txInfo);  
TransactionAspectSupport->631:txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
AbstractPlatformTransactionManager->712:processCommit(defStatus);
AbstractPlatformTransactionManager->791:cleanupAfterCompletion(status);
AbstractPlatformTransactionManager->1005:doCleanupAfterCompletion(status.getTransaction());  //注意前面有 if (status.isNewTransaction()) 判断
DataSourceTransactionManager->389:DataSourceUtils.releaseConnection(con, this.dataSource);  //这里释放数据库连接，将数据库连接返还给数据库连接池  



编程式事务 和 注解式事务 的推荐  
注解式事务适合短流程，执行快的代码块  
注解式事务适合流程长，需要自己控制粒度的代码块  







```
mysql 使用存储过程插入100万条数据

建表语句
CREATE TABLE `t1` (
  `t1_id` int(10) NOT NULL AUTO_INCREMENT,
  't1_name' char(32) NOT NULL,
  PRIMARY KEY (`t1_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


存储过程  
-- 存储过程要是存在，先进行删除
DROP PROCEDURE IF EXISTS my_insert;
-- 定义存储过程
CREATE PROCEDURE my_insert()
BEGIN
   DECLARE n int DEFAULT 0;
           START TRANSACTION;   -- 开启事务，关闭自动提交
                loopname1:LOOP
                        INSERT INTO t1(t1_name) VALUES('cwm');
           SET n=n+1;
        IF n=1000000 THEN
            LEAVE loopname1;
        END IF;
        END LOOP loopname1;
COMMIT;  -- 数据插入完毕，手动提交
END;

```










