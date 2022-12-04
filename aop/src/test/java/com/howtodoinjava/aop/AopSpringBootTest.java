package com.howtodoinjava.aop;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AopSpringBootTest 
{
	@Autowired
	private DomainService service;
	
	@Test
	public void testGetDomainObjectById() {
		service.getDomainObjectById(10L);
	}
}