package com.parksangdo.restapi.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldControlller {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }

}
