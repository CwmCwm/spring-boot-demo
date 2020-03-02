package com.demo.mybatis.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 *
 * 我的写法
 * 第1步：编写Mapper.xml的sql文件
 * 第2步：编写Service文件
 * 第3步：编写Controller文件
 *
 * 即使Controller层可以直接调SqlSessionTemplate 来执行sql语句，也尽量不要Controller层直接调SqlSessionTemplate
 * 即使Service层很简单就，比如下面的方法有些只写了一行，相当于中转，也在Service层多写一个方法
 * 原因：层级关系，方法命名
 * 至于代码复用（一般先分开写，各自互相对立，方便改；一般都是写业务代码，业务稳定才能写公共代码；工具代码都复用）
 *
 * 一般事务都打在Service层，就是 @Transactional 写在Service层上，当然也可以写在Controller层上（一样起效），
 * 因为各层的作用职责，自己去定义各层级的职责，按规范做事
 *
 * */
@Service
public class CrmCustomerService {

    @Resource(name = "crmSqlSessionTemplate")
    SqlSessionTemplate crmSqlSessionTemplate;

    public int insert (Map<String, Object> crmCustomer) {
        return crmSqlSessionTemplate.insert("com.demo.mybatis.crmMapper.CrmCustomerMapper.insert", crmCustomer);
    }

    public int update (Map<String, Object> crmCustomer) {
        return crmSqlSessionTemplate.update("com.demo.mybatis.crmMapper.CrmCustomerMapper.update", crmCustomer);
    }

    public int delete (Map<String, Object> crmCustomer) {
        return crmSqlSessionTemplate.delete("com.demo.mybatis.crmMapper.CrmCustomerMapper.delete", crmCustomer);
    }

    public List<Map<String, Object>> selectAll (Map<String, Object> crmCustomer) {
        return crmSqlSessionTemplate.selectList("com.demo.mybatis.crmMapper.CrmCustomerMapper.selectAll", crmCustomer);
    }

    public List<Map<String, Object>> selectPage (Map<String, Object> crmCustomer) {
        return crmSqlSessionTemplate.selectList("com.demo.mybatis.crmMapper.CrmCustomerMapper.selectPage", crmCustomer);
    }

}
