package com.alpha.message.ifacade.response.sms;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.SmsMsgTemplateVo;

public class GetSmsMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = -7245738194833888347L;

	protected SmsMsgTemplateVo template;

	public SmsMsgTemplateVo getTemplate() {
		return template;
	}

	public void setTemplate(SmsMsgTemplateVo template) {
		this.template = template;
	}
}
