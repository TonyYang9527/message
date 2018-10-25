package com.alpha.message.service.wrap.sms;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alpha.message.dao.entiy.sms.SmsMessageProperty;
import com.alpha.message.dao.mapper.sms.SmsMessagePropertyMapper;
import com.google.common.base.Preconditions;


@Service
public class SmsMessagePropertyService {

	@Autowired
	protected  SmsMessagePropertyMapper propertyMapper;
	
	/**
	 * create sms_message_property
	 * 
	 * @param request
	 */
	public void saveSmsMessageProperty(Long messageId, Map<String, Object> props) {
		SmsMessageProperty   smsMessageProperty = new  SmsMessageProperty();
		smsMessageProperty.setSmsMessageId(messageId);
		//smsMessageProperty.setPropKey(entry.getKey());
		smsMessageProperty.setPropValue(JSON.toJSONString(props));
		smsMessageProperty.setCreatedTime(new Date());
		propertyMapper.insertSelective(smsMessageProperty);
	}
	
	
    /**
     * Get Sms params key/value  by   SmsMessageId
     * @param messageId
     * @return the Sms  props by message id
     */
    public SmsMessageProperty selectBySmsMessageId(Long messageId) {
        Preconditions.checkNotNull(messageId);
        return  propertyMapper.selectBySmsMessageId(messageId);
    }
    
}
