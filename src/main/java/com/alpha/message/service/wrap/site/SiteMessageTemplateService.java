package com.alpha.message.service.wrap.site;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.MsgTemplateState;
import com.alpha.message.common.enums.StateFlag;
import com.alpha.message.dao.entiy.site.SiteMessageTemplate;
import com.alpha.message.dao.mapper.site.SiteMessageTemplateMapper;
import com.alpha.message.ifacade.request.site.CreateSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.DeleteSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.DisableSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.EnableSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.UpdateSiteMsgTemplateRequest;
import com.google.common.base.Preconditions;

@Service
public class SiteMessageTemplateService {

	@Autowired
	protected SiteMessageTemplateMapper templateMapper;

	public SiteMessageTemplate selectByPrimaryKey(Long id) {
		return templateMapper.selectByPrimaryKey(id);
	}

	/**
	 * Save Site Template
	 * 
	 * @param request
	 * @return
	 */
	public SiteMessageTemplate createSiteMsgTemplate(CreateSiteMsgTemplateRequest request) {

		SiteMessageTemplate siteMessageTemplate = new SiteMessageTemplate();

		siteMessageTemplate.setTitle(request.getTitle());
		siteMessageTemplate.setContent(request.getContent());
		siteMessageTemplate.setState(MsgTemplateState.ENABLED.getValue());
		siteMessageTemplate.setCreatedBy(request.getOperator());
		siteMessageTemplate.setCreatedTime(new Date());
		siteMessageTemplate.setName(request.getName());
		siteMessageTemplate.setPriority(request.getPriority());
		siteMessageTemplate.setType(request.getType());
		siteMessageTemplate.setSender(request.getSender());
		siteMessageTemplate.setDeleted(DeleteFlag.SURVIVOR.getValue());
		templateMapper.insertSelective(siteMessageTemplate);
		return siteMessageTemplate;
	}

	/**
	 * Delete Site Template
	 * 
	 * @param request
	 */
	public void deleteSiteMsgTemplate(DeleteSiteMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkDeleted(id, DeleteFlag.SURVIVOR.getValue())) {
				templateMapper.changeDeleted(id, DeleteFlag.VICTIM.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Site Template
	 * 
	 * @param request
	 */
	public void disableSiteMsgTemplate(DisableSiteMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkState(id, StateFlag.ENABLE.getValue())) {
				templateMapper.changeState(id, StateFlag.DISABLE.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Site Template
	 * 
	 * @param request
	 */
	public void enableSiteMsgTemplate(EnableSiteMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkState(id, StateFlag.DISABLE.getValue())) {
				templateMapper.changeState(id, StateFlag.ENABLE.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Update Push Template
	 * 
	 * @param request
	 */
	public void updateSiteMsgTemplate(UpdateSiteMsgTemplateRequest request) {
		SiteMessageTemplate template = templateMapper.selectByPrimaryKey(request.getId());
		if (template != null) {
			template.setTitle(request.getTitle());
			template.setName(request.getName());
			template.setContent(request.getContent());
			template.setType(request.getType());
			template.setPriority(request.getPriority());
			template.setUpdatedBy(request.getOperator());
			template.setUpdatedTime(new Date());
			templateMapper.updateByPrimaryKeySelective(template);
		}
	}

	/**
	 * List Site Template
	 * 
	 * @param request
	 */
	public List<SiteMessageTemplate> listSiteMsgTemplate(ListMsgTemplateRequest request) {
		return templateMapper.listSiteTemplate(request.getName(), request.getStates());
	}

	/**
	 * Get Site Template ByPrimaryKey
	 * 
	 * @param id
	 * @return
	 */
	public SiteMessageTemplate getMsgTemplate(Long id) {
		Preconditions.checkNotNull(id);
		return templateMapper.selectByPrimaryKey(id);
	}

	/**
	 * Get Site Template ByPrimaryKey
	 * 
	 * @param id
	 * @return
	 */
	public SiteMessageTemplate getSiteTemplateByUniqueName(String name) {
		Preconditions.checkNotNull(name);
		return templateMapper.selectByUniqueName(name);
	}

}
