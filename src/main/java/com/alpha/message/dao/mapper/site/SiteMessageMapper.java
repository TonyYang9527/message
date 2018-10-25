package com.alpha.message.dao.mapper.site;

import java.util.List;
import java.util.Set;

import com.alpha.message.dao.entiy.site.SiteMessage;
import com.alpha.message.dao.entiy.site.SiteMessageRecord;

public interface SiteMessageMapper {

	void insertSelective(SiteMessage message);

	SiteMessage selectByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(Long id, Integer state);

	void updateByPrimaryKeySelective(SiteMessage message);

	List<SiteMessage> listSiteMessage(Set<Integer> states, Set<Integer> types);

	List<SiteMessage> getUserSiteMessage(Long userId, Set<Integer> states);

	SiteMessageRecord countUserSiteMsgTotal(Long userId, Set<Integer> states);
}
