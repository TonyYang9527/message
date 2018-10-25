package com.alpha.message.ifacade.request.sms;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendSmsRequest {
	String mobiles;
	String templateName;
	Map<String, Object> properties;
	Long scheduleUtcTime;
	Long expiredUtcTime;
	String userId;
}
