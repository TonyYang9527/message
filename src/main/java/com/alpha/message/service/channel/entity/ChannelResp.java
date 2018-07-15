package com.alpha.message.service.channel.entity;

import java.io.Serializable;

import com.alpha.message.model.enums.MsgType;

public class ChannelResp implements Serializable {

	private static final long serialVersionUID = 1203867141261489747L;
	private Long id;
	private MsgType msgType;
	private boolean isSuccess;
	private String code;
	private String result;

	/**
	 * Instantiates a new channel result.
	 * 
	 * @param id
	 * @param msgType
	 * @param isSuccess
	 * @param code
	 * @param result
	 */
	public ChannelResp(Long id, MsgType msgType, boolean isSuccess, String code, String result) {
		this.id = id;
		this.msgType = msgType;
		this.isSuccess = isSuccess;
		this.code = code;
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getCode() {
		return code;
	}

	public String getResult() {
		return result;
	}
}
