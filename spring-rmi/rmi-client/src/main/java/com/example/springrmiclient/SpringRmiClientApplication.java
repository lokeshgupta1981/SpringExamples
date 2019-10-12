package com.example.springrmiclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.example.springrmiserver.service.HelloWorldRMI;

@SpringBootApplication
public class SpringRmiClientApplication {

	@Bean
	RmiProxyFactoryBean rmiProxy() {
		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(HelloWorldRMI.class);
		bean.setServiceUrl("rmi://localhost:1099/helloworldrmi");

		return bean;
	}

	public static void main(String[] args) {
		HelloWorldRMI helloWorldRMI = SpringApplication.run(SpringRmiClientApplication.class, args).getBean(HelloWorldRMI.class);
		System.out.println("================Client Side ========================");
		System.out.println(helloWorldRMI.sayHelloRmi("Sajal"));
	}
}
