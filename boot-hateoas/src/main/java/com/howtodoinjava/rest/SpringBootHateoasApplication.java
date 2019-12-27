package com.howtodoinjava.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = HypermediaAutoConfiguration.class)
public class SpringBootHateoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHateoasApplication.class, args);
	}
}
