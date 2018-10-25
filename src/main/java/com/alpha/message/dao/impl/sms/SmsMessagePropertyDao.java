package com.alpha.message.dao.impl.sms;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.sms.SmsMessageProperty;
import com.alpha.message.dao.mapper.sms.SmsMessagePropertyMapper;

@Repository
public class SmsMessagePropertyDao implements SmsMessagePropertyMapper {

	@Override
	public void insertSelective(SmsMessageProperty smsMessageProperty) {
		smsMessageProperty.saveOrUpdate();
		
	}
	@Override
	public SmsMessageProperty selectBySmsMessageId(Long smsMessageId) {
		return SmsMessageProperty.where(" email_message_id =?  ", smsMessageId).first();
	}

}
