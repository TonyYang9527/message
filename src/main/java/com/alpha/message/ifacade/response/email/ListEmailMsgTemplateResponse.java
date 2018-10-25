package com.alpha.message.ifacade.response.email;

import java.util.List;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.EmailMsgTemplateVo;

public class ListEmailMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = 3849960798040338038L;
	List<EmailMsgTemplateVo> result;
	public List<EmailMsgTemplateVo> getResult() {
		return result;
	}
	public void setResult(List<EmailMsgTemplateVo> result) {
		this.result = result;
	}

}
