package com.howtodoinjava.aop;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class DomainService 
{
	public Object getDomainObjectById(Long id)
	{
		try {
			Thread.sleep(new Random().nextInt(2000));
		} catch (InterruptedException e) {
			//do some logging
		}
        return id;
    }
}
