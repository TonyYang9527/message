package com.alpha.message.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChannelVo {

	@ApiModelProperty(notes = "sms channel", position = 1)
	Boolean sms;
	@ApiModelProperty(notes = "email channel", position = 2)
	Boolean email;
	@ApiModelProperty(notes = "push channel ", position = 3)
	Boolean push;
	@ApiModelProperty(notes = "inbox channel ", position = 4)
	Boolean inbox;
}
