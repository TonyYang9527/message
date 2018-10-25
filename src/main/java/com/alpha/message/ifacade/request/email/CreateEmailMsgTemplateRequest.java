package com.alpha.message.ifacade.request.email;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEmailMsgTemplateRequest {
	String name;
	String title;
	String content;
	Integer type;
	Integer priority;
	String fromAddress;
	String senderName;
	String createdBy;
}
