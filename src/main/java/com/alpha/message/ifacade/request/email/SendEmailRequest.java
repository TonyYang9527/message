package com.alpha.message.ifacade.request.email;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class SendEmailRequest {

	/***** Not null Param ****/
	String toAddresses;
	String templateName;
	Map<String, Object> properties;
	Boolean  checkSkip =Boolean.FALSE;
	/***** optional Param ****/
	Long scheduleTime;
	Long expiredTime;
	String userId;
	Map<String, byte[]> attachments;
	String ccAddresses;
	String bccAddresses;
}
