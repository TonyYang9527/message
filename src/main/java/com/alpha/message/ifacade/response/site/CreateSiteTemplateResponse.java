package com.alpha.message.ifacade.response.site;

import com.alpha.message.ifacade.response.BaseResponse;

public class CreateSiteTemplateResponse  extends BaseResponse {

	private static final long serialVersionUID = -8952383144806409628L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
