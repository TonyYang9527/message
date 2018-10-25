package com.alpha.message.ifacade.response.push;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.PushMsgTemplateVo;

public class GetPushMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = 6421491313981375552L;

	protected PushMsgTemplateVo template;

	public PushMsgTemplateVo getTemplate() {
		return template;
	}

	public void setTemplate(PushMsgTemplateVo template) {
		this.template = template;
	}
}
