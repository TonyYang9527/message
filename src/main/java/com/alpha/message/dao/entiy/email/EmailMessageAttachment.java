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
public class EmailMessageAttachment {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long emailMessageId; // SQL Type: BIGINT size:19
	@Column
	String name; // SQL Type: VARCHAR size:50
	@Column
	String path; // SQL Type: VARCHAR size:100
	@Column
	java.util.Date createdTime; // SQL Type: DATETIME size:19
}
