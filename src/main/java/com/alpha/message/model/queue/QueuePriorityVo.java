package com.alpha.message.model.queue;

import java.io.Serializable;
import java.util.Date;

import com.alpha.message.common.enums.ChannelType;

public class QueuePriorityVo implements Serializable{

	private static final long serialVersionUID = 5405369627879800893L;

    private Long id;
    private Date scheduleTime;
    private Integer priority;
    private ChannelType  channelType;

    /**
     *  QueuePriorityVo
     * @param id
     * @param scheduleTime
     * @param priority
     * @param msgType
     */
    public QueuePriorityVo(Long id, Date scheduleTime, Integer priority, ChannelType channelType) {
        this.id = id;
        this.scheduleTime = scheduleTime;
        this.priority = priority;
        this.channelType = channelType;
    }

    public Long getId() {
        return id;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public Integer getPriority() {
        return priority;
    }

  
    public ChannelType getChannelType() {
		return channelType;
	}


	@Override
    public String toString() {
        return "QueuePriorityVo [id=" + id + ", scheduleTime=" + scheduleTime + ", priority=" + priority + ", channelType=" + channelType.getValue()
                + "]";
    }
}
