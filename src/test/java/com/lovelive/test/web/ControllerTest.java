package com.lovelive.test.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @RequestMapping("/test")
    public String test() {

        System.out.println("this is junit test ... ");

        return "test";
    }
}
