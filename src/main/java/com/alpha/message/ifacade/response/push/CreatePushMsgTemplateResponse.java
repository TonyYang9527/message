package com.alpha.message.ifacade.response.push;

import com.alpha.message.ifacade.response.BaseResponse;

public class CreatePushMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = 8061307794722743817L;
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
