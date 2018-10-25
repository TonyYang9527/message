package com.alpha.message.ifacade.response.site;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.SiteMsgTemplateVo;

public class GetSiteTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = -963600200402669417L;
	protected SiteMsgTemplateVo template;

	public SiteMsgTemplateVo getTemplate() {
		return template;
	}

	public void setTemplate(SiteMsgTemplateVo template) {
		this.template = template;
	}
}
