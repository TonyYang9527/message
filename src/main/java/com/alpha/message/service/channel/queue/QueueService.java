package com.alpha.message.service.channel.queue;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import javax.annotation.PreDestroy;

import com.alpha.message.model.enums.MsgType;
import com.alpha.message.model.queue.QueuePriorityVo;
import com.alpha.message.service.channel.queue.comparator.QueueComparator;


public abstract class QueueService<T> {

	protected BlockingQueue<QueuePriorityVo> priorityQueue = new PriorityBlockingQueue<QueuePriorityVo>(getInitSize(),
			getComparator());
	protected Set<Long> ids = new HashSet<Long>();

	protected abstract int getInitSize();

	protected abstract MsgType getQueueType();

	protected abstract QueuePriorityVo buildQueuePriorityVo(T entity);

	protected volatile boolean shutdown = false;

	public int getSize() {
		return priorityQueue.size();
	}

	public synchronized boolean offerToQueue(QueuePriorityVo vo) {
		if (shutdown) {
			//log.warn(getQueueType() + " queue has been shutdown!, id:{}", vo.getId());
			return false;
		}
		if (ids.contains(vo.getId())) {
			return true;
		}
		if (priorityQueue.offer(vo)) {
			ids.add(vo.getId());
			return true;
		}

		return false;
	}

	public synchronized boolean offerToQueue(T entity) {
		return offerToQueue(buildQueuePriorityVo(entity));
	}

	public synchronized QueuePriorityVo pollFromQueue() {
		QueuePriorityVo vo = priorityQueue.poll();
		if (vo != null) {
			ids.remove(vo.getId());
		}
		return vo;
	}

	@PreDestroy
	public synchronized void shutdown() {
		shutdown = true;
	}

	protected Comparator<QueuePriorityVo> getComparator() {
		return new QueueComparator();
	}
}
