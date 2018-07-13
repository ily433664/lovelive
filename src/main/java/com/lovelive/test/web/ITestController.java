package com.lovelive.test.web;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ITestController {

    @RequestMapping("/hi")
    public void hi();
}
