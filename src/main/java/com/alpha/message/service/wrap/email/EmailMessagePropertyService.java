package com.alpha.message.service.wrap.email;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alpha.message.dao.entiy.email.EmailMessageProperty;
import com.alpha.message.dao.mapper.email.EmailMessagePropertyMapper;
import com.google.common.base.Preconditions;

@Service
public class EmailMessagePropertyService {

	@Autowired
	protected EmailMessagePropertyMapper propertyMapper;

	/**
	 * create email_message_property
	 * 
	 * @param request
	 */
	public void saveEmailMessageProperty(Long messageId, Map<String, Object> props) {
		EmailMessageProperty emailMessageProperty = new EmailMessageProperty();
		emailMessageProperty.setEmailMessageId(messageId);
		// emailMessageProperty.setPropKey(entry.getKey());
		emailMessageProperty.setPropValue(JSON.toJSONString(props));
		emailMessageProperty.setCreatedTime(new Date());
		propertyMapper.insertSelective(emailMessageProperty);

	}

	/**
	 * Get Email params key/value by EmailMessageId
	 * 
	 * @param messageId EmailMessageId
	 * @return the email props by message id
	 */
	public EmailMessageProperty getEmailPropsByMessageId(Long messageId) {
		Preconditions.checkNotNull(messageId);
		return propertyMapper.selectByEmailMessageId(messageId);
	}

}
