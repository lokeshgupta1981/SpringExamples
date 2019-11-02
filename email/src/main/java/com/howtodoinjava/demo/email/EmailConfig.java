package com.howtodoinjava.demo.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig 
{
	@Bean
	public SimpleMailMessage emailTemplate()
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("user@gmail.com");
		message.setFrom("admin@gmail.com");
		message.setSubject("Important email");
	    message.setText("FATAL - Application crash. Save your job !!");
	    return message;
	}
}