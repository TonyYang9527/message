package com.alpha.message.service.channel.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class EmailTask extends BaseTask {

	public EmailTask() {
	}

	@Override
	public void sendTask() {
		System.out.println("sendTask  ");

	}

}
