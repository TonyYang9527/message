package com.alpha.message.ifacade.response.email;

import com.alpha.message.ifacade.response.BaseResponse;

public class SendMessageResponse extends BaseResponse {

	private static final long serialVersionUID = -8748917414256434684L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
