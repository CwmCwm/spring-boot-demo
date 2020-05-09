package com.demo.mybatis.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 *
 *
 * */
@Service
public class Crm1Service {

    @Resource(name = "crmSqlSessionTemplate")
    SqlSessionTemplate crmSqlSessionTemplate;

    //自己注入自己
    @Autowired
    Crm1Service crm1Service;

    @Autowired
    Crm2Service crm2Service;

    /**
     下面 transaction 里面调用 insert方法，那么insert方法有执行数据库切面吗？=》没有，因为这是Crm1Service实例的内部调用，
     不是spring注入，不是Crm1Service的代理类，不是代理当然不走数据库切面
     * */
//    @Transactional(transactionManager = "crmTransactionManager", propagation = Propagation.REQUIRED)
//    public int transaction (Map<String, Object> crm1) {
//        // 下面 crm1Service.insert(crm1);   这样写就可以走切面。当然从 ApplicationContext.getBean方法中获取同理
//        //crm1Service.insert(crm1);
//        return insert(crm1);
//    }


    @Transactional(transactionManager = "crmTransactionManager", propagation = Propagation.REQUIRED)
    public int transaction (Map<String, Object> crm1, Map<String, Object> crm2) {
        crm1Service.insert(crm1);
        crm2Service.insert(crm2);
        return 1;
    }

    @Transactional(transactionManager = "crmTransactionManager", propagation = Propagation.REQUIRED)
    public int insert (Map<String, Object> crm1) {
        return crmSqlSessionTemplate.insert("com.demo.mybatis.crmMapper.Crm1Mapper.insert", crm1);
    }


}
