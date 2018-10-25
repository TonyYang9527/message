package com.alpha.message.service.wrap.site;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alpha.message.dao.entiy.site.SiteMessageProperty;
import com.alpha.message.dao.mapper.site.SiteMessagePropertyMapper;
import com.google.common.base.Preconditions;

@Service
public class SiteMessagePropertyService {

	@Autowired
	protected SiteMessagePropertyMapper propertyMapper;

	/**
	 * create Site_message_property
	 * 
	 * @param request
	 */
	public void saveSiteMessageProperty(Long messageId, Map<String, Object> props) {
		SiteMessageProperty siteMessageProperty = new SiteMessageProperty();
		siteMessageProperty.setSiteMessageId(messageId);
		siteMessageProperty.setPropValue(JSON.toJSONString(props));
		siteMessageProperty.setCreatedTime(new Date());
		propertyMapper.insertSelective(siteMessageProperty);
	}

	/**
	 * Get Site params key/value by SiteMessageId
	 * 
	 * @param messageId
	 * @return the props by message id
	 */
	public SiteMessageProperty selectBySiteMessageId(Long messageId) {
		Preconditions.checkNotNull(messageId);
		return propertyMapper.selectBySiteMessageId(messageId);
	}

}
