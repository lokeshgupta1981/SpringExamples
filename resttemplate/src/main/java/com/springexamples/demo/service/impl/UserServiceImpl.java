package com.springexamples.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springexamples.demo.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
    RestTemplate restTemplate;
	
	@Override
	public void testUserService() {

		final String uri = "http://localhost:8080/users";
   	 
        String result = restTemplate.getForObject(uri, String.class);
 
        System.out.println(result);
	}

}
