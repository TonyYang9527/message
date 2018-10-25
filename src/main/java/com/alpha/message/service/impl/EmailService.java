package com.alpha.message.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alpha.message.common.enums.ImmediateType;
import com.alpha.message.common.utils.BeanMapper;
import com.alpha.message.dao.entiy.email.EmailMessage;
import com.alpha.message.dao.entiy.email.EmailMessageTemplate;
import com.alpha.message.ifacade.facade.EmailFacade;
import com.alpha.message.ifacade.request.email.CreateEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.DeleteEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.DisableEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.EnableEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.SendEmailRequest;
import com.alpha.message.ifacade.request.email.UpdateEmailMsgTemplateRequest;
import com.alpha.message.ifacade.response.email.CreateEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.DeleteEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.DisableEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.EnableEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.GetEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.ListEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.SendMessageResponse;
import com.alpha.message.ifacade.response.email.UpdateEmailMsgTemplateResponse;
import com.alpha.message.model.vo.EmailMsgTemplateVo;
import com.alpha.message.service.driver.entity.response.ChannelResp;
import com.alpha.message.service.wrap.email.EmailMessagePropertyService;
import com.alpha.message.service.wrap.email.EmailMessageService;
import com.alpha.message.service.wrap.email.EmailMessageTemplateService;
import com.alpha.message.service.wrap.task.EmailTaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService implements EmailFacade {

	@Autowired
	private EmailMessageService emailMessageService;
	@Autowired
	private EmailMessagePropertyService emailPropertyService;
	@Autowired
	private EmailMessageTemplateService emailTemplateService;
	@Autowired
	protected EmailTaskService emailTaskService;

	@Override
	public SendMessageResponse sendEmail(SendEmailRequest request) throws IOException {

		SendMessageResponse resp = new SendMessageResponse();
		EmailMessage emailMessage = saveReqToDB(request);
		if (emailMessage == null) {
			resp.setRetMsg("Create Email  Message  Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		if (emailMessage.getImmediate().equals(ImmediateType.IMMEDIATE.getValue())) {
			ChannelResp channelResp = emailTaskService.sendTask(emailMessage.getId(),request.getCheckSkip());
			log.info("SendEmailTask :  email:{}, result :{}  ", emailMessage, channelResp);
		}
		resp.setId(emailMessage.getId());
		resp.setRetMsg("Create Email  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("Send Email  Message  Success . Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public CreateEmailMsgTemplateResponse createEmailMsgTemplate(CreateEmailMsgTemplateRequest request) {
		CreateEmailMsgTemplateResponse resp = new CreateEmailMsgTemplateResponse();
		EmailMessageTemplate template = emailTemplateService.createEmailMsgTemplate(request);
		if (template != null) {
			resp.setId(template.getId());
			resp.setRetCode(HttpStatus.OK.value());
			resp.setRetMsg(" Create  Email  Message  Template  Success");
		} else {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg(" Create  Email  Message  Template  Failed");
		}
		log.info(" Create Email Message Template: Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public DeleteEmailMsgTemplateResponse deleteEmailMsgTemplate(DeleteEmailMsgTemplateRequest request) {
		DeleteEmailMsgTemplateResponse resp = new DeleteEmailMsgTemplateResponse();
		emailTemplateService.deleteEmailMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Delete  Email  Message  Template  Success");
		log.info(" Delete Email Message Template : Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public DisableEmailMsgTemplateResponse disableEmailMsgTemplate(DisableEmailMsgTemplateRequest request) {
		DisableEmailMsgTemplateResponse resp = new DisableEmailMsgTemplateResponse();
		emailTemplateService.disableEmailMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Disable  Email  Message  Template  Success");
		log.info(" Disable Email Message Template : Request:{}, Response:{} ", request, JSON.toJSONString(resp));
		return resp;
	}

	@Override
	public EnableEmailMsgTemplateResponse enableEmailMsgTemplate(EnableEmailMsgTemplateRequest request) {
		EnableEmailMsgTemplateResponse resp = new EnableEmailMsgTemplateResponse();
		emailTemplateService.enableEmailMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Enable  Email  Message  Template  Success");
		log.info(" Enable Email Message Template : Request:{}, Response:{} ", request, JSON.toJSONString(resp));
		return resp;
	}

	@Override
	public UpdateEmailMsgTemplateResponse updateEmailMsgTemplate(UpdateEmailMsgTemplateRequest request) {
		UpdateEmailMsgTemplateResponse resp = new UpdateEmailMsgTemplateResponse();
		emailTemplateService.updateEmailMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Update  Email  Message  Template  Success");
		log.info(" Update Email Message Template : Request:{},response:{} ", request, JSONObject.valueToString(resp));
		return resp;
	}

	@Override
	public GetEmailMsgTemplateResponse getEmailMsgTemplate(Long emailTemplateId) {
		GetEmailMsgTemplateResponse resp = new GetEmailMsgTemplateResponse();
		EmailMessageTemplate template = emailTemplateService.selectByPrimaryKey(emailTemplateId);
		if (template == null) {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg(" Get  Email  Message  Template  Failed");
			return resp;
		}
		EmailMsgTemplateVo emailMsgTemplateVo = BeanMapper.map(template, EmailMsgTemplateVo.class);
		resp.setTemplate(emailMsgTemplateVo);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("  Get  Email  Message  Template  Success");
		log.info(" Get  Email Message Template : emailTemplateId:{},Response :{} ", emailTemplateId, resp);
		return resp;
	}

	@Override
	public ListEmailMsgTemplateResponse listEmailMsgTemplate(ListMsgTemplateRequest request) {
		ListEmailMsgTemplateResponse resp = new ListEmailMsgTemplateResponse();
		List<EmailMessageTemplate> templates = emailTemplateService.listEmailMsgTemplate(request);
		if (CollectionUtils.isEmpty(templates)) {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg(" List  Email  Message  Template  Failed");
			return resp;
		}
		List<EmailMsgTemplateVo> vos = BeanMapper.mapList(templates, EmailMsgTemplateVo.class);
		resp.setResult(vos);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("  List  Email  Message  Template  Success");
		log.info(" List Email Message Template : Request:{},Response :{} ", request, resp);
		return resp;
	}

	/**
	 * Save Email message to DB
	 * 
	 * @param request
	 * @return the email message
	 * @throws UnsupportedEncodingException
	 */
	private EmailMessage saveReqToDB(SendEmailRequest request) {
		EmailMessage emailMessage = emailMessageService.saveEmailMessageByReq(request);
		if (emailMessage != null && !CollectionUtils.isEmpty(request.getProperties())) {
			emailPropertyService.saveEmailMessageProperty(emailMessage.getId(), request.getProperties());
		} else {
			log.error("saveReqToDB  Email Message  is Null");
		}
		return emailMessage;
	}
}
