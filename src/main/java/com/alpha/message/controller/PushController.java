package com.alpha.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.message.ifacade.facade.PushFacade;
import com.alpha.message.ifacade.request.push.CreatePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.DeletePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.DisablePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.EnablePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.SendPushRequest;
import com.alpha.message.ifacade.request.push.UpdatePushMsgTemplateRequest;
import com.alpha.message.ifacade.response.push.CreatePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.DeletePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.DisablePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.EnablePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.GetPushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.ListPushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.SendMessageResponse;
import com.alpha.message.ifacade.response.push.UpdatePushMsgTemplateResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("push")
@RestController
@RequestMapping("/push")
public class PushController {

	@Autowired
	private PushFacade pushService;

	@ApiOperation(" Send Push  ")
	@PostMapping(value = "/message/send")
	public SendMessageResponse sendPush(@RequestBody SendPushRequest request) {
		SendMessageResponse response = new SendMessageResponse();
		try {
			response = pushService.sendPush(request);
		} catch (Exception e) {
			response.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setRetMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			log.error("Push Message Exception :{} ", e);
		}
		return response;
	}

	@ApiOperation(" Create  Push Template  ")
	@PostMapping(value = "/template/create")
	public CreatePushMsgTemplateResponse createPushMsgTemplate(@RequestBody CreatePushMsgTemplateRequest request) {
		return pushService.createPushMsgTemplate(request);
	}

	@ApiOperation(" Delete  Push  Template  ")
	@PostMapping(value = "/template/delete")
	public DeletePushMsgTemplateResponse deletePushMsgTemplate(@RequestBody DeletePushMsgTemplateRequest request) {
		return pushService.deletePushMsgTemplate(request);
	}

	@ApiOperation("  Disable  Push  Template  ")
	@PostMapping(value = "/template/disable")
	public DisablePushMsgTemplateResponse disablePushMsgTemplate(@RequestBody DisablePushMsgTemplateRequest request) {
		return pushService.disablePushMsgTemplate(request);
	}

	@ApiOperation("  Enable  Push  Template  ")
	@PostMapping(value = "/template/enable")
	public EnablePushMsgTemplateResponse enablePushMsgTemplate(@RequestBody EnablePushMsgTemplateRequest request) {
		return pushService.enablePushMsgTemplate(request);
	}

	@ApiOperation(" Update  Push  Template  ")
	@PostMapping(value = "/template/update")
	public UpdatePushMsgTemplateResponse updatePushMsgTemplate(@RequestBody UpdatePushMsgTemplateRequest request) {
		return pushService.updatePushMsgTemplate(request);
	}

	@GetMapping("/template/select/{pushTemplateId}")
	@ApiOperation(value = "select  Push Template ")
	public GetPushMsgTemplateResponse getPushMsgTemplate(@PathVariable Long pushTemplateId) {
		return pushService.getPushMsgTemplate(pushTemplateId);
	}

	@ApiOperation(" List  Push  Template  ")
	@PostMapping(value = "/template/list")
	public ListPushMsgTemplateResponse listPushMsgTemplate(@RequestBody ListMsgTemplateRequest request) {
		log.info(" List   Site  Message  Template   :  request:{} ", request);
		return pushService.listPushMsgTemplate(request);
	}
}
