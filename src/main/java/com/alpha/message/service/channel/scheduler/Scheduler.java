package com.alpha.message.service.channel.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alpha.message.service.impl.email.AsynchronousService;

@Component
public class Scheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

	@Autowired
	private AsynchronousService checkAsyncService;

	@Scheduled(fixedRate = 5000, initialDelay = 1000)
	public void checkTheScedule() throws InterruptedException {
		LOGGER.info("Called checkTheScedule");
		checkAsyncService.printEmployees();
	}

}
