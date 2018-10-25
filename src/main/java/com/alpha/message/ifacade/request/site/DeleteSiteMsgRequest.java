package com.alpha.message.ifacade.request.site;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteSiteMsgRequest {
	Set<Long> ids;
	String operator;
}
