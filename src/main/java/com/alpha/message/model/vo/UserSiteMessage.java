package com.alpha.message.model.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSiteMessage {

	@ApiModelProperty(notes = "userId ", position = 1)
	String userId;
	@ApiModelProperty(notes = "state  (0:draft,10: Send Pending,15 JOB Processed,20:Sending,30:Send Done,40:Readed,99:Send Failed) ", position = 2)
	Integer state;
	@ApiModelProperty(notes = "title ", position = 3)
	String title;
	@ApiModelProperty(notes = "content ", position = 4)
	String content;
	@ApiModelProperty(notes = "link ", position = 5)
	String link;
	@ApiModelProperty(notes = "createTime ",  position =6)
	Date createTime;
	@ApiModelProperty(notes = "siteMessageId ", position = 7)
	Long siteMessageId;
}
