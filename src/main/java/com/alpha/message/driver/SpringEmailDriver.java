//package com.bmo.message.driver.email;
//
//import java.util.Date;
//
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeUtility;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import com.bmo.message.common.enums.ChannelType;
//import com.bmo.message.driver.BasicDriver;
//import com.bmo.message.driver.entity.request.ChannelReq;
//import com.bmo.message.driver.entity.request.EmailChannelReq;
//import com.bmo.message.driver.entity.response.ChannelResp;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class SpringEmailDriver implements BasicDriver {
//
//	private JavaMailSender emailTemplate;
//
//	@Autowired
//	public SpringEmailDriver(JavaMailSender emailTemplate) {
//		this.emailTemplate = emailTemplate;
//	}
//
//	@Override
//	public ChannelType getType() {
//		return ChannelType.EMAIL;
//	}
//
//	@Override
//	public ChannelResp send(ChannelReq req) {
//
//		EmailChannelReq emailReq = (EmailChannelReq) req;
//		Boolean isSuccess = false;
//		String result = null;
//		try {
//			String from = emailReq.getFrom();
//			String senderName = emailReq.getSenderName();
//			String text = emailReq.getContent();
//			Boolean isHtmlText = emailReq.getIsHtmlText();
//			String subject = emailReq.getTitle();
//			String[] cc = emailReq.getCc();
//			String[] bcc = emailReq.getBcc();
//			String[] to = emailReq.getTarget();
//
//			MimeMessage message = emailTemplate.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message, true);
//			helper.setFrom(new InternetAddress(from, MimeUtility.encodeText(senderName, "UTF-8", "B")));
//			helper.setTo(to);
//			helper.setSubject(subject);
//			helper.setText(text, isHtmlText);
//
//			if (cc != null && cc.length > 0) {
//				helper.setCc(cc);
//			}
//			if (bcc != null && bcc.length > 0) {
//				helper.setBcc(bcc);
//			}
//			helper.setSentDate(new Date());
//
//			emailTemplate.send(message);
//			isSuccess = true;
//			result = "success";
//			log.info("EmailDriver Send Success message :{} ", message);
//		} catch (Exception e) {
//			isSuccess = false;
//			result = "false";
//			log.error("EmailDriver  Send Email   Failed  Request:{} ,Exception :{}", emailReq, e);
//		}
//
//		return new ChannelResp(emailReq.getId(), ChannelType.EMAIL, isSuccess, null, result);
//	}
//
//}
