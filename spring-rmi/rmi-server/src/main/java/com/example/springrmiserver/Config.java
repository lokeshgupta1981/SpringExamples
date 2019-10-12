package com.example.springrmiserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

import com.example.springrmiserver.service.HelloWorldRMI;
import com.example.springrmiserver.service.HelloWorldRMIimpl;

@Configuration
public class Config {
	
	@Bean
	RemoteExporter registerRMIExporter() {
		
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceName("helloworldrmi");
		exporter.setServiceInterface(HelloWorldRMI.class);
		exporter.setService(new HelloWorldRMIimpl());
		
		return exporter;
	}
	

}
