package com.example.springrmiserver.service;

import java.util.Date;

public class HelloWorldRMIimpl implements HelloWorldRMI {

	@Override
	public String sayHelloRmi(String msg) {
		System.out.println("================Server Side ========================");
		System.out.println("Inside Rmi IMPL - Incoming msg : " + msg);
		return "Hello " + msg + " :: Response time - > " + new Date();
	}
}
