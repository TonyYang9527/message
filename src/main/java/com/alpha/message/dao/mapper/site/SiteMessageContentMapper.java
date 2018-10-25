package com.alpha.message.dao.mapper.site;

import com.alpha.message.dao.entiy.site.SiteMessageContent;

public interface SiteMessageContentMapper {
	void insertSelective(SiteMessageContent message);

	SiteMessageContent selectByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(Long id, Integer state);

	void updateByPrimaryKeySelective(SiteMessageContent message);
}
