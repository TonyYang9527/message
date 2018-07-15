package com.alpha.message.model.queue;

import java.io.Serializable;
import java.util.Date;

import com.alpha.message.model.enums.MsgType;

public class QueuePriorityVo implements Serializable{

	private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;
    private Date scheduleTime;
    private Byte priority;
    private MsgType msgType;

    public QueuePriorityVo(Long id, Date scheduleTime, Byte priority, MsgType msgType) {
        this.id = id;
        this.scheduleTime = scheduleTime;
        this.priority = priority;
        this.msgType = msgType;
    }

    public Long getId() {
        return id;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public Byte getPriority() {
        return priority;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    @Override
    public String toString() {
        return "QueuePriorityVo [id=" + id + ", scheduleTime=" + scheduleTime + ", priority=" + priority + ", msgType=" + msgType
                + "]";
    }
}
