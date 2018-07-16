package com.alpha.message;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableScheduling
@EnableAsync
@EnableEmailTools
public class Application {
	 public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	    }
	 
	 @Bean
	 public JavaMailSender javaMailService() {
	        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

	        javaMailSender.setHost("smtp.gmail.com");
	        javaMailSender.setJavaMailProperties(getMailProperties());
	        javaMailSender.setUsername("yangxiangjiang19880805@gmail.com");
	        javaMailSender.setPassword("dreamon9527");

	        return javaMailSender;
	    }

	    private Properties getMailProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("mail.transport.protocol", "smtp");
	        properties.setProperty("mail.smtp.auth", "true");
	        properties.setProperty("mail.smtp.starttls.enable", "true");
	        properties.setProperty("mail.debug", "true");
	        properties.setProperty("mail.smtp.ssl.enable","false");
	        properties.setProperty("mail.test-connection","true");
	        return properties;
	    }
}
