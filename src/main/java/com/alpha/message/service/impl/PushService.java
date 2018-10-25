package com.alpha.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alpha.message.common.enums.ImmediateType;
import com.alpha.message.common.utils.BeanMapper;
import com.alpha.message.dao.entiy.push.PushMessage;
import com.alpha.message.dao.entiy.push.PushMessageTemplate;
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
import com.alpha.message.model.vo.PushMsgTemplateVo;
import com.alpha.message.service.driver.entity.response.ChannelResp;
import com.alpha.message.service.wrap.push.PushMessagePropertyService;
import com.alpha.message.service.wrap.push.PushMessageService;
import com.alpha.message.service.wrap.push.PushMessageTemplateService;
import com.alpha.message.service.wrap.task.PushTaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushService implements PushFacade {

	@Autowired
	private PushMessageService pushMessageService;
	@Autowired
	private PushMessagePropertyService pushPropertyService;
	@Autowired
	private PushMessageTemplateService pushTemplateService;

	@Autowired
	protected PushTaskService pushTaskService;

	@Override
	public SendMessageResponse sendPush(SendPushRequest request) {
		SendMessageResponse resp = new SendMessageResponse();
		PushMessage pushMessage = saveReqToDB(request);
		if (pushMessage == null) {
			resp.setRetMsg("Send  Push  Message Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		if (pushMessage.getImmediate().equals(ImmediateType.IMMEDIATE.getValue())) {
			ChannelResp channelResp = pushTaskService.sendTask(pushMessage.getId());
			log.info("SendPushTask :  pushMessage:{}, result :{}  ", pushMessage, channelResp);
		}
		resp.setId(pushMessage.getId());
		resp.setRetMsg("Create Push  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("Send Push Message  Success.Request:{},Response:{} ", request, resp);
		return resp;
	}

	@Override
	public CreatePushMsgTemplateResponse createPushMsgTemplate(CreatePushMsgTemplateRequest request) {
		CreatePushMsgTemplateResponse resp = new CreatePushMsgTemplateResponse();
		PushMessageTemplate template = pushTemplateService.createPushMsgTemplate(request);
		if (template == null) {
			resp.setRetMsg("Create  Push  Message Template Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}

		resp.setId(template.getId());
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Create  Push  Message  Template  Success");
		log.info(" Create Push Message  Template Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public DeletePushMsgTemplateResponse deletePushMsgTemplate(DeletePushMsgTemplateRequest request) {
		DeletePushMsgTemplateResponse resp = new DeletePushMsgTemplateResponse();
		pushTemplateService.deletePushMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Delete  Push  Message  Template  Success");
		log.info(" Delete  Push Message  Template Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public DisablePushMsgTemplateResponse disablePushMsgTemplate(DisablePushMsgTemplateRequest request) {
		DisablePushMsgTemplateResponse resp = new DisablePushMsgTemplateResponse();
		pushTemplateService.disablePushMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Disable  Push  Message  Template  Success");
		log.info(" Disable Push Message  Template Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public EnablePushMsgTemplateResponse enablePushMsgTemplate(EnablePushMsgTemplateRequest request) {
		EnablePushMsgTemplateResponse resp = new EnablePushMsgTemplateResponse();
		pushTemplateService.enablePushMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Enable  Push  Message  Template  Success");
		log.info(" Enable  Push Message  Template Success.Request:{},Response:{} ", request, resp);
		return resp;
	}

	@Override
	public UpdatePushMsgTemplateResponse updatePushMsgTemplate(UpdatePushMsgTemplateRequest request) {
		UpdatePushMsgTemplateResponse resp = new UpdatePushMsgTemplateResponse();
		log.info(" Update   Push  Message  Template   :  request:{} ", request);
		pushTemplateService.updatePushMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Update  Push  Message  Template  Success");
		log.info(" Update  Push Message  Template Success.Request:{}, response:{} ", request, resp);
		return resp;
	}

	@Override
	public GetPushMsgTemplateResponse getPushMsgTemplate(Long pushTemplateId) {
		GetPushMsgTemplateResponse resp = new GetPushMsgTemplateResponse();
		PushMessageTemplate template = pushTemplateService.selectByPrimaryKey(pushTemplateId);
		if (template == null) {
			resp.setRetMsg("Get  Push Message  Template Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		PushMsgTemplateVo pushMsgTemplateVo = BeanMapper.map(template, PushMsgTemplateVo.class);
		resp.setTemplate(pushMsgTemplateVo);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("  Get  Push  Message  Template  Success");
		log.info(" Get  Push Message  Template Success.pushTemplateId:{}, Response :{} ", pushTemplateId, resp);
		return resp;
	}

	@Override
	public ListPushMsgTemplateResponse listPushMsgTemplate(ListMsgTemplateRequest request) {
		ListPushMsgTemplateResponse resp = new ListPushMsgTemplateResponse();
		List<PushMessageTemplate> templates = pushTemplateService.listPushMsgTemplate(request);
		if (CollectionUtils.isEmpty(templates)) {
			resp.setRetMsg("List Push Message  Template Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		List<PushMsgTemplateVo> vos = BeanMapper.mapList(templates, PushMsgTemplateVo.class);
		resp.setResult(vos);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("  List  Push  Message  Template  Success");
		log.info(" List Push Message  Template Success.Request:{}, Response :{} ", request, resp);
		return resp;
	}

	private PushMessage saveReqToDB(SendPushRequest request)  {
		PushMessage pushMessage = pushMessageService.savePushMessageByReq(request);
		// no param template
		if (pushMessage != null && !CollectionUtils.isEmpty(request.getProperties())) {
			pushPropertyService.savePushMessageProperty(pushMessage.getId(), request.getProperties());
		} else {
			log.error("saveReqToDB  Push Message  is Null");
		}

		return pushMessage;
	}
}
