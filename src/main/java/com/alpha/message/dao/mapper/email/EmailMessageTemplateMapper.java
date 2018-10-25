package com.alpha.message.dao.mapper.email;

import java.util.List;
import java.util.Set;

import com.alpha.message.dao.entiy.email.EmailMessageTemplate;

public interface EmailMessageTemplateMapper {

	EmailMessageTemplate selectByPrimaryKey(Long id);
	
	EmailMessageTemplate selectByUniqueName(String  name);

	void deleteByPrimaryKey(Long id);

	void insertSelective(EmailMessageTemplate template);

	void updateByPrimaryKeySelective(EmailMessageTemplate template);

	void changeDeleted(Long id, int flag, String deletedBy);

	boolean checkDeleted(Long id, int deleted);
	
	void changeState(Long id, int state, String updatedBy);

	boolean checkState(Long id, int state);
	
	List<EmailMessageTemplate> listEmailTemplate(String name ,Set<Integer> states);
}
