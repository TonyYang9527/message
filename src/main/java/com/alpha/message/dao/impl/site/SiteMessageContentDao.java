package com.alpha.message.dao.impl.site;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.alpha.message.dao.entiy.site.SiteMessageContent;
import com.alpha.message.dao.mapper.site.SiteMessageContentMapper;

@Repository
public class SiteMessageContentDao implements SiteMessageContentMapper {

	@Override
	public void insertSelective(SiteMessageContent message) {
		message.saveOrUpdate();
	}

	@Override
	public SiteMessageContent selectByPrimaryKey(Long id) {
		return SiteMessageContent.findById(id);
	}

	@Override
	public void updateByPrimaryKeySelective(Long id, Integer state) {
		SiteMessageContent siteMessageContent = this.selectByPrimaryKey(id);
		if (siteMessageContent != null) {
			siteMessageContent.saveOrUpdate();
		}

	}

	@Override
	public void updateByPrimaryKeySelective(SiteMessageContent message) {
		SiteMessageContent siteMessageContent = this.selectByPrimaryKey(message.getId());
		if (siteMessageContent != null) {
			message.setUpdatedTime(new Date());
			message.saveOrUpdate();
		}

	}

}
