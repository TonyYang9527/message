package com.alpha.message.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProperties {

	@Value("${ali.mail.smtp.host}")
	public String host;

	@Value("${ali.mail.smtp.port}")
	public String port;

	@Value("${ali.mail.smtp.auth}")
	public String auth;

	@Value("${ali.mail.user}")
	public String user;

	@Value("${ali.mail.password}")
	public String password;

	@Value("${ali.mail.smtp.socketFactory.class}")
	public String socketFactory;

	@Value("${ali.mail.smtp.socketFactory.port}")
	public String socketFactoryPort;

	@Value("${ali.mail.from.address}")
	public String fromAddress;

	@Value("${ali.mail.from.name}")
	public String fromName;

	@Value("${ali.mail.debug}")
	public boolean debug;

	@Value("${message.channel.switch.email}")
	public boolean on_off_email;
	

}
