package com.alpha.message.ifacade.request.email;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteEmailMsgTemplateRequest {
	
	Set<Long> ids;
	String operator;
}
