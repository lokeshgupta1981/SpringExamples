package com.howtodoinjava.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
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
		
		Assert.assertNotNull(emp.getId());
	}
}
