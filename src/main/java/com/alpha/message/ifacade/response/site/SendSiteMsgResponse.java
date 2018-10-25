package com.alpha.message.ifacade.response.site;

import com.alpha.message.ifacade.response.BaseResponse;

public class SendSiteMsgResponse extends BaseResponse  {

	private static final long serialVersionUID = -7020185703146792511L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
