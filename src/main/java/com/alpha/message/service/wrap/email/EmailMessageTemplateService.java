package com.alpha.message.service.wrap.email;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.MsgTemplateState;
import com.alpha.message.common.enums.StateFlag;
import com.alpha.message.dao.entiy.email.EmailMessageTemplate;
import com.alpha.message.dao.mapper.email.EmailMessageTemplateMapper;
import com.alpha.message.ifacade.request.email.CreateEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.DeleteEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.DisableEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.EnableEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.UpdateEmailMsgTemplateRequest;
import com.google.common.base.Preconditions;

@Service
public class EmailMessageTemplateService {

	@Autowired
	protected EmailMessageTemplateMapper templateMapper;

	public EmailMessageTemplate selectByPrimaryKey(Long id) {
		return templateMapper.selectByPrimaryKey(id);
	}

	/**
	 * Save Email Template
	 * 
	 * @param request
	 * @return
	 */
	public EmailMessageTemplate createEmailMsgTemplate(CreateEmailMsgTemplateRequest request) {

		EmailMessageTemplate emailMessageTemplate = new EmailMessageTemplate();
		emailMessageTemplate.setFromAddress(request.getFromAddress());
		emailMessageTemplate.setSenderName(request.getSenderName());
		emailMessageTemplate.setTitle(request.getTitle());
		emailMessageTemplate.setContent(request.getContent());
		emailMessageTemplate.setState(MsgTemplateState.ENABLED.getValue());
		emailMessageTemplate.setCreatedBy(request.getCreatedBy());
		emailMessageTemplate.setCreatedTime(new Date());
		emailMessageTemplate.setName(request.getName());
		emailMessageTemplate.setPriority(request.getPriority());
		emailMessageTemplate.setType(request.getType());
		emailMessageTemplate.setDeleted(DeleteFlag.SURVIVOR.getValue());
		templateMapper.insertSelective(emailMessageTemplate);
		return emailMessageTemplate;
	}

	/**
	 * Delete Email Template
	 * 
	 * @param request
	 */
	public void deleteEmailMsgTemplate(DeleteEmailMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkDeleted(id, DeleteFlag.SURVIVOR.getValue())) {
				templateMapper.changeDeleted(id, DeleteFlag.VICTIM.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Email Template
	 * 
	 * @param request
	 */
	public void disableEmailMsgTemplate(DisableEmailMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkState(id, StateFlag.ENABLE.getValue())) {
				templateMapper.changeState(id, StateFlag.DISABLE.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Email Template
	 * 
	 * @param request
	 */
	public void enableEmailMsgTemplate(EnableEmailMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkState(id, StateFlag.DISABLE.getValue())) {
				templateMapper.changeState(id, StateFlag.ENABLE.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Update Email Template
	 * 
	 * @param request
	 */
	public void updateEmailMsgTemplate(UpdateEmailMsgTemplateRequest request) {
		EmailMessageTemplate template = templateMapper.selectByPrimaryKey(request.getId());
		if (template != null) {
			template.setFromAddress(request.getFromAddress());
			template.setSenderName(request.getSenderName());
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
	 * List Email Template
	 * 
	 * @param request
	 */
	public List<EmailMessageTemplate> listEmailMsgTemplate(ListMsgTemplateRequest request) {
		return templateMapper.listEmailTemplate(request.getName(), request.getStates());
	}
	

    /**
     *Get  Email Template  ByPrimaryKey 
     * @param id
     * @return
     */
    public EmailMessageTemplate getMsgTemplate(Long id) {
        Preconditions.checkNotNull(id);
        return  templateMapper.selectByPrimaryKey(id);
    }
}
