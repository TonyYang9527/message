package com.alpha.message.dao.mapper.push;

import com.alpha.message.dao.entiy.push.PushMessageProperty;

public interface PushMessagePropertyMapper {

	void insertSelective(PushMessageProperty  pushMessageProperty);

	PushMessageProperty selectByPushMessageId(Long pushMessageId);
}
