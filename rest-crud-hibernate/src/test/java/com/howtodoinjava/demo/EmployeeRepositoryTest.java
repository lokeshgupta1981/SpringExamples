package com.howtodoinjava.demo;

import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTest 
{
	@Autowired
	EmployeeRepository repository;
	
	@Test
	public void testRepository() 
	{
		EmployeeEntity emp = new EmployeeEntity();
		emp.setFirstName("Lokesh");
		emp.setLastName("Gupta");
		emp.setEmail("howtodoinjava@gmail.com");
		
		repository.save(emp);
		
		System.out.println(emp);
		
		Assertions.assertNotNull(emp.getId());
	}
}
