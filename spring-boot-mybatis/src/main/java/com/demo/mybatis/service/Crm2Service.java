package com.demo.mybatis.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;


/**
 *
 *
 * */
@Service
public class Crm2Service {

    @Resource(name = "crmSqlSessionTemplate")
    SqlSessionTemplate crmSqlSessionTemplate;

    @Transactional(transactionManager = "crmTransactionManager", propagation = Propagation.REQUIRED)
    public int insert (Map<String, Object> crm2) {
        return crmSqlSessionTemplate.insert("com.demo.mybatis.crmMapper.Crm2Mapper.insert", crm2);
    }


}
