package com.alpha.message.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifiedEmailEventData {

	@ApiModelProperty(notes = "userId ", position = 1)
	Long userId;

	@ApiModelProperty(notes = "email", position = 2)
	String email;
}
