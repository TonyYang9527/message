package com.alpha.message.service.driver.sms;

import org.json.JSONObject;

public class SMSAgentParams {

    private String serialNo;
    private String serialPassword;
    private String key;
    private String sendUrl;
    private String sendTimeUrl;
    protected String registerUrl;
    protected String receiveUrl;
    protected String queryBalanceUrl;
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getSerialPassword() {
		return serialPassword;
	}
	public void setSerialPassword(String serialPassword) {
		this.serialPassword = serialPassword;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSendUrl() {
		return sendUrl;
	}
	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}
	public String getSendTimeUrl() {
		return sendTimeUrl;
	}
	public void setSendTimeUrl(String sendTimeUrl) {
		this.sendTimeUrl = sendTimeUrl;
	}
	public String getRegisterUrl() {
		return registerUrl;
	}
	public void setRegisterUrl(String registerUrl) {
		this.registerUrl = registerUrl;
	}
	public String getReceiveUrl() {
		return receiveUrl;
	}
	public void setReceiveUrl(String receiveUrl) {
		this.receiveUrl = receiveUrl;
	}
	public String getQueryBalanceUrl() {
		return queryBalanceUrl;
	}
	public void setQueryBalanceUrl(String queryBalanceUrl) {
		this.queryBalanceUrl = queryBalanceUrl;
	}
    
  
	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("serialNo", this.serialNo);
		json.put("serialPassword", this.serialPassword);
		json.put("key", this.key);
		json.put("sendUrl", this.sendUrl);
		json.put("sendTimeUrl", this.sendTimeUrl);
		
		json.put("registerUrl", this.registerUrl);
		json.put("receiveUrl", this.receiveUrl);
		json.put("queryBalanceUrl", this.queryBalanceUrl);
		
		return json.toString();
	}
    
}
