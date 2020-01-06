package com.demo.test;

import com.demo.test.component.Human;
import com.demo.test.controller.SpringMVCDemo1Controller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * 这个示例单元测试 @Controller
 *
 * @WebAppConfiguration 用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
 * */
@SpringBootTest(classes = TestApplication.class)
@RunWith(value = SpringRunner.class)
@WebAppConfiguration
public class ControllerTest {

    // 注入Controller
    @Autowired
    SpringMVCDemo1Controller springMVCDemo1Controller;

    /**
     * GET请求
     * */
    @Test
    public void test1() throws Exception {
        // 建造者模式创建MockMvc测试模板。见MockMvc注释，有示例代码，就是写法不一样
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(springMVCDemo1Controller).build();
        // perform方法，传入请求路径（哎，入参规定的类型，还用讲url再封装一次，直接写String不行吗）
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/springMVCAnnotationDemo1Controller/get"))
                // andExpect方法，传入你期待匹配的结果，就相当于Assert
                .andExpect(MockMvcResultMatchers.status().is(200))
                // andDo方法，上面andExpect匹配通过后就会执行andDo方法，这里MockMvcResultHandlers.print() 是打印执行结果，详见控制台输出
                .andDo(MockMvcResultHandlers.print())
                // andReturn方法，返回MvcResult，然后你自己操作咯
                .andReturn();
    }


}
