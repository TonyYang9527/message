package com.alpha.message.service.driver.entity.request;

import java.io.Serializable;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alpha.message.common.enums.ChannelType;

public class ChannelReq implements Serializable {

	private static final long serialVersionUID = -474864395452292999L;

	private Long id;
	private String[] target;
	private String content;
	private ChannelType channelType;
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

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}



	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("target", JSON.toJSONString(this.target));
		json.put("content", this.content);
		json.put("channelType", this.channelType);
		json.put("type", this.type);
		return json.toString();
	}
}
