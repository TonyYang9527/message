package com.alpha.message.ifacade.request.site;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnableSiteMsgTemplateRequest {
	Set<Long> ids;
	String operator;
}
