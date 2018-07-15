package com.alpha.message.service.channel.executor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alpha.message.model.queue.QueuePriorityVo;
import com.alpha.message.service.channel.queue.QueueService;
import com.alpha.message.service.channel.task.BaseTask;

public abstract class BaseExecutor implements Runnable {

	protected ThreadPoolTaskExecutor threadPool;
	protected QueueService<?> queueService;
	protected String threadName;
	private volatile boolean shutdown;
	private static final int THREAD_SLEEP_TIME_IN_MS = 10;

	@Override
	public void run() {
		while (true) {
			System.out.println(" BaseExecutor run  ssssss  ");
			QueuePriorityVo queuePriorityVo = null;
			try {
				// 从 Queue 取出优先级最高的对象
				queuePriorityVo = queueService.pollFromQueue();
				if (queuePriorityVo != null) {
					BaseTask task = initTask();
					task.setQueuePriorityVo(queuePriorityVo);
					threadPool.execute(task);
				} else if (shutdown) {
					// 把队列中所有消息都发送掉，再关闭
					if (queueService.getSize() == 0) {
						threadPool.shutdown();
						return;
					} else {
						System.out.println(" BaseExecutor run  ssssss  ");
						continue;
					}
				} else {
					Thread.sleep(THREAD_SLEEP_TIME_IN_MS);
				}
			} catch (Exception e) {

			}
		}
	}

	@PostConstruct
	public void init() {
		this.threadPool = initThreadPool();
		this.queueService = initQueueService();
		this.threadName = initThreadExecutorName();
		this.shutdown = false;
		// log.info("启动{}成功", threadName);
		new Thread(this, threadName).start();
	}

	@PreDestroy
	public synchronized void shutdown() {
		this.shutdown = true;
		// log.info("关闭{}成功", threadName);
	}

	/**
	 * 初始化线程池.
	 *
	 * @return the executor service
	 */
	protected abstract ThreadPoolTaskExecutor initThreadPool();

	/**
	 * 初始化queueService.
	 *
	 * @return the executor service
	 */
	protected abstract QueueService<?> initQueueService();

	/**
	 * 初始化task.
	 *
	 * @return the executor service
	 */
	protected abstract BaseTask initTask();

	/**
	 * 初始化线程名字.
	 *
	 * @return the executor service
	 */
	protected abstract String initThreadExecutorName();

}
