package com.demo.mybatis.component;

import com.demo.mybatis.TestApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * @SpringBootTest 看注释
 *   classes属性：加载main类，这样就能加载整个ApplicationContext，那就能注入ApplicationContext中的bean了
 *
 * @RunWith 看注释
 *   看不懂，反正照着用
 *
 * 这个Demo单元测试 @Bean, @Service, @Repository, @Component
 * 单元测试 @Controller 见MockMvcControllerTest
 * */
@SpringBootTest(classes = TestApplication.class)
@RunWith(value = SpringRunner.class)
public class HumanTest {

    @Autowired
    private Human human;

    /**
     * 下面方法会在@Test 注解的方法运行前执行
     * 运行一下就知道
     * */
    @Before
    public void before() {
        System.out.println("Test1.before()");
    }

    /**
     * 下面方法会在@Test 注解的方法运行后执行
     * 运行一下就知道
     * */
    @After
    public void after() {
        System.out.println("Test1.after()");
    }

    /**
     * 单元测试注解
     * 基本这个够用了，org.junit.xxx 这些注解我就只用 org.junit.Test
     * 其他我用不到就不理了
     * */
    @Test
    public void test1() {
        System.out.println(human.toString());
    }

    @Test
    public void test2() {
        long l1 = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(l1);
        System.out.println(l1);
        System.out.println(timestamp.getTime());
        System.out.println(timestamp.getNanos());
    }

    @Test
    public void test3() {
        System.out.println(System.currentTimeMillis());

        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate.toString());
        System.out.println(localDateTime.toString());


        System.out.println(localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }


}
