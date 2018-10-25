package com.alpha.message.model.vo;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SiteMsgVo {
	Long id;
	String siteMessageContent;
	String siteMessageAddition;
	String receiverId;
	String sender;
	String title;
	Integer newType;
	Integer type;
	Integer priority;
	Integer state;
	boolean immediate;
	Date scheduleTime;
	Date expiredTime;
	Date sentTime;
	Date readTime;
	Date createdTime;
}
