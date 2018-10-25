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
public class SiteMessageContent {
	@Id
	@Column
	Long id; // SQL Type: BIGINT size:19
	@Column
	Long siteMessageTemplateId; // SQL Type: BIGINT size:19
	@Column
	String title; // SQL Type: VARCHAR size:50
	@Column
	String content; // SQL Type: VARCHAR size:500
	@Column
	String addition; // SQL Type: VARCHAR size:400
	@Column
	Integer type; // SQL Type: SMALLINT size:5
	@Column
	java.util.Date createdTime; // SQL Type: DATETIME size:19
	@Column
	String createdBy; // SQL Type: VARCHAR size:36
	@Column
	java.util.Date updatedTime; // SQL Type: DATETIME size:19
	@Column
	String updatedBy; // SQL Type: VARCHAR size:36
}
