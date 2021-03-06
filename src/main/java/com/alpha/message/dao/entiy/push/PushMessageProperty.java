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
public class PushMessageProperty {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long pushMessageId; // SQL Type: BIGINT size:19
	@Column
	String propKey; // SQL Type: VARCHAR size:50
	@Column
	String propValue; // SQL Type: VARCHAR size:500
	@Column
	java.util.Date createdTime; // SQL Type: DATETIME size:19
}
