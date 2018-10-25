package com.alpha.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.alpha.message.common.utils.BeanMapper;
import com.alpha.message.dao.entiy.sms.SmsMessage;
import com.alpha.message.dao.entiy.sms.SmsMessageTemplate;
import com.alpha.message.ifacade.facade.SmsFacade;
import com.alpha.message.ifacade.request.sms.CreateSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.DeleteSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.DisableSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.EnableSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.SendSmsRequest;
import com.alpha.message.ifacade.request.sms.UpdateSmsMsgTemplateRequest;
import com.alpha.message.ifacade.response.sms.CreateSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.DeleteSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.DisableSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.EnableSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.GetSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.ListSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.SendSmsResponse;
import com.alpha.message.ifacade.response.sms.UpdateSmsMsgTemplateResponse;
import com.alpha.message.model.vo.SmsMsgTemplateVo;
import com.alpha.message.service.wrap.sms.SmsMessagePropertyService;
import com.alpha.message.service.wrap.sms.SmsMessageService;
import com.alpha.message.service.wrap.sms.SmsMessageTemplateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsService implements SmsFacade {

	@Autowired
	private SmsMessageService smsMessageService;
	@Autowired
	private SmsMessagePropertyService smsPropertyService;
	@Autowired
	private SmsMessageTemplateService smsTemplateService;

	@Override
	public SendSmsResponse sendSms(SendSmsRequest request) {
		SendSmsResponse resp = new SendSmsResponse();
		SmsMessage smsMessage = saveReqToDB(request);
		if (smsMessage == null) {
			resp.setRetMsg("Send Sms  Message Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setId(smsMessage.getId());
		resp.setRetMsg("Send Sms  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("Send Sms Message  Success . Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public CreateSmsMsgTemplateResponse createSmsMsgTemplate(CreateSmsMsgTemplateRequest request) {
		CreateSmsMsgTemplateResponse resp = new CreateSmsMsgTemplateResponse();
		SmsMessageTemplate template = smsTemplateService.createSmsMsgTemplate(request);
		if (template == null) {
			resp.setRetMsg("Create Sms  Message Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setId(template.getId());
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Create  Sms  Message  Template  Success");
		log.info(" Create Sms Message  Success . Request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public DeleteSmsMsgTemplateResponse deleteSmsMsgTemplate(DeleteSmsMsgTemplateRequest request) {
		DeleteSmsMsgTemplateResponse resp = new DeleteSmsMsgTemplateResponse();
		smsTemplateService.deleteSmsMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Delete  Sms  Message  Template  Success");
		log.info(" Delete Sms Message  Success . Request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public DisableSmsMsgTemplateResponse disableSmsMsgTemplate(DisableSmsMsgTemplateRequest request) {
		DisableSmsMsgTemplateResponse resp = new DisableSmsMsgTemplateResponse();
		smsTemplateService.disableSmsMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Disable  Sms  Message  Template  Success");
		log.info(" Disable Sms Message  Success . Request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public EnableSmsMsgTemplateResponse enableSmsMsgTemplate(EnableSmsMsgTemplateRequest request) {
		EnableSmsMsgTemplateResponse resp = new EnableSmsMsgTemplateResponse();
		smsTemplateService.enableSmsMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Enable  Sms  Message  Template  Success");
		log.info(" Enable Sms Message  Success . Request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public UpdateSmsMsgTemplateResponse updateSmsMsgTemplate(UpdateSmsMsgTemplateRequest request) {
		UpdateSmsMsgTemplateResponse resp = new UpdateSmsMsgTemplateResponse();
		smsTemplateService.updateSmsMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Update  Sms  Message  Template  Success");
		log.info(" Update Sms Message  Success . Request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public GetSmsMsgTemplateResponse getSmsMsgTemplate(Long smsTemplateId) {
		GetSmsMsgTemplateResponse resp = new GetSmsMsgTemplateResponse();
		SmsMessageTemplate template = smsTemplateService.getSmsMsgTemplate(smsTemplateId);
		if (template == null) {
			resp.setRetMsg("Get   Sms  Message  Template Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		SmsMsgTemplateVo smsMsgTemplateVo = BeanMapper.map(template, SmsMsgTemplateVo.class);
		resp.setTemplate(smsMsgTemplateVo);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("  Get  Sms  Message  Template  Success");
		log.info(" Get Sms Message  Success . smsTemplateId:{}, Response:{}", smsTemplateId, resp);
		return resp;
	}

	@Override
	public ListSmsMsgTemplateResponse listSmsMsgTemplate(ListMsgTemplateRequest request) {
		ListSmsMsgTemplateResponse resp = new ListSmsMsgTemplateResponse();
		List<SmsMessageTemplate> templates = smsTemplateService.listSmsMsgTemplate(request);
		if (CollectionUtils.isEmpty(templates)) {
			resp.setRetMsg("List   Sms  Message  Template Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		List<SmsMsgTemplateVo> vos = BeanMapper.mapList(templates, SmsMsgTemplateVo.class);
		resp.setResult(vos);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("  List  Sms  Message  Template  Success");
		log.info(" List Sms Message  Success . Request:{}, Response:{}", request, resp);
		return resp;
	}

	protected SmsMessage saveReqToDB(SendSmsRequest request) {
		SmsMessage smsMessage = smsMessageService.saveSmsMessageByReq(request);
		if (smsMessage != null && !CollectionUtils.isEmpty(request.getProperties())) {
			smsPropertyService.saveSmsMessageProperty(smsMessage.getId(), request.getProperties());
		}

		return smsMessage;
	}
}
