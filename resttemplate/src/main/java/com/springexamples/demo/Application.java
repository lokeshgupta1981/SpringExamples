package com.springexamples.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.springexamples.demo.config.HttpClientConfig;
import com.springexamples.demo.config.RestTemplateConfig;
import com.springexamples.demo.service.UserService;

@SpringBootApplication
@Import({ HttpClientConfig.class, RestTemplateConfig.class })
public class Application implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//userService.testUserService();
	}
}
