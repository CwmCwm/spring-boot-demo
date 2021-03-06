package com.demo.jdbc.service;


import com.demo.jdbc.JDBCApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest(classes = JDBCApplication.class)
@RunWith(value = SpringRunner.class)
public class CrmAllDataTypeServiceTest {

    //直接使用api，看看 NamedParameterJdbcTemplate 有哪些常用api
    @Resource(name = "crmNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    @Test
    public void selectAll() {
        Map<String, Object> paramMap = new HashMap<>();
        String sqlString = "select * from crmAllDataType";
        List<Map<String, Object>> crmAllDataTypes = crmNamedParameterJdbcTemplate.queryForList(sqlString, paramMap);
        for (Map<String, Object> crmAllDataType : crmAllDataTypes) {
            Set<String> keySet = crmAllDataType.keySet();
            for (String key : keySet) {
                System.out.println(key + "=" + crmAllDataType.get(key).getClass().getName());
            }
        }
        //debug看一下数据库类型和java数据类型的映射，和使用Mybatis一样
        System.out.println(crmAllDataTypes);
    }

}
