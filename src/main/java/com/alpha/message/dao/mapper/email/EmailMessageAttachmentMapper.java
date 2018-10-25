package com.alpha.message.dao.mapper.email;


public interface EmailMessageAttachmentMapper {

	  void  insertSelective(Long messageId, String name, String path);
}
