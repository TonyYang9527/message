package com.alpha.message.dao.mapper.sms;

import java.util.List;
import java.util.Set;

import com.alpha.message.dao.entiy.sms.SmsMessageTemplate;

public interface SmsMessageTemplateMapper {

	SmsMessageTemplate selectByPrimaryKey(Long id);

	SmsMessageTemplate  selectByUniqueName(String  name);
	
	void deleteByPrimaryKey(Long id);

	void insertSelective(SmsMessageTemplate  template);

	void updateByPrimaryKeySelective(SmsMessageTemplate template);

	void changeDeleted(Long id, int flag, String deletedBy);

	boolean checkDeleted(Long id, int deleted);
	
	void changeState(Long id, int state, String updatedBy);

	boolean checkState(Long id, int state);
	
	List<SmsMessageTemplate> listSmsTemplate(String name ,Set<Integer> states);
}
