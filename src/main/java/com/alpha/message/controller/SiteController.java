package com.alpha.message.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmo.ibackend.model.privilege.BmoPrivilege;
import com.alpha.message.ifacade.facade.SiteFacade;
import com.alpha.message.ifacade.request.site.CreateSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.DeleteSiteMsgRequest;
import com.alpha.message.ifacade.request.site.DeleteSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.DisableSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.EnableSiteMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.site.ListSiteMsgRequest;
import com.alpha.message.ifacade.request.site.ReadSiteMsgRequest;
import com.alpha.message.ifacade.request.site.SiteMsgRequest;
import com.alpha.message.ifacade.request.site.UpdateSiteMsgRequest;
import com.alpha.message.ifacade.request.site.UpdateSiteMsgTemplateRequest;
import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.ifacade.response.site.CreateSiteTemplateResponse;
import com.alpha.message.ifacade.response.site.DeleteSiteTemplateResponse;
import com.alpha.message.ifacade.response.site.DisableSiteTemplateResponse;
import com.alpha.message.ifacade.response.site.EnableSiteTemplateResponse;
import com.alpha.message.ifacade.response.site.GetSiteMsgResponse;
import com.alpha.message.ifacade.response.site.GetSiteTemplateResponse;
import com.alpha.message.ifacade.response.site.ListSiteMsgResponse;
import com.alpha.message.ifacade.response.site.ListSiteTemplateResponse;
import com.alpha.message.ifacade.response.site.SendSiteMsgResponse;
import com.alpha.message.ifacade.response.site.UpdateSiteTemplateResponse;
import com.alpha.message.ifacade.response.site.UserSiteMessageResponse;
import com.alpha.message.ifacade.response.site.UserSiteMsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("Site")
@RestController
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private SiteFacade siteService;

	@ApiOperation(" Create  Site Template  ")
	@PostMapping(value = "/template/create")
	public CreateSiteTemplateResponse createSiteMsgTemplate(@RequestBody CreateSiteMsgTemplateRequest request)
			throws IOException {
		return siteService.createSiteMsgTemplate(request);
	}

	@ApiOperation(" Update  Site  Template  ")
	@PostMapping(value = "/template/update")
	public UpdateSiteTemplateResponse updateSiteMsgTemplate(@RequestBody UpdateSiteMsgTemplateRequest request)
			throws IOException {
		return siteService.updateSiteMsgTemplate(request);
	}

	@ApiOperation(" Delete  Site  Template  ")
	@PostMapping(value = "/template/delete")
	public DeleteSiteTemplateResponse deleteSiteMsgTemplate(@RequestBody DeleteSiteMsgTemplateRequest request)
			throws IOException {
		return siteService.deleteSiteMsgTemplate(request);
	}

	@ApiOperation(" Disable  Site  Template  ")
	@PostMapping(value = "/template/disable")
	public DisableSiteTemplateResponse disableSiteMsgTemplate(@RequestBody DisableSiteMsgTemplateRequest request)
			throws IOException {
		return siteService.disableSiteMsgTemplate(request);
	}

	@ApiOperation("  Enable  Site  Template  ")
	@PostMapping(value = "/template/enable")
	public EnableSiteTemplateResponse enableSiteMsgTemplate(@RequestBody EnableSiteMsgTemplateRequest request)
			throws IOException {
		return siteService.enableSiteMsgTemplate(request);
	}

	@GetMapping("/template/select/{siteTemplateId}")
	@ApiOperation(value = "select  Site Template ")
	public GetSiteTemplateResponse getSiteMsgTemplate(@PathVariable Long siteTemplateId) throws IOException {
		return siteService.getSiteMsgTemplate(siteTemplateId);
	}

	@ApiOperation(" List  Site  Template  ")
	@PostMapping(value = "/template/list")
	public ListSiteTemplateResponse listSiteMsgTemplate(@RequestBody ListMsgTemplateRequest request)
			throws IOException {
		log.info(" List   Site  Message  Template   :  request:{} ", request);
		return siteService.listSiteMsgTemplate(request);
	}

	@ApiOperation(value = "Create Site Message ", notes = "Not Null Param : templateName,title,properties,sender,receiverId")
	@PostMapping(value = "/message/create")
	public SendSiteMsgResponse createSiteMsg(@RequestBody SiteMsgRequest request) {
		SendSiteMsgResponse response = new SendSiteMsgResponse();
		try {
			if (StringUtils.isNotBlank(request.getSender()) && StringUtils.isNotBlank(request.getReceiverId())
					&& StringUtils.isNotBlank(request.getTemplateName())
					&& !CollectionUtils.isEmpty(request.getProperties())) {
				response = siteService.createAndSendSiteMessage(request);
			} else {
				response.setRetCode(HttpStatus.BAD_REQUEST.value());
				response.setRetMsg(HttpStatus.BAD_REQUEST.getReasonPhrase());
			}
		} catch (IOException e) {
			log.error("createSiteMsg Exception :{} ", e);
			response.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setRetMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
		return response;
	}

	@ApiOperation("Update  Site  Message ")
	@PostMapping(value = "/message/update")
	public BaseResponse updateSiteMsg(@RequestBody UpdateSiteMsgRequest request) {
		return siteService.updateSiteMsg(request);
	}

	@ApiOperation("Delete  Site Message ")
	@PostMapping(value = "/message/delete")
	public BaseResponse deleteSiteMsg(@RequestBody DeleteSiteMsgRequest request) {
		BaseResponse response = new BaseResponse();
		if (!CollectionUtils.isEmpty(request.getIds()) && StringUtils.isNotBlank(request.getOperator())) {
			response = siteService.deleteSiteMsg(request);
		} else {
			response.setRetCode(HttpStatus.BAD_REQUEST.value());
			response.setRetMsg(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		return response;
	}

	@PostMapping(value = "/message/read")
	@BmoPrivilege(name = "readNotifications", description = "This api is used to read  notifications")
	@ApiOperation("Read  Site Message")
	public BaseResponse readSiteMsg(@RequestBody ReadSiteMsgRequest request) {
		BaseResponse response = new BaseResponse();
		log.info(" Read  Site Message:  request:{} ", request);
		if (!CollectionUtils.isEmpty(request.getIds())) {
			response = siteService.readSiteMsg(request);
		} else {
			response.setRetCode(HttpStatus.BAD_REQUEST.value());
			response.setRetMsg(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		return response;
	}

	@PostMapping(value = "/message/list")
	@ApiOperation("List  Site  Message")
	public ListSiteMsgResponse listSiteMsg(@RequestBody ListSiteMsgRequest request) {
		log.info(" List  Site Message:  request:{} ", request);
		return siteService.listSiteMsg(request);
	}

	@GetMapping("/message/select/{siteMessageId}")
	@ApiOperation(value = "select site message")
	public GetSiteMsgResponse getSiteMsg(@PathVariable Long siteMessageId) {
		log.info(" Get  Site Message:  siteMessageId:{} ", siteMessageId);
		return siteService.getSiteMsg(siteMessageId);
	}

	@GetMapping("/message/search/{userId}")
	@BmoPrivilege(name = "getUserNotifications", description = "This api is used to get  user notifications by userId")
	@ApiOperation(value = "select site messages")
	public UserSiteMsgResponse getUsertSiteMessage(@PathVariable Long userId) {
		UserSiteMsgResponse response = new UserSiteMsgResponse();
		if (userId != null) {
			response = siteService.getUserSiteMsg(userId);
		} else {
			response.setRetCode(HttpStatus.BAD_REQUEST.value());
			response.setRetMsg(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		return response;
	}

	@GetMapping("/message/count/{userId}")
	@BmoPrivilege(name = "countNotifications", description = "This api is used to count total and unread notifications")
	@ApiOperation(value = "count site messages")
	public UserSiteMessageResponse countUsertSiteMessage(@PathVariable Long userId) {
		UserSiteMessageResponse response = new UserSiteMessageResponse();
		if (userId != null) {
			response = siteService.countUserSiteMsg(userId);
		} else {
			response.setRetCode(HttpStatus.BAD_REQUEST.value());
			response.setRetMsg(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		return response;
	}
}
