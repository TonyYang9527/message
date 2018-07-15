package com.alpha.message.service.channel.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alpha.message.service.channel.queue.QueueService;
import com.alpha.message.service.channel.queue.email.EmailQueueService;
import com.alpha.message.service.channel.task.BaseTask;
import com.alpha.message.service.channel.task.EmailTask;

public class EmailExecutors extends BaseExecutor {

	@Autowired
	@Qualifier("emailTaskExecutor")
	private ThreadPoolTaskExecutor emailTaskExecutor;
	@Autowired
	private EmailQueueService queueService;
	@Override
	protected ThreadPoolTaskExecutor initThreadPool() {
		return emailTaskExecutor;
	}
	@Override
	protected QueueService<?> initQueueService() {
		return queueService;
	}

	@Override
	protected BaseTask initTask() {
		return new EmailTask();
	}
	
	@Override
	protected String initThreadExecutorName() {
		return "Email Thread Executor";
	}

}
