package com.lovelive.test.web;

import com.lovelive.test.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ControllerTestJunit2
 * @Description 直接调用方法的单元测试
 */
public class ControllerTestJunit2 extends BaseJunitTest {

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

    // 测试
    @Test
    public void test() throws Exception {

        controllerTest.test();
    }

}
