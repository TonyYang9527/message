package com.alpha.message.ifacade.request.sms;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DisableSmsMsgTemplateRequest {
	Set<Long> ids;
	String operator;
}
