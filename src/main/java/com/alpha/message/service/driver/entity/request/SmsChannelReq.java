package com.alpha.message.service.driver.entity.request;

import com.alpha.message.common.enums.ChannelType;
import com.alpha.message.common.enums.SmsChannel;

public class SmsChannelReq extends ChannelReq {

	private static final long serialVersionUID = 3315216351099568390L;

	private final SmsChannel smsChannel;

	public SmsChannel getSmsChannel() {
		return smsChannel;
	}
	/**
	 * Instantiates a new sms channel req. 
	 * @param id
	 * @param target
	 * @param content
	 */
	public SmsChannelReq(Long id, String[] target, String content, short type, SmsChannel smsChannel) {
		super.setId(id);
		super.setContent(content);
		super.setTarget(target);
		 super.setChannelType(ChannelType.SMS);
		super.setType(type);
		this.smsChannel = smsChannel;
	}

}
