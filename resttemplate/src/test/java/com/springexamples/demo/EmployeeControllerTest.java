package com.springexamples.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.springexamples.demo.config.HttpClientConfig;
import com.springexamples.demo.config.RestTemplateConfig;
import com.springexamples.demo.web.EmployeeController;

/*@RunWith(SpringRunner.class)
@Import({EmployeeController.class, HttpClientConfig.class, RestTemplateConfig.class})*/
public class EmployeeControllerTest 
{
	/*@Autowired
    RestTemplate restTemplate;
	
	*//*@Test
	public void testGetEmployees() 
	{
		final String uri = "http://localhost:8080/employees";
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
	}*/
}
