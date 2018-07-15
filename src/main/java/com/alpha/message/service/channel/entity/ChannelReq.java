package com.alpha.message.service.channel.entity;

import java.io.Serializable;

import com.alpha.message.model.enums.MsgType;

public class ChannelReq implements Serializable {

	private static final long serialVersionUID = -9217017920497962178L;

	private Long id;
	private String[] target;
	private String content;
	private MsgType msgType;
	private short type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getTarget() {
		return target;
	}

	public void setTarget(String[] target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

}
