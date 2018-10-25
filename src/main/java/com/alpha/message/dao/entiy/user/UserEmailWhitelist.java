package com.alpha.message.dao.entiy.user;

import com.bmo.ibackend.persistence.Column;
import com.bmo.ibackend.persistence.Id;
import com.bmo.ibackend.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table
public class UserEmailWhitelist {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long userId; // SQL Type: BIGINT size:19
	@Column
	String email; // SQL Type: VARCHAR size:100
	@Column
	java.util.Date createdTime; // SQL Type: TIMESTAMP size:19
	@Column
	String createdBy; // SQL Type: VARCHAR size:36
	@Column
	java.util.Date updatedTime; // SQL Type: TIMESTAMP size:19
	@Column
	String updatedBy; // SQL Type: VARCHAR size:36
	@Column
	java.util.Date deletedTime; // SQL Type: TIMESTAMP size:19
	@Column
	Integer deleted; // SQL Type: TINYINT size:3
}
