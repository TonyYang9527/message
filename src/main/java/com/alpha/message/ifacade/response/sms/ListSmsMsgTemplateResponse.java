package com.alpha.message.ifacade.response.sms;

import java.util.List;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.SmsMsgTemplateVo;

public class ListSmsMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = 6865747852677793157L;

	 protected List<SmsMsgTemplateVo> result;

	public List<SmsMsgTemplateVo> getResult() {
		return result;
	}

	public void setResult(List<SmsMsgTemplateVo> result) {
		this.result = result;
	}


}
