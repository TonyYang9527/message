package com.alpha.message.service.wrap.sms;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.ImmediateType;
import com.alpha.message.common.enums.SendState;
import com.alpha.message.dao.entiy.sms.SmsMessage;
import com.alpha.message.dao.entiy.sms.SmsMessageTemplate;
import com.alpha.message.dao.mapper.sms.SmsMessageMapper;
import com.alpha.message.dao.mapper.sms.SmsMessageTemplateMapper;
import com.alpha.message.ifacade.request.sms.SendSmsRequest;
import com.google.common.base.Preconditions;

@Service
public class SmsMessageService {

	@Autowired
	protected SmsMessageMapper smsMessageMapper;

	@Autowired
	protected SmsMessageTemplateMapper templateMapper;

	public SmsMessage getSmsMessageById(Long id) {
		Preconditions.checkNotNull(id);
		return smsMessageMapper.selectByPrimaryKey(id);
	}

	/**
	 * save SmsMessage
	 *
	 * @param req
	 *            the req
	 * @return the long
	 */
	public SmsMessage saveSmsMessageByReq(SendSmsRequest req) {
		String  templateName = req.getTemplateName();
		SmsMessageTemplate template = templateMapper.selectByUniqueName(templateName);
		SmsMessage msg = buildSmsMessage(template, req);
		smsMessageMapper.insertSelective(msg);
		return msg;
	}

	/**
	 * build SmsMessage
	 *
	 * @param template
	 * @param req
	 * @return the sms message
	 */
	private SmsMessage buildSmsMessage(SmsMessageTemplate template, SendSmsRequest req) {
		SmsMessage message = new SmsMessage();
		message.setSmsMessageTemplateId(template.getId());
		message.setMobiles(req.getMobiles());
		message.setPriority(template.getPriority());
		message.setState(SendState.TOBESEND.getValue());
		if (req.getScheduleUtcTime() == null) {
			message.setImmediate(ImmediateType.IMMEDIATE.getValue());
			message.setScheduleTime(new Date());
		} else {
			message.setImmediate(ImmediateType.SCHEDULE.getValue());
			message.setScheduleTime(new Date(req.getScheduleUtcTime()));
		}
		message.setExpiredTime(new Date(req.getExpiredUtcTime()));
		message.setCreatedBy(req.getUserId());
		message.setCreatedTime(new Date());
		return message;
	}

	public boolean updateSmsSendResult(Long id, boolean success, String retCode, Date sentDate) {
		SmsMessage smsMessage = new SmsMessage();
		smsMessage.setId(id);
		smsMessage.setSentResult(retCode);
		smsMessage.setSentTime(sentDate);
		if (success) {
			smsMessage.setState(SendState.SENT.getValue());
		} else {
			smsMessage.setState(SendState.FAILED.getValue());
		}
		smsMessageMapper.updateByPrimaryKeySelective(smsMessage);
		return true;
	}

}
