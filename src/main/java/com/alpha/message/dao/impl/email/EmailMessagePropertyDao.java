package com.alpha.message.dao.impl.email;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.email.EmailMessageProperty;
import com.alpha.message.dao.mapper.email.EmailMessagePropertyMapper;

@Repository
public class EmailMessagePropertyDao implements EmailMessagePropertyMapper {

	@Override
	public void  insertSelective(EmailMessageProperty  emailMessageProperty) {
              emailMessageProperty.saveOrUpdate();
	}

	@Override
	public EmailMessageProperty  selectByEmailMessageId(Long emailMessageId) {
		return EmailMessageProperty.where(" email_message_id =?  ", emailMessageId).first();
	}

}
