package com.alpha.message.ifacade.response.site;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.SiteMsgVo;


public class GetSiteMsgResponse extends BaseResponse {

	private static final long serialVersionUID = -4301619541048416363L;

	protected SiteMsgVo siteMessage;

	public SiteMsgVo getSiteMessage() {
		return siteMessage;
	}

	public void setSiteMessage(SiteMsgVo siteMessage) {
		this.siteMessage = siteMessage;
	}
	
	
}
