package com.alpha.message.ifacade.request.site;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SiteMsgRequest {
	
	/*****Not null****/
	String templateName;
	String title;
	Map<String, Object> properties;
	String sender;
	String receiverId;

	/*****optional param****/
	String operator;
	Integer newType;
	Integer type;
	Integer priority;
	String content;
	String addition;
	Long scheduleTime;
	Long expiredTime;
}
