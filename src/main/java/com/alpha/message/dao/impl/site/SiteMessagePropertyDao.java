package com.alpha.message.dao.impl.site;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.site.SiteMessageProperty;
import com.alpha.message.dao.mapper.site.SiteMessagePropertyMapper;


@Repository
public class SiteMessagePropertyDao implements SiteMessagePropertyMapper {

	@Override
	public void insertSelective(SiteMessageProperty siteMessageProperty) {
		siteMessageProperty.saveOrUpdate();

	}

	@Override
	public SiteMessageProperty selectBySiteMessageId(Long  siteMessageId) {
		return SiteMessageProperty.where(" site_message_id =?  ", siteMessageId).first();
	}

}
