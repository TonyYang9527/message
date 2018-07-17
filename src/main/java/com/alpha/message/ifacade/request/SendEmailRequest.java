package com.alpha.message.ifacade.request;

import java.util.Map;

public class SendEmailRequest extends BaseRequest {

	private static final long serialVersionUID = 7941190887390001456L;

	String toAddresses;
	String ccAddresses;
	String bccAddresses;
	Long emailMessageTemplateId;
	Map<String, String> properties;
	Long scheduleUtcTime;
	Long expiredUtcTime;
	String userId;
	Map<String, byte[]> attachments;

	public String getToAddresses() {
		return toAddresses;
	}

	public void setToAddresses(String toAddresses) {
		this.toAddresses = toAddresses;
	}

	public String getCcAddresses() {
		return ccAddresses;
	}

	public void setCcAddresses(String ccAddresses) {
		this.ccAddresses = ccAddresses;
	}

	public String getBccAddresses() {
		return bccAddresses;
	}

	public void setBccAddresses(String bccAddresses) {
		this.bccAddresses = bccAddresses;
	}

	public Long getEmailMessageTemplateId() {
		return emailMessageTemplateId;
	}

	public void setEmailMessageTemplateId(Long emailMessageTemplateId) {
		this.emailMessageTemplateId = emailMessageTemplateId;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Long getScheduleUtcTime() {
		return scheduleUtcTime;
	}

	public void setScheduleUtcTime(Long scheduleUtcTime) {
		this.scheduleUtcTime = scheduleUtcTime;
	}

	public Long getExpiredUtcTime() {
		return expiredUtcTime;
	}

	public void setExpiredUtcTime(Long expiredUtcTime) {
		this.expiredUtcTime = expiredUtcTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, byte[]> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, byte[]> attachments) {
		this.attachments = attachments;
	}

}
