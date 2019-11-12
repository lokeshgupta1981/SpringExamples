package com.springexamples.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springexamples.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    RestTemplate restTemplate;
	
	@Override
	public String testUserService() {

		final String uri = "http://localhost:8080/users";
   	 
        String result = restTemplate.getForObject(uri, String.class);
 
        System.out.println(result);
        
        return result;
	}
}
