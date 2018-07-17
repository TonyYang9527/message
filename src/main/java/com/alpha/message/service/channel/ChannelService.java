package com.alpha.message.service.channel;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.model.enums.MsgType;
import com.alpha.message.service.channel.entity.ChannelReq;
import com.alpha.message.service.channel.entity.ChannelResp;
import com.alpha.message.service.channel.entity.email.EmailChannelReq;
import com.alpha.message.service.channel.proxy.EmailProxy;

@Service
public class ChannelService {

	/** SMS SIGNATURE. */
//	private static final String SIGNATURE = "BWOIL";

	@Autowired
	protected EmailProxy emailProxy;

	public ChannelResp request(ChannelReq req) {
		MsgType msgType = req.getMsgType();
		ChannelResp resp = null;
		switch (msgType) {
		case SMS:
			resp = sendSms(req);
			break;
		case EMAIL:
			resp = sendEmail(req);
			break;
		case PUSH:
			resp = sendPush(req);
			break;
		case SITE_MSG:
			break;
		}
		return resp;
	}

	private ChannelResp sendEmail(ChannelReq req) {
		
		EmailChannelReq emailReq = (EmailChannelReq) req;
		Boolean isSuccess = false;
		String result = null;
		String from = emailReq.getFrom();
		String senderName = emailReq.getSenderName();
		String text = emailReq.getContent();
		Boolean isHtmlText = emailReq.getIsHtmlText();
		String subject = emailReq.getTitle();
		String[] cc = emailReq.getCc();
		String[] bcc = emailReq.getBcc();
		String[] to = emailReq.getTarget();
		List<File> attachmentFiles = null;
		try {
			emailProxy.sendEmail(from, senderName, text, isHtmlText, subject, cc, to, bcc, attachmentFiles);
			isSuccess = true;
			result = "success";

		} catch (Exception e) {
			isSuccess = false;
			result = "false";
		}
		ChannelResp resp = new ChannelResp(emailReq.getId(), MsgType.EMAIL, isSuccess, null, result);
		return resp;
	}

	private ChannelResp sendPush(ChannelReq req) {
		return null;
	}

	private ChannelResp sendSms(ChannelReq req) {
		return null;
	}

}
