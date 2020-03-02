package com.demo.mybatis.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class OmsOrderService {

    @Resource(name = "omsSqlSessionTemplate")
    SqlSessionTemplate omsSqlSessionTemplate;
    @Resource(name = "crmSqlSessionTemplate")
    SqlSessionTemplate crmSqlSessionTemplate;

    //注入数据库事务管理器
    @Resource(name = "omsTransactionManager")
    DataSourceTransactionManager omsTransactionManager;
    @Resource(name = "crmTransactionManager")
    DataSourceTransactionManager crmTransactionManager;

    public int insert (Map<String, Object> omsOrder) {
        return omsSqlSessionTemplate.insert("com.demo.mybatis.omsMapper.OmsOrderMapper.insert", omsOrder);
    }

    public int update (Map<String, Object> omsOrder) {
        return omsSqlSessionTemplate.update("com.demo.mybatis.omsMapper.OmsOrderMapper.update", omsOrder);
    }

    public int delete (Map<String, Object> omsOrder) {
        return omsSqlSessionTemplate.delete("com.demo.mybatis.omsMapper.OmsOrderMapper.delete", omsOrder);
    }

    public List<Map<String, Object>> selectAll (Map<String, Object> omsOrder) {
        return omsSqlSessionTemplate.selectList("com.demo.mybatis.omsMapper.OmsOrderMapper.selectAll", omsOrder);
    }

    public List<Map<String, Object>> selectPage (Map<String, Object> omsOrder) {
        return omsSqlSessionTemplate.selectList("com.demo.mybatis.omsMapper.OmsOrderMapper.selectPage", omsOrder);
    }
    //上面就是很常规方法，不是重点
    //下面是数据库事务的演示

    /**
     * 事务写法有3种： 第1种：传统的使用JDBC的事务管理（这写法现在一般不写）  第2种：spring的声明式事务   第3种：spring提供的编程式的事务管理
     *
     * 这里是 第3种：spring提供的编程式的事务管理
     * @Transactional 注解方式来使用事务， transactionManager属性指定事务管理器
     * 关于 @Transactional 使用事务传播属性，记起来确实麻烦，要用到就查一下
     * 插入两条记录
     * 通过主键重复演示抛异常来回滚事务
     *
     * 多个数据库肯定是不同不停数据库事务，这里就用事务传播，这个理解麻烦，最好画一张表和示例进行说明
     * */
    @Transactional(transactionManager = "omsTransactionManager")
    public int insertTwoRecordV1 (Map<String, Object> omsOrder1, Map<String, Object> omsOrder2) {
        int result1 = omsSqlSessionTemplate.insert("com.demo.mybatis.omsMapper.OmsOrderMapper.insert", omsOrder1);
        System.out.println("result1=" + result1);
        int result2 = omsSqlSessionTemplate.insert("com.demo.mybatis.omsMapper.OmsOrderMapper.insert", omsOrder2);
        System.out.println("result2=" + result2);
        return 1;
    }


    /**
     * 这里是 第2种：spring的声明式事务
     * 使用编程式事务，做的事和上面 insertTwoRecordV2 方法一样
     * 就是写法不一样， @Transactional 底层也是编程式事务，自行权衡和使用场景
     * 编程式事务是最通用，就一种写法，就是多写代码
     *
     * 多个数据库肯定是不同不停数据库事务，这里手动控制理解最简单
     * */
    public int insertTwoRecordV2 (Map<String, Object> omsOrder1, Map<String, Object> omsOrder2) {
        //事务声明定义，就是个标识，写法就是这样,功能强就写法就麻烦，反正就是提供很多api，具体我也不想看
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = omsTransactionManager.getTransaction(defaultTransactionDefinition);
        //自行处理异常
        try {
            int result1 = omsSqlSessionTemplate.insert("com.demo.mybatis.omsMapper.OmsOrderMapper.insert", omsOrder1);
            System.out.println("result1=" + result1);
            int result2 = omsSqlSessionTemplate.insert("com.demo.mybatis.omsMapper.OmsOrderMapper.insert", omsOrder2);
            System.out.println("result2=" + result2);
            //手动commit
            omsTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            //手动rollback
            omsTransactionManager.rollback(transactionStatus);
        }
        return 1;
    }

    /**
     * TODO，下面这样写，会抛 throw new IllegalStateException("Transaction synchronization is not active");
     * 自己debug，见 org.springframework.transaction.support.TransactionSynchronizationManager：309行->public static List<TransactionSynchronization> getSynchronizations() throws IllegalStateException
     * 还是要看源码，额外设置的类和方法到底为了啥，debug后，真正执行 commit不在org.springframework.transaction.support.TransactionSynchronizationManager
     * org.springframework.transaction.support.TransactionSynchronizationManager 感觉就是一些判断，但不执行 commit，debug配合数据库就能看出那个方法执行commit
     * 按逻辑crm事务管理器、crm事务状态 和 oms事务管理器 、oms事务状态应该是互相独立的，但spring的事务管理又有耦合点，或许是设计思想的问题（我哪知道spring事务的设计思想）
     * 猜测不同数据库事务属于 分布式事务范畴
     *
     * 第1种：传统的使用JDBC的事务管理（这写法现在一般不写）必然是可以自己手动控制
     *
     * crmCustomer表插入一条记录，omsOrder表插入一条记录
     * */
    public int insertTwoRecordV3 (Map<String, Object> crmCustomer, Map<String, Object> omsOrder) {
        DefaultTransactionDefinition crmDefaultTransactionDefinition = new DefaultTransactionDefinition();
        crmDefaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus crmTransactionStatus = crmTransactionManager.getTransaction(crmDefaultTransactionDefinition);
        DefaultTransactionDefinition omsDefaultTransactionDefinition = new DefaultTransactionDefinition();
        omsDefaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus omsTransactionStatus = omsTransactionManager.getTransaction(omsDefaultTransactionDefinition);
        //自行处理异常
        try {
            int result1 = crmSqlSessionTemplate.insert("com.demo.mybatis.crmMapper.CrmCustomerMapper.insert", crmCustomer);
            System.out.println("result1=" + result1);
            int result2 = omsSqlSessionTemplate.insert("com.demo.mybatis.omsMapper.OmsOrderMapper.insert", omsOrder);
            System.out.println("result2=" + result2);

            //手动commit
            //crmTransactionManager commit后会清空当前线程的synchronizations
            //具体见org.springframework.transaction.support.TransactionSynchronizationManager：84行->private static final ThreadLocal<Set<TransactionSynchronization>> synchronizations = new NamedThreadLocal<>("Transaction synchronizations");
            crmTransactionManager.commit(crmTransactionStatus);

            //上面 crmTransactionManager commit后，当前线程的synchronizations置为null
            //omsTransactionManager commit 运行到org.springframework.transaction.support.TransactionSynchronizationManager：309行就抛错
            omsTransactionManager.commit(omsTransactionStatus);
        } catch (Exception e) {
            //手动rollback
            crmTransactionManager.rollback(crmTransactionStatus);
            omsTransactionManager.rollback(omsTransactionStatus);
        }
        return 1;
    }


    /**
     * TODO 这样写跟上面 insertTwoRecordV3 debug走的完全一样
     * 就是执行commit后会清空当前线程的synchronizations=空Set，就不会抛错
     *
     * 就这样吧，反正这属于分布式事务的范畴，其实分布式事务有专门的理论和业界框架
     * */
    public int insertTwoRecordV4 (Map<String, Object> crmCustomer, Map<String, Object> omsOrder) {
        //定义实例化事务状态
        DefaultTransactionDefinition crmDefaultTransactionDefinition = new DefaultTransactionDefinition();
        crmDefaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus crmTransactionStatus = crmTransactionManager.getTransaction(crmDefaultTransactionDefinition);
        DefaultTransactionDefinition omsDefaultTransactionDefinition = new DefaultTransactionDefinition();
        omsDefaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus omsTransactionStatus = omsTransactionManager.getTransaction(omsDefaultTransactionDefinition);

        //添加事务管理器 和 事务状态
        Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack = new Stack<DataSourceTransactionManager>();
        Stack<TransactionStatus> transactionStatusStack = new Stack<TransactionStatus>();
        dataSourceTransactionManagerStack.add(crmTransactionManager);
        dataSourceTransactionManagerStack.add(omsTransactionManager);
        transactionStatusStack.add(crmTransactionStatus);
        transactionStatusStack.add(omsTransactionStatus);

        //自行处理异常
        try {
            int result1 = crmSqlSessionTemplate.insert("com.demo.mybatis.crmMapper.CrmCustomerMapper.insert", crmCustomer);
            System.out.println("result1=" + result1);
            int result2 = omsSqlSessionTemplate.insert("com.demo.mybatis.omsMapper.OmsOrderMapper.insert", omsOrder);
            System.out.println("result2=" + result2);

            //手动commit  使用Stack提交commit
            while (!dataSourceTransactionManagerStack.isEmpty()) {
                dataSourceTransactionManagerStack.pop().commit(transactionStatusStack.pop());
            }
        } catch (Exception e) {
            //手动rollback  使用Stack回滚rollback
            while (!dataSourceTransactionManagerStack.isEmpty()) {
                dataSourceTransactionManagerStack.pop().rollback(transactionStatusStack.pop());
            }
        }
        return 1;
    }

}
