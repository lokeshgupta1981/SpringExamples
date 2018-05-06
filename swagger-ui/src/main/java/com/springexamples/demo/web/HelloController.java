package com.springexamples.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class HelloController {

	@ApiOperation(value = "Get hello world message ", response = String.class)
    @GetMapping("/")
    public String helloWorld() {
        return "Greetings from Spring Examples !!";
    }

}
