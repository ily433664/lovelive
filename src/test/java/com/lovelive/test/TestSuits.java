package com.lovelive.test;

import com.lovelive.test.web.ControllerTestJunit;
import com.lovelive.test.web.ControllerTestJunit2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Description:打包测试
 */
// @Ignore("not ready yet") //忽略的测试方法
@RunWith(Suite.class)
@Suite.SuiteClasses({ControllerTestJunit.class, ControllerTestJunit2.class})
public class TestSuits {

    // 不用写代码，只需要注解即可

}
