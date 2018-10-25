package com.alpha.message.dao.impl.sms;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.sms.SmsMessage;
import com.alpha.message.dao.mapper.sms.SmsMessageMapper;

@Repository
public class SmsMessageDao implements SmsMessageMapper {

	@Override
	public void insertSelective(SmsMessage message) {
		message.saveOrUpdate();

	}

	@Override
	public SmsMessage selectByPrimaryKey(Long id) {
		return SmsMessage.findById(id);
	}

	@Override
	public void updateByPrimaryKeySelective(Long id, Integer state) {
		SmsMessage smsMessage = this.selectByPrimaryKey(id);
		if (smsMessage != null) {
			smsMessage.setState(state);
			smsMessage.setSentTime(new Date());
			smsMessage.saveOrUpdate();
		}
	}

	@Override
	public void updateByPrimaryKeySelective(SmsMessage message) {
		SmsMessage smsMessage = this.selectByPrimaryKey(message.getId());
		if (smsMessage != null) {
			message.saveOrUpdate();
		}
	}

}
