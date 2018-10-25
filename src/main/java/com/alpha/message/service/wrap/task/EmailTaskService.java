package com.alpha.message.service.wrap.task;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.utils.JsonUtil;
import com.alpha.message.common.utils.MessageAssembleUtil;
import com.alpha.message.dao.entiy.email.EmailMessage;
import com.alpha.message.dao.entiy.email.EmailMessageProperty;
import com.alpha.message.dao.entiy.email.EmailMessageTemplate;
import com.alpha.message.service.driver.entity.request.EmailChannelReq;
import com.alpha.message.service.driver.entity.response.ChannelResp;
import com.alpha.message.service.wrap.channel.ChannelServices;
import com.alpha.message.service.wrap.email.EmailMessagePropertyService;
import com.alpha.message.service.wrap.email.EmailMessageService;
import com.alpha.message.service.wrap.email.EmailMessageTemplateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailTaskService {

	private static final String SPILT_REGEX = "\\|";
	@Autowired
	private EmailMessagePropertyService emailMessagePropertyService;
	@Autowired
	private EmailMessageService emailMessageService;
	@Autowired
	private EmailMessageTemplateService emailMessageTemplateService;
	@Autowired
	private ChannelServices channelServices;

	public ChannelResp sendTask(Long messageId, Boolean checkSkip) throws IOException {
		// 1. get email message
		EmailMessage eamilMessage = getEmailMessage(messageId);
		// 2. get message template
		EmailMessageTemplate template = getEmailMessageTemplate(eamilMessage);
		// 3. get Email message Key/Value
		Map<String, Object> props = getProps(eamilMessage);
		// 4. build full messages & send gateway
		Long id = eamilMessage.getId();
		String content = MessageAssembleUtil.mustacheContent(template.getName(), template.getContent(), props);
		String toAddress = eamilMessage.getToAddress();
		String from = template.getFromAddress();
		String senderName = template.getSenderName();
		Boolean isHtmlText = true;
		List<File> attachmentFiles = null;
		String title = null;
		log.info("EmailTaskService  ************props :{} ", props);
		if (!props.isEmpty() && props.containsKey("title")) {
			title = props.get("title").toString();
		} else {
			title = MessageAssembleUtil.mustacheContent(template.getName(), template.getTitle(), props);
		}
		ChannelResp channelResp = sendEmailToChannel(id, content, toAddress, attachmentFiles, from, senderName, title,
				isHtmlText, checkSkip);
		// 5. Update Email Message State
		saveEmailSendResult(channelResp);
		log.info("EmailTask   channelResp :{} ", channelResp);
		return channelResp;
	}

	/**
	 * build Channel Request & Send Request
	 * 
	 * @param id
	 * @param content
	 * @param toAddress
	 * @param isHtmlText
	 * @param title
	 * @param bccAddress
	 * @param ccAddress
	 * @param senderName
	 * @param from
	 * @return the channel resp
	 */
	private ChannelResp sendEmailToChannel(Long id, String content, String toAddress, List<File> attachmentFiles,
			String from, String senderName, String title, Boolean isHtmlText, Boolean checkSkip) {
		String[] emailArrs = null;
		if (toAddress != null && toAddress.length() > 0) {
			emailArrs = toAddress.split(SPILT_REGEX);
		}

		EmailChannelReq req = new EmailChannelReq(id, emailArrs, content, from, title, senderName, isHtmlText,
				attachmentFiles, checkSkip);
		log.info(" build SendEmail  Channel Request :{}   ", req);
		ChannelResp resp = channelServices.request(req);
		log.info(" Channel SendEmail  Response :{}", resp);
		return resp;
	}

	/**
	 * Get EmailMessage.
	 * 
	 * @param queuePriorityVo
	 * @return emailMessage
	 */
	private EmailMessage getEmailMessage(Long messageId) {
		return emailMessageService.getEmailMessageById(messageId);
	}

	/**
	 * Get EmailMessageTemplate.
	 * 
	 * @param EmailMessage
	 * @return EmailMessageTemplate
	 */
	private EmailMessageTemplate getEmailMessageTemplate(EmailMessage emailMessage) {
		return emailMessageTemplateService.getMsgTemplate(emailMessage.getEmailMessageTemplateId());
	}

	/**
	 * Get email param Key/Value .
	 * 
	 * @param EmailMessage
	 * @return the props
	 */
	private Map<String, Object> getProps(EmailMessage emailMessage) {
		EmailMessageProperty property = emailMessagePropertyService.getEmailPropsByMessageId(emailMessage.getId());
		if (property == null || StringUtils.isBlank(property.getPropValue())) {
			return new HashMap<String, Object>();
		}
		return JsonUtil.map(property.getPropValue());
	}

	/**
	 * Update Email message State
	 * 
	 * @param sendEmailRequest
	 */
	private void saveEmailSendResult(ChannelResp channelResp) {
		if (channelResp != null) {
			emailMessageService.updateEmailSendResult(channelResp.getId(), channelResp.isSuccess());
		}

	}
}
