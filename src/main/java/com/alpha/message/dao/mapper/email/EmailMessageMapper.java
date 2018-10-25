package com.alpha.message.dao.mapper.email;

import com.alpha.message.dao.entiy.email.EmailMessage;

public interface EmailMessageMapper {
	   void  insertSelective(EmailMessage message);
	   EmailMessage  selectByPrimaryKey(Long  id );
	   void   updateByPrimaryKeySelective(Long id, Integer state);
}
