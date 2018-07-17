package com.alpha.message.service.channel.proxy;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailProxy {

	private JavaMailSender emailTemplate;
	
	@Autowired
	public EmailProxy(JavaMailSender emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	/**
	 * Send Email
	 * 
	 * @param from
	 * @param senderName
	 * @param text
	 * @param isHtmlText
	 * @param subject
	 * @param cc
	 * @param to
	 * @param bcc
	 * @param sentDate
	 * @param attachmentFiles
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendEmail(String from, String senderName, String text, boolean isHtmlText, String subject, String[] cc, String[] to,
			String[] bcc, List<File> attachmentFiles) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = emailTemplate.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(new InternetAddress(from, MimeUtility.encodeText(senderName, "UTF-8", "B")));
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, isHtmlText);
		if (cc != null && cc.length > 0) {
			helper.setCc(cc);
		}
		if (bcc != null && bcc.length > 0) {
			helper.setBcc(bcc);
		}
		helper.setSentDate(new Date());
		if (attachmentFiles != null && attachmentFiles.size() > 0) {
			for (File file : attachmentFiles) {
				FileSystemResource fileSystemResource = new FileSystemResource(file);
				helper.addAttachment(file.getName(), fileSystemResource);
			}
		}
		emailTemplate.send(message);
	}
}
