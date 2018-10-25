package com.alpha.message.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alpha.message.common.enums.SendState;
import com.alpha.message.common.enums.SiteState;
import com.alpha.message.common.utils.BeanMapper;
import com.alpha.message.dao.entiy.site.SiteMessage;
import com.alpha.message.dao.entiy.site.SiteMessageContent;
import com.alpha.message.dao.entiy.site.SiteMessageTemplate;
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
import com.alpha.message.ifacade.response.site.CreateSiteMsgResponse;
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
import com.alpha.message.model.vo.SiteMsgTemplateVo;
import com.alpha.message.model.vo.SiteMsgVo;
import com.alpha.message.model.vo.UserSiteMessage;
import com.alpha.message.service.wrap.site.SiteMessageContentService;
import com.alpha.message.service.wrap.site.SiteMessagePropertyService;
import com.alpha.message.service.wrap.site.SiteMessageService;
import com.alpha.message.service.wrap.site.SiteMessageTemplateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SiteService implements SiteFacade {

	@Autowired
	private SiteMessageService siteMessageService;
	@Autowired
	private SiteMessagePropertyService propertyService;
	@Autowired
	private SiteMessageTemplateService templateService;
	@Autowired
	private SiteMessageContentService siteMessageContentService;

	@Override
	public CreateSiteTemplateResponse createSiteMsgTemplate(CreateSiteMsgTemplateRequest request) {
		CreateSiteTemplateResponse resp = new CreateSiteTemplateResponse();
		SiteMessageTemplate template = templateService.createSiteMsgTemplate(request);
		if (template == null) {
			resp.setRetMsg("Create  Site  Message  Template Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setId(template.getId());
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Create  Site  Message  Template  Success");
		log.info(" Create Site  Message  Template: Request:{},Response:{} ", request, resp);
		return resp;
	}

	@Override
	public DeleteSiteTemplateResponse deleteSiteMsgTemplate(DeleteSiteMsgTemplateRequest request) {
		DeleteSiteTemplateResponse resp = new DeleteSiteTemplateResponse();
		templateService.deleteSiteMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Delete  Site  Message  Template  Success");
		log.info(" Delete Site  Message  Template: Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public DisableSiteTemplateResponse disableSiteMsgTemplate(DisableSiteMsgTemplateRequest request) {
		DisableSiteTemplateResponse resp = new DisableSiteTemplateResponse();
		templateService.disableSiteMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Disable  Site  Message  Template  Success");
		log.info(" Disable Site  Message  Template: Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public EnableSiteTemplateResponse enableSiteMsgTemplate(EnableSiteMsgTemplateRequest request) {
		EnableSiteTemplateResponse resp = new EnableSiteTemplateResponse();
		log.info(" Enable  Site  Message  Template   :  request:{} ", request);
		templateService.enableSiteMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Enable  Site  Message  Template  Success");
		log.info(" Enable Site  Message  Template: Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public UpdateSiteTemplateResponse updateSiteMsgTemplate(UpdateSiteMsgTemplateRequest request) {
		UpdateSiteTemplateResponse resp = new UpdateSiteTemplateResponse();
		templateService.updateSiteMsgTemplate(request);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Update  Site  Message  Template  Success");
		log.info(" Update Site  Message  Template: Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public GetSiteTemplateResponse getSiteMsgTemplate(Long siteTemplateId) {
		GetSiteTemplateResponse resp = new GetSiteTemplateResponse();
		SiteMessageTemplate template = templateService.selectByPrimaryKey(siteTemplateId);
		if (template == null) {
			resp.setRetMsg("Create Email  Message  Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		SiteMsgTemplateVo siteMsgTemplateVo = BeanMapper.map(template, SiteMsgTemplateVo.class);
		resp.setTemplate(siteMsgTemplateVo);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("  Get  Site  Message  Template  Success");
		log.info(" Get Site  Message  Template: siteTemplateId:{}, Response:{} ", siteTemplateId, resp);
		return resp;
	}

	@Override
	public ListSiteTemplateResponse listSiteMsgTemplate(ListMsgTemplateRequest request) {
		ListSiteTemplateResponse resp = new ListSiteTemplateResponse();
		List<SiteMessageTemplate> templates = templateService.listSiteMsgTemplate(request);
		if (CollectionUtils.isEmpty(templates)) {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg(" List  Site  Message  Template  Failed");
			return resp;
		}
		List<SiteMsgTemplateVo> vos = BeanMapper.mapList(templates, SiteMsgTemplateVo.class);
		resp.setResult(vos);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" List  Site  Message  Template  Success");
		log.info(" List  Site  Message  Template: request:{}, Response:{} ", request, resp);
		return resp;
	}

	public SendSiteMsgResponse sendSiteMessage(Long siteMessageId) {
		SendSiteMsgResponse resp = new SendSiteMsgResponse();
		SiteMessage siteMessage = siteMessageService.getSiteMessageById(siteMessageId);
		if (siteMessage != null) {
			siteMessageService.changeSiteMessageState(siteMessageId, SendState.SENT.getValue());
			resp.setRetCode(HttpStatus.OK.value());
			resp.setRetMsg("  Send  Site  Message  Success");
		} else {
			resp.setRetCode(HttpStatus.NOT_FOUND.value());
			resp.setRetMsg("  No Found   Site  Message ,Please  try again");
		}
		log.info(" Send Site  Message  Template: siteMessageId:{}, Response:{} ", siteMessageId, resp);
		return resp;
	}

	public SendSiteMsgResponse createAndSendSiteMessage(SiteMsgRequest request) throws IOException {
		SendSiteMsgResponse resp = new SendSiteMsgResponse();
		SiteMessage siteMessage = this.saveReqToDB(request, SiteState.SEND);
		if (siteMessage == null) {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg(" Send  Site  Message Failed");
			return resp;
		}
		resp.setId(siteMessage.getId());
		resp.setRetMsg("Create  & Send  Email  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info(" Create  & Send  Site  Message : request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public CreateSiteMsgResponse createSiteMsg(SiteMsgRequest request) throws IOException {
		CreateSiteMsgResponse resp = new CreateSiteMsgResponse();
		SiteMessage siteMessage = this.saveReqToDB(request, SiteState.CREATE);
		if (siteMessage == null) {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg(" Create Site  Message  Failed");
			return resp;
		}
		resp.setId(siteMessage.getId());
		resp.setRetMsg("Create Site (Draft)  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("Create Site  Message : request:{}, Response:{}", request, resp);
		return resp;
	}

	protected SiteMessage saveReqToDB(SiteMsgRequest request, SiteState siteState) throws IOException {
		SiteMessage siteMessage = null;
		SiteMessageContent content = siteMessageContentService.saveSiteContent(request);
		if (content != null) {
			siteMessage = siteMessageService.saveSiteMessageByReq(content.getId(), request, siteState);
			if (siteMessage != null && !CollectionUtils.isEmpty(request.getProperties())) {
				propertyService.saveSiteMessageProperty(siteMessage.getId(), request.getProperties());
			}
		}
		return siteMessage;
	}

	@Override
	public BaseResponse updateSiteMsg(UpdateSiteMsgRequest request) {
		BaseResponse resp = new BaseResponse();
		siteMessageService.updateSiteMsg(request);
		resp.setRetMsg("Update Site  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("Update  Site  Message : request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public BaseResponse deleteSiteMsg(DeleteSiteMsgRequest request) {
		BaseResponse resp = new BaseResponse();
		siteMessageService.deleteSiteMsg(request);
		resp.setRetMsg("Delete Site  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("Delete Site  Message : request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public BaseResponse readSiteMsg(ReadSiteMsgRequest request) {
		BaseResponse resp = new BaseResponse();
		siteMessageService.readSiteMsg(request);
		resp.setRetMsg("Read Site  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("Read Site  Message : request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public ListSiteMsgResponse listSiteMsg(ListSiteMsgRequest request) {
		ListSiteMsgResponse resp = new ListSiteMsgResponse();
		List<SiteMessage> siteMessages = siteMessageService.listSiteMessage(request);
		if (CollectionUtils.isEmpty(siteMessages)) {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg("List Site  Message Failed");
			return resp;
		}
		List<SiteMsgVo> result = BeanMapper.mapList(siteMessages, SiteMsgVo.class);
		resp.setResult(result);
		resp.setRetMsg("List Site  Message  Success.");
		resp.setRetCode(HttpStatus.OK.value());
		log.info("List Site  Message : request:{}, Response:{}", request, resp);
		return resp;
	}

	@Override
	public GetSiteMsgResponse getSiteMsg(Long siteMessageId) {
		GetSiteMsgResponse resp = new GetSiteMsgResponse();
		SiteMessage siteMessage = siteMessageService.getSiteMessageById(siteMessageId);
		if (siteMessage == null) {
			resp.setRetCode(HttpStatus.NOT_FOUND.value());
			resp.setRetMsg(HttpStatus.NOT_FOUND.name());
			return resp;
		}
		SiteMessageContent siteMessageContent = siteMessageContentService
				.getSiteMessageContent(siteMessage.getSiteMessageContentId());
		SiteMsgVo siteMsgVo = buildSiteMsgVo(siteMessage);
		if (siteMsgVo == null) {
			resp.setSiteMessage(siteMsgVo);
			resp.setRetCode(HttpStatus.NOT_FOUND.value());
			resp.setRetMsg(HttpStatus.NOT_FOUND.name());
			return resp;
		}
		siteMsgVo.setSiteMessageContent(siteMessageContent.getContent());
		resp.setSiteMessage(siteMsgVo);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("Get  Site  Message  Success.");
		log.debug("Get Site  Message : siteMessageId:{}, Response:{}", siteMessageId, resp);
		return resp;
	}

	@Override
	public UserSiteMsgResponse getUserSiteMsg(Long userId) {
		UserSiteMsgResponse resp = new UserSiteMsgResponse();
		List<UserSiteMessage> messages = siteMessageService.getUserSiteMessage(userId);
		if (CollectionUtils.isEmpty(messages)) {
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setRetMsg(" Get  User Site  Message Failed");
			return resp;
		}
		resp.setMessages(messages);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("Get  User Site  Message  Success.");
		log.debug("Get User Site  Message: userId:{}, Response:{}", userId, resp);
		return resp;
	}

	private SiteMsgVo buildSiteMsgVo(SiteMessage siteMessage) {
		if (siteMessage == null)
			return null;
		SiteMsgVo vo = new SiteMsgVo();
		vo.setId(siteMessage.getId());
		vo.setReceiverId(siteMessage.getReceiverId());
		vo.setSender(siteMessage.getSender());
		vo.setScheduleTime(siteMessage.getScheduleTime());
		vo.setExpiredTime(siteMessage.getExpiredTime());
		vo.setSentTime(siteMessage.getSentTime());
		vo.setReadTime(siteMessage.getReadTime());
		vo.setCreatedTime(siteMessage.getCreatedTime());

		if (siteMessage.getImmediate() != null)
			vo.setImmediate(Boolean.valueOf(siteMessage.getImmediate().toString()));
		else
			vo.setImmediate(false);

		vo.setPriority(siteMessage.getPriority());
		vo.setState(siteMessage.getState());
		vo.setNewType(siteMessage.getType());
		return vo;
	}

	@Override
	public UserSiteMessageResponse countUserSiteMsg(Long userId) {
		UserSiteMessageResponse resp = new UserSiteMessageResponse();
		Integer total = siteMessageService.countUserSiteMsgTotal(userId);
		Integer unread = siteMessageService.countUserSiteMsgUnread(userId);
		resp.setTotal(total == null ? 0 : total);
		resp.setUnread(unread == null ? 0 : unread);
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg("Count Site  Message  Success.");
		log.info("Count Site  Message. userId:{}, Response :{} ", userId, resp);
		return resp;
	}

}
