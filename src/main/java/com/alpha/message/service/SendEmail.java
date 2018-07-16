package com.alpha.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String to,String body) {

	    System.out.println("Sending email...");

	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(to);
	    message.setFrom("yangxiangjiang19880805@gmail.com");
	    message.setSubject("Confirm appointment");
	    message.setText(body);
	    javaMailSender.send(message);

	    System.out.println("Email Sent!");
	    }
}
