package com.sample.opentrace.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {

    @RequestMapping(value = "/hello")
    public String helloMethod(){

        return "Hello";
    }
}
