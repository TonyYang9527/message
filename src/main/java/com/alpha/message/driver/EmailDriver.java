package com.bmo.message.driver.email;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmo.message.common.enums.ChannelType;
import com.bmo.message.driver.BasicDriver;
import com.bmo.message.driver.entity.request.ChannelReq;
import com.bmo.message.driver.entity.request.EmailChannelReq;
import com.bmo.message.driver.entity.response.ChannelResp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailDriver implements BasicDriver {

	@Autowired
	private EmailSessionManager emailManager;

	@Override
	public ChannelResp send(ChannelReq req) {
		EmailChannelReq emailReq = (EmailChannelReq) req;
		Boolean isSuccess = false;
		String result = null;

		String subject = emailReq.getTitle();
		String content = emailReq.getContent();
		String[] to = emailReq.getTarget();
		String[] cc = emailReq.getCc();
		String[] bcc = emailReq.getBcc();
		try {
			MimeMessage message = emailManager.buildMimeMessage();
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			if (to != null && to.length > 0) {
				InternetAddress[] tos = new InternetAddress[to.length];
				for (int i = 0; i <= to.length - 1; i++) {
					tos[i] = new InternetAddress(to[i]);
				}
				message.setRecipients(MimeMessage.RecipientType.TO, tos);
			}

			if (cc != null && cc.length > 0) {
				InternetAddress[] ccs = new InternetAddress[cc.length];
				for (int i = 0; i <= cc.length - 1; i++) {
					ccs[i] = new InternetAddress(cc[i]);
				}
				message.setRecipients(Message.RecipientType.CC, ccs);
			}

			if (bcc != null && bcc.length > 0) {
				InternetAddress[] bccs = new InternetAddress[bcc.length];
				for (int i = 0; i <= bcc.length - 1; i++) {
					bccs[i] = new InternetAddress(bcc[i]);
				}
				message.setRecipients(Message.RecipientType.BCC, bccs);
			}
			isSuccess = true;
			result = "true";
			log.info("EmailDriver Send Success message :{} ", message);
			Transport.send(message);
		} catch (MessagingException e) {
			result = "false";
			log.error("EmailDriver  Send Email   Failed  Exception :{}", e);
			return new ChannelResp(emailReq.getId(), ChannelType.EMAIL, isSuccess, null, result);
		}
		return new ChannelResp(emailReq.getId(), ChannelType.EMAIL, isSuccess, null, result);
	}

	@Override
	public ChannelType getType() {
		return ChannelType.EMAIL;
	}

}
