package com.alpha.message.dao.impl.push;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.push.PushMessage;
import com.alpha.message.dao.mapper.push.PushMessageMapper;

@Repository
public class PushMessageDao implements PushMessageMapper {

	@Override
	public void insertSelective(PushMessage message) {
		message.saveOrUpdate();
	}

	@Override
	public PushMessage selectByPrimaryKey(Long id) {
		return PushMessage.findById(id);
	}

	@Override
	public void updateByPrimaryKeySelective(Long id, Integer state) {
		PushMessage pushMessage = this.selectByPrimaryKey(id);
		if (pushMessage != null) {
			pushMessage.setState(state);
			pushMessage.setSentTime(new Date());
			pushMessage.saveOrUpdate();
		}

	}

}
