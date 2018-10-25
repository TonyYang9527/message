package com.alpha.message.ifacade.request.push;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ApiModel
public class UserDeviceRequest {

	@ApiModelProperty(notes = "userId", position = 0)
	Long userId;

	@ApiModelProperty(notes = "deviceId", position = 1)
	String deviceId;

	@ApiModelProperty(notes = "platform", position = 2)
	@NotNull(message = "platform not null")
	String platform;

	@ApiModelProperty(notes = "registrationId", position = 3)
	@NotNull(message = "registrationId not null")
	String registrationId;

	@ApiModelProperty(notes = "application", position = 4)
	@NotNull(message = "application not null")
	String application;

	@ApiModelProperty(notes = "createdBy", position = 5)
	String createdBy;

}
