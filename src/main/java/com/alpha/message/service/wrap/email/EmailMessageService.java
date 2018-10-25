package com.alpha.message.service.wrap.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.ImmediateType;
import com.alpha.message.common.enums.SendState;
import com.alpha.message.dao.entiy.email.EmailMessage;
import com.alpha.message.dao.entiy.email.EmailMessageTemplate;
import com.alpha.message.dao.mapper.email.EmailMessageAttachmentMapper;
import com.alpha.message.dao.mapper.email.EmailMessageMapper;
import com.alpha.message.dao.mapper.email.EmailMessagePropertyMapper;
import com.alpha.message.dao.mapper.email.EmailMessageTemplateMapper;
import com.alpha.message.ifacade.request.email.SendEmailRequest;
import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailMessageService {
	@Autowired
	protected EmailMessageMapper emailMessageMapper;
	@Autowired
	protected EmailMessageAttachmentMapper attachmentMapper;
	@Autowired
	protected EmailMessagePropertyMapper propertyMapper;
	@Autowired
	protected EmailMessageTemplateMapper templateMapper;

	/**
	 * Save EmailMessage
	 * 
	 * @param req
	 * @return the long
	 */
	public EmailMessage saveEmailMessageByReq(SendEmailRequest req) {
		String templateName = req.getTemplateName();
		EmailMessageTemplate template = templateMapper.selectByUniqueName(templateName);
		EmailMessage msg = null;
		if (template != null) {
			msg = buildEmailMessage(template, req);
			emailMessageMapper.insertSelective(msg);
		} else {
			log.error(" Email Message  Template  is null .");
		}

		return msg;
	}

	/**
	 * build EmailMessage
	 * 
	 * @param template
	 * @param req
	 * @return the email message
	 */
	private EmailMessage buildEmailMessage(EmailMessageTemplate template, SendEmailRequest req) {
		EmailMessage message = new EmailMessage();

		message.setEmailMessageTemplateId(template.getId());
		message.setToAddress(req.getToAddresses());
		message.setCcAddress(req.getCcAddresses());
		message.setBccAddress(req.getBccAddresses());
		message.setPriority(template.getPriority());
		message.setState(SendState.TOBESEND.getValue());
		// ScheduleUtcTime is Null , this message is Instant Message
		// instant Message Flag , 0: Instant Message , 1 : Schedule message
		message.setImmediate(ImmediateType.IMMEDIATE.getValue());
		message.setScheduleTime(new Date());
		message.setExpiredTime(new Date());
		
		if (req.getUserId() == null) {
			message.setCreatedBy("admin");
		} else {
			message.setCreatedBy(req.getUserId());
		}

		message.setCreatedTime(new Date());
		return message;
	}

	public EmailMessage getEmailMessageById(Long id) {
		Preconditions.checkNotNull(id);
		return emailMessageMapper.selectByPrimaryKey(id);
	}

	/**
	 * Update Email message State
	 * 
	 * @param id
	 * @param success
	 * @return true, if successful
	 */
	public boolean updateEmailSendResult(Long id, boolean isSuccess) {
		Preconditions.checkNotNull(id);
		Integer state = isSuccess ? SendState.SENT.getValue() : SendState.FAILED.getValue();
		emailMessageMapper.updateByPrimaryKeySelective(id, state);
		return true;
	}
}
