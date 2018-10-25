package com.alpha.message.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PushEnrollVO {

	@ApiModelProperty(notes = "userId ", position = 1)
	String userId;

	@ApiModelProperty(notes = "deviceId ", position = 2)
	String deviceId;
	
	@ApiModelProperty(notes = "platform ", position = 3)
	String platform;
	
	@ApiModelProperty(notes = "application ", position = 4)
	String application;
	
	@ApiModelProperty(notes = "registrationId ", position = 5)
	String registrationId;
}
