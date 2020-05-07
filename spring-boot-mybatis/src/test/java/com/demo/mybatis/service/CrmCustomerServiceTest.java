package com.demo.mybatis.service;


import com.demo.mybatis.MybatisApplication;
import com.demo.mybatis.util.SnowFlakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = MybatisApplication.class)
@RunWith(value = SpringRunner.class)
public class CrmCustomerServiceTest {

    //这里引入Service层，主要是演示数据库事务的写法
    @Autowired
    private CrmCustomerService crmCustomerService;

    //基本SqlSessionTemplate 常用的api 和 标签 我就用这些，足够应付大多数了
    //这里直接引入SqlSessionTemplate，反正是演示Mapper.xml 的写法
    @Resource(name = "crmSqlSessionTemplate")
    SqlSessionTemplate crmSqlSessionTemplate;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Test
    public void insert() {
        Map<String, Object> crmCustomer = new HashMap<>();
        Long customerId = snowFlakeUtil.nextId();
        crmCustomer.put("customerId", customerId);
        crmCustomer.put("createTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("customerName", customerId.toString());
        int result = crmSqlSessionTemplate.insert("com.demo.mybatis.crmMapper.CrmCustomerMapper.insert", crmCustomer);
        System.out.println(result);
    }

    @Test
    public void update() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerId", 1);
        crmCustomer.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("customerName", "gg2");
        int result = crmSqlSessionTemplate.update("com.demo.mybatis.crmMapper.CrmCustomerMapper.update", crmCustomer);
        System.out.println(result);
    }

    @Test
    public void delete() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerId", 1);
        int result = crmSqlSessionTemplate.delete("com.demo.mybatis.crmMapper.CrmCustomerMapper.delete", crmCustomer);
        System.out.println(result);
    }

    @Test
    public void selectAll() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerName", 1);
        List<Map<String, Object>> crmCustomers = crmSqlSessionTemplate.selectList("com.demo.mybatis.crmMapper.CrmCustomerMapper.selectAll", crmCustomer);
        System.out.println(crmCustomers);
    }

    @Test
    public void selectPage() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerName", 1);
        crmCustomer.put("limitStart", 0);
        crmCustomer.put("pageSize", 1);
        List<Map<String, Object>> crmCustomers = crmSqlSessionTemplate.selectList("com.demo.mybatis.crmMapper.CrmCustomerMapper.selectPage", crmCustomer);
        System.out.println(crmCustomers);

        Map<String, Object> countMap = crmSqlSessionTemplate.selectOne("com.demo.mybatis.crmMapper.CrmCustomerMapper.selectCount", crmCustomer);
        System.out.println(countMap.get("count(*)"));
    }

    @Test
    public void selectOne() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerId", 1);
        Map<String, Object> crmCustomerMap = crmSqlSessionTemplate.selectOne("com.demo.mybatis.crmMapper.CrmCustomerMapper.selectOne", crmCustomer);
        System.out.println(crmCustomerMap);
    }

    @Test
    public void selectIn() {
        Long[] customerIds = new Long[]{1L, 2L};
        List<Map<String, Object>> crmCustomers = crmSqlSessionTemplate.selectList("com.demo.mybatis.crmMapper.CrmCustomerMapper.selectIn", customerIds);
        System.out.println(crmCustomers);
    }


    @Test
    public void test1() throws Exception {
        Map<String, Object> crmCustomer1 = new HashMap<>();
        crmCustomer1.put("customerId", snowFlakeUtil.nextId());
        crmCustomer1.put("createTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer1.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer1.put("customerName", "cwm");

        Map<String, Object> crmCustomer2 = new HashMap<>();
        crmCustomer2.put("customerId", snowFlakeUtil.nextId());
        crmCustomer2.put("createTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer2.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer2.put("customerName", "hwy");

        crmCustomerService.insertTwoRecordV1(crmCustomer1, crmCustomer2);
    }
}
