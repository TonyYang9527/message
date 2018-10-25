package com.alpha.message.ifacade.response;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse implements Serializable {
	private static final long serialVersionUID = 971053907781988104L;
	int retCode;
	String retMsg;
}
