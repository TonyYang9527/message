package com.alpha.message.ifacade.request.push;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@ApiModel
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendPushRequest {
	@ApiModelProperty(notes = "templateName", position = 0)
	String templateName;

	@ApiModelProperty(notes = "properties", position = 1)
	Map<String, Object> properties;

	@ApiModelProperty(notes = "receivers", position = 2)
	String receivers;

	@ApiModelProperty(notes = "application", position = 3)
	String application;

	@ApiModelProperty(notes = "extras", position = 4)
	Map<String, String> extras;

	@ApiModelProperty(notes = "createdBy", position = 5)
	String createdBy;

}
