package com.alpha.message.dao.impl.push;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.push.PushMessageProperty;
import com.alpha.message.dao.mapper.push.PushMessagePropertyMapper;

@Repository
public class PushMessagePropertyDao implements PushMessagePropertyMapper {

	@Override
	public void insertSelective(PushMessageProperty pushMessageProperty) {
		pushMessageProperty.saveOrUpdate();
	}

	@Override
	public PushMessageProperty selectByPushMessageId(Long pushMessageId) {
		return PushMessageProperty.where(" push_message_id =?  ", pushMessageId).first();
	}

}
