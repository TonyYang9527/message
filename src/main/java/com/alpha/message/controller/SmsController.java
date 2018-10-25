package com.alpha.message.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("sms")
@RestController
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private SmsFacade smsService;

	@ApiOperation(" Send Sms  ")
	@PostMapping(value = "/message/send")
	public SendSmsResponse sendSms(@RequestBody SendSmsRequest request) throws IOException {
		return smsService.sendSms(request);
	}

	@ApiOperation(" Create  Sms Template  ")
	@PostMapping(value = "/template/create")
	public CreateSmsMsgTemplateResponse createSmsMsgTemplate(@RequestBody CreateSmsMsgTemplateRequest request) throws IOException {
		return smsService.createSmsMsgTemplate(request);
	}

	@ApiOperation(" Delete  Sms  Template  ")
	@PostMapping(value = "/template/delete")
	public DeleteSmsMsgTemplateResponse deleteSmsMsgTemplate(@RequestBody DeleteSmsMsgTemplateRequest request) throws IOException {
		return smsService.deleteSmsMsgTemplate(request);
	}

	@ApiOperation(" Disable  Sms  Template  ")
	@PostMapping(value = "/template/disable")
	public DisableSmsMsgTemplateResponse disableSmsMsgTemplate(@RequestBody DisableSmsMsgTemplateRequest request) throws IOException {
		return smsService.disableSmsMsgTemplate(request);
	}

	@ApiOperation(" Enable  Sms  Template  ")
	@PostMapping(value = "/template/enable")
	public EnableSmsMsgTemplateResponse enableSmsMsgTemplate(@RequestBody EnableSmsMsgTemplateRequest request) throws IOException {
		return smsService.enableSmsMsgTemplate(request);
	}

	@ApiOperation("   Update  Sms  Template  ")
	@PostMapping(value = "/template/update")
	public UpdateSmsMsgTemplateResponse updateSmsMsgTemplate(@RequestBody UpdateSmsMsgTemplateRequest request) throws IOException {
		return smsService.updateSmsMsgTemplate(request);
	}

	@GetMapping("/template/select/{smsTemplateId}")
	@ApiOperation(value = "select  sms  Template ")
	public GetSmsMsgTemplateResponse getSmsMsgTemplate(@PathVariable Long smsTemplateId) throws IOException {
		return smsService.getSmsMsgTemplate(smsTemplateId);
	}

	@ApiOperation(" List  Sms  Template  ")
	@PostMapping(value = "/template/list")
	public ListSmsMsgTemplateResponse listSmsMsgTemplate(@RequestBody ListMsgTemplateRequest request) throws IOException {
		log.info(" List   Site  Message  Template   :  request:{} ", request);
		return smsService.listSmsMsgTemplate(request);
	}
}
