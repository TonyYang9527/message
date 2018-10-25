package com.alpha.message.ifacade.request.push;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateRegIdRequest {
	Long id;
	String registrationId;
	String updateBy;
}
