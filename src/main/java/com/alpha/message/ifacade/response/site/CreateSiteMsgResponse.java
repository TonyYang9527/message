package com.alpha.message.ifacade.response.site;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import com.alpha.message.ifacade.response.BaseResponse;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSiteMsgResponse extends BaseResponse {
	private static final long serialVersionUID = -5209092304469301018L;
	Long id;
}
