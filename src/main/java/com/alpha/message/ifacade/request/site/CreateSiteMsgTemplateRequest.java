package com.alpha.message.ifacade.request.site;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSiteMsgTemplateRequest {
	String name;
	String title;
	String content;
	String addition;
	Integer type;
	Integer priority;
	Integer state;
	String sender;
	String operator;
	String description;
}
