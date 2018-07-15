package com.alpha.message.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class EmailController {

	 @RequestMapping("/")
	    public String index() {
	        return "Greetings from Spring Boot!";
	    }
}
