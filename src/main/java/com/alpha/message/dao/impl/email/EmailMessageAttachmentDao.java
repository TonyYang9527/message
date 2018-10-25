package com.alpha.message.dao.impl.email;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.email.EmailMessageAttachment;
import com.alpha.message.dao.mapper.email.EmailMessageAttachmentMapper;

@Repository
public class EmailMessageAttachmentDao implements EmailMessageAttachmentMapper {

	@Override
	public void  insertSelective(Long messageId, String name, String path) {
		
        EmailMessageAttachment emailMessageAttachment = new EmailMessageAttachment();
        
        emailMessageAttachment.setEmailMessageId(messageId);
        emailMessageAttachment.setName(name);
        emailMessageAttachment.setPath(path);
        emailMessageAttachment.setCreatedTime(new Date());
        
        emailMessageAttachment.saveOrUpdate();
	}

}
