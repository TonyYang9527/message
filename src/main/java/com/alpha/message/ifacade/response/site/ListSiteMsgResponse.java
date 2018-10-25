package com.alpha.message.ifacade.response.site;

import java.util.List;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.SiteMsgVo;

public class ListSiteMsgResponse extends BaseResponse {
	private static final long serialVersionUID = 3291014657189346108L;
	List<SiteMsgVo> result;

	public List<SiteMsgVo> getResult() {
		return result;
	}

	public void setResult(List<SiteMsgVo> result) {
		this.result = result;
	}

}
