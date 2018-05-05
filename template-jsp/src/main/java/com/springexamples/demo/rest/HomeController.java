package com.springexamples.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "Hi buddy");
		modal.addAttribute("message", "Welcome to SprinExamples.com");
		return "home";
	}

}
