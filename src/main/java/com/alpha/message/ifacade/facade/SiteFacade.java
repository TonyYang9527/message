package com.alpha.message.ifacade.facade;

import java.io.IOException;

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

public interface SiteFacade {

	public CreateSiteTemplateResponse createSiteMsgTemplate(CreateSiteMsgTemplateRequest request);

	public UpdateSiteTemplateResponse updateSiteMsgTemplate(UpdateSiteMsgTemplateRequest request);

	public DeleteSiteTemplateResponse deleteSiteMsgTemplate(DeleteSiteMsgTemplateRequest request);

	public DisableSiteTemplateResponse disableSiteMsgTemplate(DisableSiteMsgTemplateRequest request);

	public EnableSiteTemplateResponse enableSiteMsgTemplate(EnableSiteMsgTemplateRequest request);

	public GetSiteTemplateResponse getSiteMsgTemplate(Long siteTemplateId);

	public ListSiteTemplateResponse listSiteMsgTemplate(ListMsgTemplateRequest request);

	public CreateSiteMsgResponse createSiteMsg(SiteMsgRequest request) throws IOException;

	public SendSiteMsgResponse sendSiteMessage(Long siteMessageId) throws IOException;

	public SendSiteMsgResponse createAndSendSiteMessage(SiteMsgRequest request) throws IOException;

	public BaseResponse updateSiteMsg(UpdateSiteMsgRequest request);

	public BaseResponse deleteSiteMsg(DeleteSiteMsgRequest request);

	public BaseResponse readSiteMsg(ReadSiteMsgRequest request);

	public ListSiteMsgResponse listSiteMsg(ListSiteMsgRequest request);

	public GetSiteMsgResponse getSiteMsg(Long siteMessageId);

	public UserSiteMsgResponse getUserSiteMsg(Long userId);

	public UserSiteMessageResponse countUserSiteMsg(Long userId);
}
