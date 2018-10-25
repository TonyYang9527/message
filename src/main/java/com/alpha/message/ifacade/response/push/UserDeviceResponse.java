package com.alpha.message.ifacade.response.push;

import com.alpha.message.ifacade.response.BaseResponse;

public class UserDeviceResponse extends BaseResponse {

	private static final long serialVersionUID = -6296749978898896379L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
