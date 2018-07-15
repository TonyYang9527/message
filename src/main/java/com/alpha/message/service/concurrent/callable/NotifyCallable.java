package com.alpha.message.service.concurrent.callable;


import java.util.concurrent.Callable;

import com.alpha.message.service.concurrent.event.NotfiyEvent;

public class NotifyCallable implements Callable<NotfiyEvent> {

	public NotfiyEvent call() throws Exception {
		System.out.println("Task completed" );
		return new NotfiyEvent();
	}

}
