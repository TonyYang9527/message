package com.alpha.message.service.channel.task;

import com.alpha.message.model.queue.QueuePriorityVo;
import com.alpha.message.service.channel.ChannelService;
import com.alpha.message.support.SpringApplicationContextHolder;

public  abstract class BaseTask implements Runnable{

	   /** 队列中的优先级对象. */
    protected QueuePriorityVo queuePriorityVo;

    protected ChannelService channelService = SpringApplicationContextHolder.getSpringBean(ChannelService.class);

    public void setQueuePriorityVo(QueuePriorityVo queuePriorityVo) {
        this.queuePriorityVo = queuePriorityVo;
    }

    /**
     * 发送业务逻辑实现. 1. 获取短信主体 2. 获取短信模板 3. 获取短信Key/Value 4. 拼装内容发送至网关 5. 更新保存结果
     */
    public abstract void sendTask();

    @Override
    public void run() {
        sendTask();
    }

}
