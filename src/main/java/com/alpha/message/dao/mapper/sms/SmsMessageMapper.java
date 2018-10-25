package com.alpha.message.dao.mapper.sms;

import com.alpha.message.dao.entiy.sms.SmsMessage;

public interface SmsMessageMapper {

	void insertSelective(SmsMessage message);

	SmsMessage selectByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(Long id, Integer state);

	void updateByPrimaryKeySelective(SmsMessage message);
}
