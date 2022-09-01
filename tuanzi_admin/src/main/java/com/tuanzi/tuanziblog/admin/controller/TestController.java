package com.tuanzi.tuanziblog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testEnv")
public class TestController {
    @Autowired
    private Environment environment;
    @GetMapping("/test1")
    public void test1(){
        System.out.println(environment.getProperty("data.webSite.url"));
    }
}
