package com.alpha.message.ifacade.response.sms;

import com.alpha.message.ifacade.response.BaseResponse;

public class CreateSmsMsgTemplateResponse  extends BaseResponse {

	private static final long serialVersionUID = -4671722576366139247L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
