package com.alpha.message.ifacade.response.push;

import com.alpha.message.ifacade.response.BaseResponse;

public class SendMessageResponse extends BaseResponse {

	private static final long serialVersionUID = 3030007098907083011L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
