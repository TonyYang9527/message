package com.alpha.message.ifacade.response.site;

import java.util.List;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.SiteMsgTemplateVo;

public class ListSiteTemplateResponse  extends BaseResponse {

	private static final long serialVersionUID = -4920280232262282506L;

	protected List<SiteMsgTemplateVo> result;

	public List<SiteMsgTemplateVo> getResult() {
		return result;
	}

	public void setResult(List<SiteMsgTemplateVo> result) {
		this.result = result;
	}
}
