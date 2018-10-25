package com.alpha.message.service.driver.entity.request;

import java.util.Map;

import com.alpha.message.common.enums.ChannelType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class PushRequest extends ChannelReq {

	private static final long serialVersionUID = -5529813169834013318L;

	String title;
	Map<String, String> extras;
	String[] receivers;
	String application;
	String contentType;

	public PushRequest(Long id, String title, String content, Map<String, String> extras, String[] receiverArrs, String application,String contentType) {
		super.setId(id);
		super.setContent(content);
		super.setChannelType(ChannelType.PUSH);
		this.title = title;
		this.extras = extras;
		this.receivers = receiverArrs;
		this.application = application;
		this.contentType = contentType;
	}
}
