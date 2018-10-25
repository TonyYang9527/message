package com.alpha.message.ifacade.response.sms;

import com.alpha.message.ifacade.response.BaseResponse;

public class SendSmsResponse  extends BaseResponse {

	private static final long serialVersionUID = -8064818273063844112L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
