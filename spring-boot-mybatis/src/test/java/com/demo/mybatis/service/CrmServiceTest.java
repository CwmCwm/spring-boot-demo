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
public class CrmServiceTest {

    @Autowired
    private Crm1Service crm1Service;

    @Autowired
    private Crm2Service crm2Service;

    /**
     跟一下数据库事务，spring的数据库事务源码。
     这个示例简单明了执行spring事务流程，没有spring事务传播，
     当然先了解spring事务流程，spring事务传播就是在spring事务流程中某些判断中进入去修改一些状态，走一些额外流程
     * */
    @Test
    public void crm1Service_insert() {
        Map<String, Object> crm1 = new HashMap<>();
        crm1.put("crm1Id", 100L);
        // crm1Service.insert(crm1);  打个断点跟进去
        crm1Service.insert(crm1);
    }


    /**
     上面跟完后了解spring事务流程后，就再来看spring事务传播流程
     这里演示了spring事务传播
     跟完默认的 PROPAGATION_REQUIRED ，可以改下跟 PROPAGATION_REQUIRES_NEW 和 PROPAGATION_NESTED
     * */
    @Test
    public void crm1Service_transaction() {
        Map<String, Object> crm1 = new HashMap<>();
        crm1.put("crm1Id", 100L);
        Map<String, Object> crm2 = new HashMap<>();
        crm2.put("crm2Id", 200L);
        // 打个断点跟进去
        crm1Service.transaction(crm1, crm2);
    }


}
