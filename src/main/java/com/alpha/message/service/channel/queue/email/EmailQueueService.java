package com.alpha.message.service.channel.queue.email;

import java.util.Date;

import com.alpha.message.model.email.EmailMessage;
import com.alpha.message.model.enums.MsgType;
import com.alpha.message.model.queue.QueuePriorityVo;
import com.alpha.message.service.channel.queue.QueueService;

public class EmailQueueService extends QueueService<EmailMessage> {

	@Override
	protected int getInitSize() {
		return 100;
	}

	@Override
	protected MsgType getQueueType() {
		return MsgType.EMAIL;
	}

	@Override
	protected QueuePriorityVo buildQueuePriorityVo(EmailMessage entity) {
		Long id = entity.getId();
		byte priority = entity.getPriority();
		Date scheduleTime = entity.getScheduleTime();
		return new QueuePriorityVo(id, scheduleTime, priority, getQueueType());
	}

}
