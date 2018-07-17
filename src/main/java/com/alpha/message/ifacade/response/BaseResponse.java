package com.alpha.message.ifacade.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -6330116651294255063L;

	protected int retCode;
	protected String retMsg;

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}
