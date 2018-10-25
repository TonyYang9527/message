package com.alpha.message.service.wrap.push;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.MsgTemplateState;
import com.alpha.message.common.enums.StateFlag;
import com.alpha.message.dao.entiy.push.PushMessageTemplate;
import com.alpha.message.dao.mapper.push.PushMessageTemplateMapper;
import com.alpha.message.ifacade.request.push.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.CreatePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.DeletePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.DisablePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.EnablePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.UpdatePushMsgTemplateRequest;
import com.google.common.base.Preconditions;

@Service
public class PushMessageTemplateService {

	@Autowired
	protected PushMessageTemplateMapper templateMapper;

	public PushMessageTemplate selectByPrimaryKey(Long id) {
		return templateMapper.selectByPrimaryKey(id);
	}

	/**
	 * Save Push Template
	 * 
	 * @param request
	 * @return
	 */
	public PushMessageTemplate createPushMsgTemplate(CreatePushMsgTemplateRequest request) {

		PushMessageTemplate pushMessageTemplate = new PushMessageTemplate();
		pushMessageTemplate.setName(request.getName());
		pushMessageTemplate.setTitle(request.getTitle());
		pushMessageTemplate.setContent(request.getContent());
		pushMessageTemplate.setState(MsgTemplateState.ENABLED.getValue());
		pushMessageTemplate.setCreatedBy(request.getOperator());
		pushMessageTemplate.setCreatedTime(new Date());
		pushMessageTemplate.setPriority(request.getPriority());
		pushMessageTemplate.setType(request.getType());
		pushMessageTemplate.setDeleted(DeleteFlag.SURVIVOR.getValue());
		templateMapper.insertSelective(pushMessageTemplate);
		return pushMessageTemplate;
	}

	/**
	 * Delete Push Template
	 * 
	 * @param request
	 */
	public void deletePushMsgTemplate(DeletePushMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkDeleted(id, DeleteFlag.SURVIVOR.getValue())) {
				templateMapper.changeDeleted(id, DeleteFlag.VICTIM.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Push Template
	 * 
	 * @param request
	 */
	public void disablePushMsgTemplate(DisablePushMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkState(id, StateFlag.ENABLE.getValue())) {
				templateMapper.changeState(id, StateFlag.DISABLE.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Push Template
	 * 
	 * @param request
	 */
	public void enablePushMsgTemplate(EnablePushMsgTemplateRequest request) {
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
	public void updatePushMsgTemplate(UpdatePushMsgTemplateRequest request) {
		PushMessageTemplate template = templateMapper.selectByPrimaryKey(request.getId());
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
	 * List Push Template
	 * 
	 * @param request
	 */
	public List<PushMessageTemplate> listPushMsgTemplate(ListMsgTemplateRequest request) {
		return templateMapper.listPushTemplate(request.getName(), request.getStates());
	}

	/**
	 * Get Push Template ByPrimaryKey
	 * 
	 * @param id
	 * @return
	 */
	public PushMessageTemplate getMsgTemplate(Long id) {
		Preconditions.checkNotNull(id);
		return templateMapper.selectByPrimaryKey(id);
	}
}
