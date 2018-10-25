package com.alpha.message.dao.entiy.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import com.bmo.ibackend.persistence.Column;
import com.bmo.ibackend.persistence.Id;
import com.bmo.ibackend.persistence.Table;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table
public class UserDevice {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long userId; // SQL Type: BIGINT size:19
	@Column
	String platform; // SQL Type: VARCHAR size:50
	@Column
	String deviceId; // SQL Type: VARCHAR size:100
	@Column
	String registrationId; // SQL Type: VARCHAR size:100
	@Column
	String application; // SQL Type: VARCHAR size:100
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
