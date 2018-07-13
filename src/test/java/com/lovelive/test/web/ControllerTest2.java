package com.lovelive.test.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest2 {

    @RequestMapping("/test2")
    public String test2() {

        System.out.println("this is junit test2 ... ");

        return "test2";
    }
}
