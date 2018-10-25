package com.alpha.message.dao.entiy.push;

import com.bmo.ibackend.persistence.Column;
import com.bmo.ibackend.persistence.Id;
import com.bmo.ibackend.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table
public class PushMessage {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long pushMessageTemplateId; // SQL Type: BIGINT size:19
	@Column
	String receivers; // SQL Type: TEXT size:65535
	@Column
	String tags; // SQL Type: TEXT size:65535
	@Column
	String alias; // SQL Type: TEXT size:65535
	@Column
	Integer audienceType; // SQL Type: TINYINT size:3
	@Column
	String application; // SQL Type: VARCHAR size:100
	@Column
	String contentType; // SQL Type: VARCHAR size:100
	@Column
	String extras; // SQL Type: TEXT size:65535
	@Column
	Integer priority; // SQL Type: TINYINT size:3
	@Column
	Integer state; // SQL Type: TINYINT size:3
	@Column
	Integer immediate; // SQL Type: TINYINT size:3
	@Column
	java.util.Date scheduleTime; // SQL Type: TIMESTAMP size:19
	@Column
	java.util.Date expireTime; // SQL Type: TIMESTAMP size:19
	@Column
	java.util.Date sentTime; // SQL Type: TIMESTAMP size:19
	@Column
	String sentResult; // SQL Type: VARCHAR size:50
	@Column
	java.util.Date createdTime; // SQL Type: TIMESTAMP size:19
	@Column
	String createdBy; // SQL Type: VARCHAR size:36
	@Column
	java.util.Date updatedTime; // SQL Type: TIMESTAMP size:19
	@Column
	String updatedBy; // SQL Type: VARCHAR size:36
}
