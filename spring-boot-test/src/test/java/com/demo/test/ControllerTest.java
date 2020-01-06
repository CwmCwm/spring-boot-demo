package com.demo.test;


import com.demo.test.controller.MockMvcController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * 这个示例单元测试 @Controller
 * Controller的单元测试写起来是麻烦，使用MockMvc 要熟悉它的方法和使用
 * 虽然可以用postMan完成单元测试，但使用MockMvc 有单元测试的代码啊
 *
 * @WebAppConfiguration 用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
 * */
@SpringBootTest(classes = TestApplication.class)
@RunWith(value = SpringRunner.class)
@WebAppConfiguration
public class ControllerTest {

    // 注入Controller
    @Autowired
    MockMvcController mockMvcController;

    /**
     * GET请求
     * */
    @Test
    public void get() throws Exception {
        // 建造者模式创建MockMvc测试模板。见MockMvc注释，有示例代码，就是写法不一样
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mockMvcController).build();
        // perform方法，传入请求路径（哎，入参规定的类型，还用讲url再封装一次，直接写String不行吗）
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/mockMvcController/get"))
                // andExpect方法，传入你期待匹配的结果，就相当于Assert
                .andExpect(MockMvcResultMatchers.status().is(200))
                // andDo方法，上面andExpect匹配通过后就会执行andDo方法，这里MockMvcResultHandlers.print() 是打印执行结果，详见控制台输出
                .andDo(MockMvcResultHandlers.print())
                // andReturn方法，返回MvcResult，然后你自己操作咯
                .andReturn();
    }

    /**
     * POST请求
     * */
    @Test
    public void post() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mockMvcController).build();
        // 请求内容
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mockMvcController/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "  \"name\": \"John\"," +
                        "  \"age\": 30," +
                        "  \"money\": 100000.01," +
                        "  \"favor\": [\"football\", \"basketball\"]," +
                        "  \"wife\": {\"name\": \"Lucy\", \"age\":30}," +
                        "  \"married\": true" +
                        "}");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
