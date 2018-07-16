package com.alpha.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.message.service.SendEmail;

@RestController
public class EmailController {

	@Autowired
	SendEmail sendEmail;

	@RequestMapping("/")
	public String index() {
		sendEmail.sendMail("yxj1985@126.com", "test");
		return "Greetings from Spring Boot!";
	}
}
