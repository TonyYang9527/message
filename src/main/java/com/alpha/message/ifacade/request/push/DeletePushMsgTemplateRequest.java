package com.alpha.message.ifacade.request.push;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeletePushMsgTemplateRequest {
	Set<Long> ids;
	String operator;
}
