package com.howtodoinjava.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = HypermediaAutoConfiguration.class)
public class SpringBootHateoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHateoasApplication.class, args);
	}
	
	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
	    return new ForwardedHeaderFilter();
	}
}
