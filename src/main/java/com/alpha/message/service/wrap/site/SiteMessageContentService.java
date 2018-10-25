package com.alpha.message.service.wrap.site;

import java.io.IOException;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.utils.MessageAssembleUtil;
import com.alpha.message.dao.entiy.site.SiteMessageContent;
import com.alpha.message.dao.entiy.site.SiteMessageTemplate;
import com.alpha.message.dao.mapper.site.SiteMessageContentMapper;
import com.alpha.message.ifacade.request.site.SiteMsgRequest;
import com.google.common.base.Preconditions;

@Slf4j
@Service
public class SiteMessageContentService {

	@Autowired
	protected SiteMessageContentMapper siteMessageContentMapper;

	@Autowired
	protected SiteMessageTemplateService siteMessageTemplateService;

	/**
	 * Get Site Message Content
	 * 
	 * @param id
	 * @return
	 */
	public SiteMessageContent getSiteMessageContent(Long id) {
		Preconditions.checkNotNull(id);
		SiteMessageContent content = siteMessageContentMapper.selectByPrimaryKey(id);
		return content;
	}

	/**
	 * Site Message Content
	 * 
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	public SiteMessageContent saveSiteContent(SiteMsgRequest request) throws IOException {
		SiteMessageContent siteMessageContent = new SiteMessageContent();
		if (  StringUtils.isNotBlank(request.getTemplateName())) {	
			SiteMessageTemplate template = siteMessageTemplateService.getSiteTemplateByUniqueName(request.getTemplateName()) ;
			if (template != null) {
				String content = MessageAssembleUtil.mustacheContent(template.getName(),template.getContent(), request.getProperties());
				siteMessageContent.setSiteMessageTemplateId(template.getId());
				siteMessageContent.setTitle(StringUtils.isNotBlank(request.getTitle()) ?request.getTitle() :template.getTitle());
				siteMessageContent.setContent(content);
				siteMessageContent.setAddition(StringUtils.isNotBlank(request.getAddition()) ? request.getAddition():template.getAddition());
				siteMessageContent.setType(template.getType());
				siteMessageContent.setCreatedTime(new Date());
				siteMessageContent.setCreatedBy(StringUtils.isNotBlank(request.getSender()) ? request.getSender():template.getSender());
			}
		} else {
			siteMessageContent.setTitle(request.getTitle());
			siteMessageContent.setContent(request.getContent());
			siteMessageContent.setAddition(request.getAddition());
			if (request.getType() != null) {
				siteMessageContent.setType(request.getType());
			}
			siteMessageContent.setCreatedTime(new Date());
			siteMessageContent.setCreatedBy(request.getSender());
		}
		siteMessageContentMapper.insertSelective(siteMessageContent);
		log.info("saveSiteContent     siteMessageContent ID:{}   ", siteMessageContent != null ? siteMessageContent.getId() : null);
		return siteMessageContent;
	}

}
