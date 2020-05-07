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
public class CrmAllDataTypeServiceTest {

    @Resource(name = "crmSqlSessionTemplate")
    SqlSessionTemplate crmSqlSessionTemplate;

    @Test
    public void insert() {
    }


    @Test
    public void selectAll() {
        Map<String, Object> crmAllDataType= new HashMap<>();
        List<Map<String, Object>> crmAllDataTypes = crmSqlSessionTemplate.selectList("com.demo.mybatis.crmMapper.CrmAllDataTypeMapper.selectAll", crmAllDataType);
        System.out.println(crmAllDataTypes);
    }



}
