package com.parksangdo.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldControlller {

    // get "/hello-world"
    @GetMapping(path = "hello-world")
    public String helloWorld() {
        return "helo World.";
    }

    // java bean return // auto json return, in body
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello, World!!");
    }

}
