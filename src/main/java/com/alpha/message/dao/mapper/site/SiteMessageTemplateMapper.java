package com.alpha.message.dao.mapper.site;

import java.util.List;
import java.util.Set;

import com.alpha.message.dao.entiy.site.SiteMessageTemplate;

public interface SiteMessageTemplateMapper {

	SiteMessageTemplate selectByPrimaryKey(Long id);

	SiteMessageTemplate  selectByUniqueName(String  name);
	
	void deleteByPrimaryKey(Long id);

	void insertSelective(SiteMessageTemplate  template);

	void updateByPrimaryKeySelective(SiteMessageTemplate template);

	void changeDeleted(Long id, int flag, String deletedBy);

	boolean checkDeleted(Long id, int deleted);
	
	void changeState(Long id, int state, String updatedBy);

	boolean checkState(Long id, int state);
	
	List<SiteMessageTemplate> listSiteTemplate(String name ,Set<Integer> states);
}
