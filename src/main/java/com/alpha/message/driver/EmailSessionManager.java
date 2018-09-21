package com.bmo.message.driver.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmo.message.configuration.EmailProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailSessionManager {
	@Autowired
	private EmailProperties props;

	private Properties buildEmailProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", this.props.auth);
		props.put("mail.smtp.host", this.props.host);
		props.put("mail.smtp.socketFactory.class", this.props.socketFactory);
		props.put("mail.smtp.socketFactory.port", this.props.socketFactoryPort);
		props.put("mail.smtp.port", this.props.port);
		props.put("mail.user", this.props.user);
		props.put("mail.password", this.props.password);
		return props;
	}

	private Authenticator buildAuthenticator(String name, String pwd) {
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(name, pwd);
			}
		};
		return authenticator;
	}

	public MimeMessage buildMimeMessage() {
		MimeMessage message = null;
		try {
			Properties props = this.buildEmailProperties();
			Authenticator authenticator = this.buildAuthenticator(this.props.user, this.props.password);
			Session mailSession = Session.getInstance(props, authenticator);
			mailSession.setDebug(this.props.debug);
			message = new MimeMessage(mailSession);
			InternetAddress from = new InternetAddress(this.props.fromAddress, this.props.fromName);
			message.setFrom(from);
		} catch (UnsupportedEncodingException e) {
			log.error("*******buildMimeMessage********Exception :{}", e);
		} catch (MessagingException e) {
			log.error("*******buildMimeMessage********Exception :{}", e);
		}
		return message;
	}

}
