package com.parksangdo.restapi.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // java bean return // auto json return, in body  , with path variable
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariableBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello, World!!, %s", name));
    }

}
