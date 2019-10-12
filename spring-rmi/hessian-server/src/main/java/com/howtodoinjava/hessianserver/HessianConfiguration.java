package com.howtodoinjava.hessianserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

import com.howtodoinjava.hessianserver.hessian.HelloWorld;
import com.howtodoinjava.hessianserver.hessian.HelloWorldImpl;

@Configuration
public class HessianConfiguration {

	@Bean(name = "/hellohessian")
	RemoteExporter sayHelloServiceHessian() {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(new HelloWorldImpl());
		exporter.setServiceInterface(HelloWorld.class);
		return exporter;
	}

}
