package com.alpha.message.service.wrap.sms;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.MsgTemplateState;
import com.alpha.message.common.enums.StateFlag;
import com.alpha.message.dao.entiy.sms.SmsMessageTemplate;
import com.alpha.message.dao.mapper.sms.SmsMessageTemplateMapper;
import com.alpha.message.ifacade.request.sms.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.CreateSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.DeleteSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.DisableSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.EnableSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.UpdateSmsMsgTemplateRequest;
import com.google.common.base.Preconditions;


@Service
public class SmsMessageTemplateService {

	
	@Autowired
	protected SmsMessageTemplateMapper templateMapper;

	public SmsMessageTemplate selectByPrimaryKey(Long id) {
		return templateMapper.selectByPrimaryKey(id);
	}

	/**
	 * Save SMS Template
	 * 
	 * @param request
	 * @return
	 */
	public SmsMessageTemplate createSmsMsgTemplate(CreateSmsMsgTemplateRequest request) {

		SmsMessageTemplate  smsMessageTemplate = new SmsMessageTemplate();
		smsMessageTemplate.setName(request.getName());
		smsMessageTemplate.setContent(request.getContent());
		smsMessageTemplate.setType(request.getType());
		smsMessageTemplate.setPriority(request.getPriority());
		smsMessageTemplate.setState(MsgTemplateState.ENABLED.getValue());
		smsMessageTemplate.setCreatedBy(request.getOperator());
		smsMessageTemplate.setCreatedTime(new Date());
		smsMessageTemplate.setDeleted(DeleteFlag.SURVIVOR.getValue());
		templateMapper.insertSelective(smsMessageTemplate);
		return smsMessageTemplate;
	}

	/**
	 * Delete Sms Template
	 * 
	 * @param request
	 */
	public void deleteSmsMsgTemplate(DeleteSmsMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkDeleted(id, DeleteFlag.SURVIVOR.getValue())) {
				templateMapper.changeDeleted(id, DeleteFlag.VICTIM.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Sms Template
	 * 
	 * @param request
	 */
	public void disableSmsMsgTemplate(DisableSmsMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkState(id, StateFlag.ENABLE.getValue())) {
				templateMapper.changeState(id, StateFlag.DISABLE.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Disable Sms Template
	 * 
	 * @param request
	 */
	public void enableSmsMsgTemplate(EnableSmsMsgTemplateRequest request) {
		Preconditions.checkNotNull(request.getIds());
		for (long id : request.getIds()) {
			if (templateMapper.checkState(id, StateFlag.DISABLE.getValue())) {
				templateMapper.changeState(id, StateFlag.ENABLE.getValue(), request.getOperator());
			}
		}
	}

	/**
	 * Update Sms Template
	 * 
	 * @param request
	 */
	public void updateSmsMsgTemplate(UpdateSmsMsgTemplateRequest request) {
		SmsMessageTemplate template = templateMapper.selectByPrimaryKey(request.getId());
		if (template != null) {
			template.setName(request.getName());
			template.setContent(request.getContent());
			template.setType(request.getType());
			template.setPriority(request.getPriority());
			template.setState(MsgTemplateState.ENABLED.getValue());
			template.setUpdatedBy(request.getOperator());
			template.setUpdatedTime(new Date());
			templateMapper.updateByPrimaryKeySelective(template);
		}
	}

	/**
	 * List Sms Template
	 * @param request
	 */
	public List<SmsMessageTemplate> listSmsMsgTemplate(ListMsgTemplateRequest request) {
		return templateMapper.listSmsTemplate(request.getName(), request.getStates());
	}
	

    /**
     *Get  Sms Template  ByPrimaryKey 
     * @param id
     * @return
     */
    public SmsMessageTemplate getSmsMsgTemplate(Long id) {
        Preconditions.checkNotNull(id);
        return  templateMapper.selectByPrimaryKey(id);
    }
}
