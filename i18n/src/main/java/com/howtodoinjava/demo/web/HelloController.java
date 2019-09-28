package com.howtodoinjava.demo.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	MessageSource messageSource;
	
    @GetMapping("/")
    public String index(Locale locale) {
        return messageSource.getMessage("error.notfound", null, locale);
    }

}
