package com.alpha.message.dao.mapper.push;

import com.alpha.message.dao.entiy.push.PushMessage;

public interface PushMessageMapper {

	   void  insertSelective(PushMessage message) throws Exception ;
	   PushMessage  selectByPrimaryKey(Long  id );
	   void   updateByPrimaryKeySelective(Long id, Integer state);
}
