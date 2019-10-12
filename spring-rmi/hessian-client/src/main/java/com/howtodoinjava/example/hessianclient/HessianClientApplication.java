package com.howtodoinjava.example.hessianclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.howtodoinjava.hessianserver.hessian.HelloWorld;

@SpringBootApplication
public class HessianClientApplication {

	@Bean
	public HessianProxyFactoryBean hessianInvoker() {
		HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8080/hellohessian");
		invoker.setServiceInterface(HelloWorld.class);
		return invoker;
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(HessianClientApplication.class, args);
		System.out.println("========Client Side===============");
		HelloWorld helloWorld =   	context.getBean(HelloWorld.class);
		System.out.println(helloWorld.sayHelloWithHessian("Sajal"));
	}
}
