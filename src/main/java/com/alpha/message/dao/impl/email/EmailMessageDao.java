package com.alpha.message.dao.impl.email;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.email.EmailMessage;
import com.alpha.message.dao.mapper.email.EmailMessageMapper;

@Repository
public class EmailMessageDao implements EmailMessageMapper {

	@Override
	public void insertSelective(EmailMessage message) {
		message.saveOrUpdate();
	}

	@Override
	public EmailMessage selectByPrimaryKey(Long id) {
		return EmailMessage.findById(id);
	}

	@Override
	public void updateByPrimaryKeySelective(Long id, Integer state) {
		EmailMessage emailMessage = this.selectByPrimaryKey(id);
		if (emailMessage != null) {
			emailMessage.setState(state);
			emailMessage.setSentTime(new Date());
			emailMessage.saveOrUpdate();
		}
	}

}
