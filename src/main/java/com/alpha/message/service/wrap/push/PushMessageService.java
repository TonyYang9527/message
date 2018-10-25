package com.alpha.message.service.wrap.push;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alpha.message.common.enums.AudienceType;
import com.alpha.message.common.enums.ImmediateType;
import com.alpha.message.common.enums.SendState;
import com.alpha.message.common.utils.Base64Utils;
import com.alpha.message.dao.entiy.push.PushMessage;
import com.alpha.message.dao.entiy.push.PushMessageTemplate;
import com.alpha.message.dao.mapper.push.PushMessageMapper;
import com.alpha.message.dao.mapper.push.PushMessagePropertyMapper;
import com.alpha.message.dao.mapper.push.PushMessageTemplateMapper;
import com.alpha.message.ifacade.request.push.SendPushRequest;
import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushMessageService {

	@Autowired
	protected PushMessageMapper pushMessageMapper;

	@Autowired
	protected PushMessagePropertyMapper propertyMapper;
	@Autowired
	protected PushMessageTemplateMapper templateMapper;

	/**
	 * Save PushMessage
	 * 
	 * @param req
	 * @return the long
	 */
	public PushMessage savePushMessageByReq(SendPushRequest req) {
		PushMessage msg = null;
		try {
			String templateName = req.getTemplateName();
			PushMessageTemplate template = templateMapper.selectByUniqueName(templateName);
			msg = buildPushMessage(template, req);
			pushMessageMapper.insertSelective(msg);
		} catch (Exception e) {
			log.error("savePushMessageByReq  Exception: {} ", e);
			return msg;
		}

		return msg;
	}

	/**
	 * build PushMessage
	 * 
	 * @param template
	 * @param req
	 * @return the email message
	 */
	private PushMessage buildPushMessage(PushMessageTemplate template, SendPushRequest req) {
		PushMessage message = new PushMessage();

		message.setPushMessageTemplateId(template.getId());
		message.setPriority(template.getPriority());
		message.setState(SendState.DRAFT.getValue());
		message.setImmediate(ImmediateType.IMMEDIATE.getValue());
		message.setScheduleTime(new Date());
		message.setReceivers(req.getReceivers());
		message.setAudienceType(AudienceType.REGID.getValue());
		
		String extrasJson = JSON.toJSONString(req.getExtras());
		String encodeExtras = Base64Utils.encode(extrasJson);
		
		message.setExtras(encodeExtras);
		message.setApplication(req.getApplication());
		message.setCreatedBy(StringUtils.isBlank(req.getCreatedBy()) ? "admin" : req.getCreatedBy());
		message.setCreatedTime(new Date());

		return message;
	}

	public PushMessage getPushMessageById(Long id) {
		Preconditions.checkNotNull(id);
		return pushMessageMapper.selectByPrimaryKey(id);
	}

	/**
	 * Update Push message State
	 * 
	 * @param id
	 * @param success
	 * @return true, if successful
	 */
	public boolean updatePushSendResult(Long id, boolean isSuccess) {
		Preconditions.checkNotNull(id);
		Integer state = isSuccess ? SendState.SENT.getValue() : SendState.FAILED.getValue();
		pushMessageMapper.updateByPrimaryKeySelective(id, state);
		return true;
	}
}
