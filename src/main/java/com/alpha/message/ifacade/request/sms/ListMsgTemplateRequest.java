package com.alpha.message.ifacade.request.sms;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListMsgTemplateRequest {
	String name;
	Set<Integer> states;
}
