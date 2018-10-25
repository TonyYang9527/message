package com.alpha.message.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.alpha.message.service.driver.email.EmailDriver;
import com.alpha.message.service.driver.entity.request.EmailChannelReq;
import com.alpha.message.service.driver.entity.response.ChannelResp;


@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailFacade emailService;

	@Autowired
	private EmailDriver driver;

	@PostMapping(value = "/message/send")
   public SendMessageResponse sendEmail(@RequestBody SendEmailRequest req) {
		SendMessageResponse response = new SendMessageResponse();
		try {
			response = emailService.sendEmail(req);
		} catch (IOException e) {
			response.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setRetMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		//	log.error("sendEmail Exception :{} ", e);
		}
		return response;
	}

	@PostMapping(value = "/template/create")
	public CreateEmailMsgTemplateResponse createEmailTemplate(@RequestBody CreateEmailMsgTemplateRequest request) {
		return emailService.createEmailMsgTemplate(request);
	}

	@PostMapping(value = "/template/delete")
	public DeleteEmailMsgTemplateResponse deleteEmailTemplate(@RequestBody DeleteEmailMsgTemplateRequest request) {
		return emailService.deleteEmailMsgTemplate(request);
	}

	@PostMapping(value = "/template/disable")
	@ApiOperation(value = " Disable  Email Template ")
	public DisableEmailMsgTemplateResponse disableEmailTemplate(@RequestBody DisableEmailMsgTemplateRequest request) {
		return emailService.disableEmailMsgTemplate(request);
	}

	@PostMapping(value = "/template/enable")
	@ApiOperation(value = " Enable  Email Template ")
	public EnableEmailMsgTemplateResponse enableEmailTemplate(@RequestBody EnableEmailMsgTemplateRequest request) {
		return emailService.enableEmailMsgTemplate(request);
	}

	@PostMapping(value = "/template/update")
	@ApiOperation(value = " Update  Email Template ")
	public UpdateEmailMsgTemplateResponse updateEmailTemplate(@RequestBody UpdateEmailMsgTemplateRequest request) {
		return emailService.updateEmailMsgTemplate(request);
	}

	@GetMapping("/template/select/{emailTemplateId}")
	@ApiOperation(value = "select  Email Template ")
	public GetEmailMsgTemplateResponse getEmailTemplate(@PathVariable Long emailTemplateId) {
		log.info(" Get Email Message  Template :emailTemplateId:{} ", emailTemplateId);
		return emailService.getEmailMsgTemplate(emailTemplateId);
	}

	@PostMapping(value = "/template/list")
	@ApiOperation(value = " List  Email Template ")
	public ListEmailMsgTemplateResponse listEmailTemplate(@RequestBody ListMsgTemplateRequest request) {
		log.info(" List Email Message  Template :request:{} ", request);
		return emailService.listEmailMsgTemplate(request);
	}

	@PostMapping(value = "/mail/test")
	@ApiOperation(value = " test  ")
	public ChannelResp test() {
		String subject = "测试邮件";
		String content = "测试邮件";
		String[] to = new String[] { "yangxiangjiang19880805@gmail.com" };
		EmailChannelReq req = new EmailChannelReq(0L, to, content, "no-reply@brightoilmarine.com", subject,
				"Marine Online Singapore", true, null, Boolean.TRUE);
		return driver.send(req);
	}
}