package com.howtodoinjava.demo;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {
	@GetMapping("/example")
	public String example() {
		return "Hello User !! " + LocalDateTime.now();
	}
}
