package com.howtodoinjava.demo;

import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {
	@GetMapping("/example")
	public String example() {
		return "Hello User !! " + new Date();
	}

	public static void main(String[] args) {
		String text = "Lorem Ipsum is simply dummy text";
		String substring = text.substring(3);
		System.out.println(substring);
		System.out.println(substring.length());
		
		
		substring = text.substring(3, 10);
		System.out.println(substring);
		System.out.println(substring.length());
	}

}
