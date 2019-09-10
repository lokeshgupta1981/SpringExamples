package com.howtodoinjava.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SpringBootDemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)  
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeControllerIntegrationTests 
{
	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Sql({ "schema.sql", "data.sql" })
	@Test
	public void testAllEmployees() 
	{
		assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/employees",
                Employees.class).getEmployeeList().size() == 3);
	}
	
	@Test
	public void testAddEmployee() 
	{
		Employee employee = new Employee("Lokesh", "Gupta", "howtodoinjava@gmail.com");
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/employees", employee, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
}
