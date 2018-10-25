package com.alpha.message.service.driver.entity.response;

import java.io.Serializable;

import com.alpha.message.common.enums.ChannelType;

public class ChannelResp implements Serializable {

	private static final long serialVersionUID = 7085375802793835365L;

	private Long id;
	private ChannelType channelType;
	private boolean isSuccess;
	private String code;
	private String result;

	public ChannelResp(Long id, ChannelType channelType, boolean isSuccess, String code, String result) {
		this.id = id;
		this.channelType = channelType;
		this.isSuccess = isSuccess;
		this.code = code;
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
