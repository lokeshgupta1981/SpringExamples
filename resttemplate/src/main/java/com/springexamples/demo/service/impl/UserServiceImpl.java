package com.springexamples.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springexamples.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    RestTemplate restTemplate;
	
	@Override
	public String testUserService() 
	{
		final String uri = "http://localhost:8080/users";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Header", "value");
		headers.set("Other-Header", "othervalue");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
        String result = restTemplate.getForObject(uri, String.class);
        return result;
	}
}
