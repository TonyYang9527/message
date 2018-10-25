package com.alpha.message.dao.mapper.email;

import com.alpha.message.dao.entiy.email.EmailMessageProperty;

public interface EmailMessagePropertyMapper {

	void insertSelective(EmailMessageProperty emailMessageProperty);

	EmailMessageProperty  selectByEmailMessageId(Long emailMessageId);
}
