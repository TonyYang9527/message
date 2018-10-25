package com.alpha.message.service.wrap.push;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alpha.message.common.utils.Base64Utils;
import com.alpha.message.dao.entiy.push.PushMessageProperty;
import com.alpha.message.dao.mapper.push.PushMessagePropertyMapper;
import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushMessagePropertyService {

	@Autowired
	protected PushMessagePropertyMapper propertyMapper;

	/**
	 * create push_message_property
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	public void savePushMessageProperty(Long messageId, Map<String, Object> props) {
		PushMessageProperty pushMessageProperty = new PushMessageProperty();
		pushMessageProperty.setPushMessageId(messageId);
		String propsJson = JSON.toJSONString(props);
		String encodeProps = Base64Utils.encode(propsJson);
		log.info("PushMessagePropertyService propsJson:{},encodeProps:{} ", propsJson, encodeProps);
		pushMessageProperty.setPropValue(encodeProps);
		pushMessageProperty.setCreatedTime(new Date());
		propertyMapper.insertSelective(pushMessageProperty);
	}

	/**
	 * Get Push params key/value by EmailMessageId
	 * 
	 * @param messageId
	 * @return the email props by message id
	 */
	public PushMessageProperty selectByPushMessageId(Long messageId) {
		Preconditions.checkNotNull(messageId);
		return propertyMapper.selectByPushMessageId(messageId);
	}
}
