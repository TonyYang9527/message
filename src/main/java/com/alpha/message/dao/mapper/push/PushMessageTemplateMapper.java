package com.alpha.message.dao.mapper.push;

import java.util.List;
import java.util.Set;

import com.alpha.message.dao.entiy.push.PushMessageTemplate;

public interface PushMessageTemplateMapper {

	PushMessageTemplate selectByPrimaryKey(Long id);

	PushMessageTemplate  selectByUniqueName(String  name);
	
	void deleteByPrimaryKey(Long id);

	void insertSelective(PushMessageTemplate  template);

	void updateByPrimaryKeySelective(PushMessageTemplate template);

	void changeDeleted(Long id, int flag, String deletedBy);

	boolean checkDeleted(Long id, int deleted);
	
	void changeState(Long id, int state, String updatedBy);

	boolean checkState(Long id, int state);
	
	List<PushMessageTemplate> listPushTemplate(String name ,Set<Integer> states);
}
