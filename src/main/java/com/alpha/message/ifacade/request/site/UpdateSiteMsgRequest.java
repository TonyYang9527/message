package com.alpha.message.ifacade.request.site;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateSiteMsgRequest {
	Long id;
	String receiverId;
	Long siteMessageTemplateId;
	Map<String, String> properties;
	Long scheduleUtcTime;
	Long expiredUtcTime;
	String sender;
	String operator;
	Integer newType;
	Integer type;
	Integer priority;
	String title;
	String content;
	String addition;
}
