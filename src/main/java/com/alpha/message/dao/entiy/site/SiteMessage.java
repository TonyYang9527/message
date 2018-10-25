package com.alpha.message.dao.entiy.site;

import com.bmo.ibackend.persistence.Column;
import com.bmo.ibackend.persistence.Id;
import com.bmo.ibackend.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table
public class SiteMessage {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long siteMessageContentId; // SQL Type: BIGINT size:19
	@Column
	String receiverId; // SQL Type: VARCHAR size:50
	@Column
	String sender; // SQL Type: VARCHAR size:50
	@Column
	Integer type; // SQL Type: SMALLINT size:5
	@Column
	Integer priority; // SQL Type: TINYINT size:3
	@Column
	Integer state; // SQL Type: TINYINT size:3
	@Column
	Integer immediate; // SQL Type: TINYINT size:3
	@Column
	java.util.Date scheduleTime; // SQL Type: DATETIME size:19
	@Column
	java.util.Date expiredTime; // SQL Type: DATETIME size:19
	@Column
	java.util.Date sentTime; // SQL Type: DATETIME size:19
	@Column
	java.util.Date readTime; // SQL Type: DATETIME size:19
	@Column
	java.util.Date deletedTime; // SQL Type: DATETIME size:19
	@Column
	Integer deleted; // SQL Type: TINYINT size:3
	@Column
	java.util.Date createdTime; // SQL Type: DATETIME size:19
	@Column
	String createdBy; // SQL Type: VARCHAR size:36
	@Column
	java.util.Date updatedTime; // SQL Type: DATETIME size:19
	@Column
	String updatedBy; // SQL Type: VARCHAR size:36
}
