package com.alpha.message.ifacade.response.push;

import java.util.List;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.PushMsgTemplateVo;

public class ListPushMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = -6839979306685804397L;

	protected List<PushMsgTemplateVo> result;

	public List<PushMsgTemplateVo> getResult() {
		return result;
	}

	public void setResult(List<PushMsgTemplateVo> result) {
		this.result = result;
	}
}
