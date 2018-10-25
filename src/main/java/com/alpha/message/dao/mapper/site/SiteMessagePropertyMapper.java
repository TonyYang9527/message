package com.alpha.message.dao.mapper.site;

import com.alpha.message.dao.entiy.site.SiteMessageProperty;

public interface SiteMessagePropertyMapper {
	void insertSelective( SiteMessageProperty   siteMessageProperty);

	SiteMessageProperty selectBySiteMessageId(Long siteMessageId);
}
