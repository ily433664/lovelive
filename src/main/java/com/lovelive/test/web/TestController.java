package com.lovelive.test.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lovelive.common.util.BlobUtil;
import com.lovelive.test.entity.TestMybatis;
import com.lovelive.test.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping(value = "/test")
public class TestController implements ITestController {

    private final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private ITestService testService;

    @Override
    public void hi() {

        System.out.println("test hi ...");

    }

    @RequestMapping("test")
    public String test(HttpServletRequest request, Model model,
                       @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        log.info("----------------------------------------");

        System.out.println("pageNo:" + pageNo + " , pageSize:" + pageSize);

        PageHelper.startPage(pageNo, pageSize);
        List<TestMybatis> list = testService.selectTest();
        PageInfo<TestMybatis> pageInfo = new PageInfo<TestMybatis>(list);

        //Page<TestMybatis> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(()-> testService.selectTest());

        System.out.println(new Gson().toJson(pageInfo));

        model.addAttribute("pageInfo", pageInfo);

        return "test";
    }

    @RequestMapping("test1")
    public String test1(HttpServletRequest request, Model model) {

        int num = 200000;

        Long time1 = System.currentTimeMillis();

        for (int i = 0; i <= num; i++) {
            if (log.isInfoEnabled()) {
                log.info("if log.info..." + i);
            }
            if (log.isDebugEnabled()) {
                log.debug("if log.debug.." + i);
            }
        }

        Long time2 = System.currentTimeMillis();

        for (int i = 0; i <= num; i++) {
            log.info("not if log.info..." + i);
            log.debug("not if log.debug.." + i);
            ;
        }

        Long time3 = System.currentTimeMillis();

        System.out.println("if : " + (time2 - time1));

        System.out.println("not if : " + (time3 - time2));

        return "test";
    }

    @RequestMapping("blobTest")
    public String blobTest(HttpServletRequest request, HttpServletResponse response) {
        return "test/blobTest";
    }

    @RequestMapping("blobTest2")
    public void blobTest2(HttpServletRequest request, HttpServletResponse response) {
        BlobUtil.blobTest("", request, response);
    }

    @RequestMapping("webSocketTest")
    public String webSocketTest(HttpServletRequest request, HttpServletResponse response) {
        return "test/webSocketTest";
    }

}
