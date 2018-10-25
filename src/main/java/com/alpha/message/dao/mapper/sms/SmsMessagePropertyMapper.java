package com.alpha.message.dao.mapper.sms;

import com.alpha.message.dao.entiy.sms.SmsMessageProperty;

public interface SmsMessagePropertyMapper {

	void insertSelective(SmsMessageProperty   smsMessageProperty);

	SmsMessageProperty selectBySmsMessageId(Long smsMessageId);
}
