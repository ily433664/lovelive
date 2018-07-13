package com.lovelive.test.web;

import com.google.gson.Gson;
import com.lovelive.test.BaseJunitTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName ControllerTestJunit
 * @Description 模拟http请求的单元测试
 */
public class ControllerTestJunit extends BaseJunitTest {

    // mock api 模拟http请求
    private MockMvc mockMvc;

    @Autowired
    private ControllerTest controllerTest;
	
	/*Junit常用注解：
	@Before：初始化方法
	@After：释放资源
	@Test：测试方法，在这里可以测试期望异常和超时时间
	@Ignore：忽略的测试方法
	@BeforeClass：针对所有测试，只执行一次，且必须为static void
	@AfterClass：针对所有测试，只执行一次，且必须为static void
	@RunWith：指定使用的单元测试执行类*/

    @Before
    public void setUp() {
        // 独立安装测试
        mockMvc = MockMvcBuilders.standaloneSetup(new ControllerTest()).build();
        // 集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）
        //mockMvc = MockMvcBuilders.webAppContextSetup(content).build();
    }

    // 测试
    @Test
    public void test() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("test")));

        System.out.println(new Gson().toJson(resultActions));

        controllerTest.test();
    }

}
