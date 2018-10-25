package com.alpha.message.ifacade.request.sms;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateSmsMsgTemplateRequest {
	Long id;
	String name;
	String content;
	Integer type;
	Integer priority;
	Integer state;
	String operator;
	String description;
}
