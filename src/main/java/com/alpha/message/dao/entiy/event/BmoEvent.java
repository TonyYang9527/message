package com.alpha.message.dao.entiy.event;

import com.bmo.ibackend.persistence.Column;
import com.bmo.ibackend.persistence.Id;
import com.bmo.ibackend.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table
public class BmoEvent {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	String name; // SQL Type: VARCHAR size:64
	@Column
	String data; // SQL Type: BLOB size:65535
	@Column
	String status; // SQL Type: VARCHAR size:16
	@Column
	Integer retry; // SQL Type: INT size:10
	@Column
	java.util.Date created; // SQL Type: TIMESTAMP size:19
	@Column
	java.util.Date updated; // SQL Type: TIMESTAMP size:19
}
