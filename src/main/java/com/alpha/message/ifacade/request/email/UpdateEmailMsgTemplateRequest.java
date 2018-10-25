package com.alpha.message.ifacade.request.email;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEmailMsgTemplateRequest {
	Long id;
	String name;
	String title;
	String content;
	Integer type;
	Integer priority;
	String operator;
	String fromAddress;
	String senderName;

}
