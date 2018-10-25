package com.alpha.message.ifacade.request.push;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePushMsgTemplateRequest {
	Long id;
	String name;
	String title;
	String content;
	String addition;
	Integer type;
	Integer priority;
	Integer state;
	String operator;
	String description;
}
