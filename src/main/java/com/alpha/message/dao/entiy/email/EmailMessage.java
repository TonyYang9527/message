package com.alpha.message.dao.entiy.email;

import com.bmo.ibackend.persistence.Column;
import com.bmo.ibackend.persistence.Id;
import com.bmo.ibackend.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table
public class EmailMessage {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long emailMessageTemplateId; // SQL Type: BIGINT size:19
	@Column
	String toAddress; // SQL Type: VARCHAR size:3000
	@Column
	String ccAddress; // SQL Type: VARCHAR size:3000
	@Column
	String bccAddress; // SQL Type: VARCHAR size:3000
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
	java.util.Date createdTime; // SQL Type: DATETIME size:19
	@Column
	String createdBy; // SQL Type: VARCHAR size:36
	@Column
	java.util.Date updatedTime; // SQL Type: DATETIME size:19
	@Column
	String updatedBy; // SQL Type: VARCHAR size:36
}
