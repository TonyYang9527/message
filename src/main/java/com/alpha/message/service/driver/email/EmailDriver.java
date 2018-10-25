package com.alpha.message.service.driver.email;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.ChannelType;
import com.alpha.message.context.AppProperties;
import com.alpha.message.context.EmailProperties;
import com.alpha.message.service.driver.BasicDriver;
import com.alpha.message.service.driver.entity.request.ChannelReq;
import com.alpha.message.service.driver.entity.request.EmailChannelReq;
import com.alpha.message.service.driver.entity.response.ChannelResp;
import com.alpha.message.service.wrap.user.UserEmailWhitelistService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailDriver implements BasicDriver {

	@Autowired
	private EmailSessionManager emailManager;

	private JavaMailSender emailTemplate;

	@Autowired
	private AppProperties props;

	@Autowired
	private UserEmailWhitelistService whitelistService;

	@Autowired
	private EmailProperties email;

	@Autowired
	public EmailDriver(JavaMailSender emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	@Override
	public ChannelType getType() {
		return ChannelType.EMAIL;
	}

	@Override
	public ChannelResp send(ChannelReq req) {
		ChannelResp resp = null;
		if (email.on_off_email) {
			log.info(" EmailDriver  message  channel  email  ON-OFF ", email.on_off_email);
			return new ChannelResp(0L, ChannelType.EMAIL, true, null, "success");
		}
		if (props.envName.equalsIgnoreCase("SIT") || props.envName.equalsIgnoreCase("production")) {
			resp = gmail(req);
		} else {
			resp = new ChannelResp(0L, ChannelType.EMAIL, true, null, "success");
		}
		log.info(" EmailDriver <<<<Gmail>>>> Server Send  Email. Request:{} ,Response:{} ", req, resp);
		return resp;
	}

	public ChannelResp aliyun(ChannelReq req) {
		EmailChannelReq emailReq = (EmailChannelReq) req;
		Boolean isSuccess = false;
		String result = null;

		String subject = emailReq.getTitle();
		String content = emailReq.getContent();
		String[] to = emailReq.getCheckSkip() ? emailReq.getTarget() : filterUnverifiedEmail(emailReq.getTarget());

		if (ArrayUtils.isEmpty(to)) {
			return new ChannelResp(emailReq.getId(), ChannelType.EMAIL, true, null, "success");
		}
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

	public ChannelResp gmail(ChannelReq req) {
		EmailChannelReq emailReq = (EmailChannelReq) req;
		Boolean isSuccess = false;
		String result = null;
		try {
			String from = emailReq.getFrom();
			String senderName = emailReq.getSenderName();
			String text = emailReq.getContent();
			Boolean isHtmlText = emailReq.getIsHtmlText();
			String subject = emailReq.getTitle();
			String[] to = emailReq.getCheckSkip() ? emailReq.getTarget() : filterUnverifiedEmail(emailReq.getTarget());

			if (ArrayUtils.isEmpty(to)) {
				return new ChannelResp(emailReq.getId(), ChannelType.EMAIL, true, null, "success");
			}
			MimeMessage message = emailTemplate.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, MimeUtility.encodeText(senderName, "UTF-8", "B")));
			helper.setSubject(subject);
			helper.setText(text, isHtmlText);
			if (to != null && to.length > 0) {
				helper.setTo(to);
			}
			helper.setSentDate(new Date());

			emailTemplate.send(message);
			isSuccess = true;
			result = "success";
			log.info("EmailDriver Send Success message :{} ", message);
		} catch (Exception e) {
			isSuccess = false;
			result = "false";
			log.error("EmailDriver  Send Email   Failed  Request:{} ,Exception :{}", emailReq, e);
		}
		return new ChannelResp(emailReq.getId(), ChannelType.EMAIL, isSuccess, null, result);
	}

	private String[] filterUnverifiedEmail(String[] to) {
		if (ArrayUtils.isEmpty(to)) {
			return null;
		}
		return whitelistService.filterUnverifiedEmail(to);
	}

}
