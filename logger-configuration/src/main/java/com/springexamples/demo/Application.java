package com.springexamples.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner
{
	protected final Log logger = LogFactory.getLog(getClass());
	
    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		logger.info("INFO level log statement !!");
		logger.debug("DEBUG level log statement !!");
	}
}
