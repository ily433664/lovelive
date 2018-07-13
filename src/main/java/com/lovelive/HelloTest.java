package com.lovelive;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloTest {
    //添加了一个方法
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "hello";
    }
}
