package com.alpha.message.model.vo;

import java.io.Serializable;

public class EmailMsgTemplateVo implements Serializable {

	private static final long serialVersionUID = -3650679872095824244L;

	private	Long id; // SQL Type: BIGINT size:19
	
	private	String name; // SQL Type: VARCHAR size:50
	
	private	String title; // SQL Type: VARCHAR size:100
	
	private	String content; // SQL Type: VARCHAR size:5000
	
	private Integer type; // SQL Type: SMALLINT size:5
	
	private Integer state; // SQL Type: TINYINT size:3
	
	private Integer priority; // SQL Type: TINYINT size:3
	
	private String fromAddress; // SQL Type: VARCHAR size:50
	
	private String senderName; // SQL Type: VARCHAR size:100
	
	private	java.util.Date createdTime; // SQL Type: DATETIME size:19
	
	private	String createdBy; // SQL Type: VARCHAR size:36
	
	private java.util.Date updatedTime; // SQL Type: DATETIME size:19
	
	private String updatedBy; // SQL Type: VARCHAR size:36
	
	private java.util.Date deletedTime; // SQL Type: DATETIME size:19
	
	private String deletedBy; // SQL Type: VARCHAR size:36
	
	private Integer deleted; // SQL Type: TINYINT size:3


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public String getFromAddress() {
		return fromAddress;
	}


	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}


	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	public java.util.Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(java.util.Date createdTime) {
		this.createdTime = createdTime;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public java.util.Date getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(java.util.Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public java.util.Date getDeletedTime() {
		return deletedTime;
	}


	public void setDeletedTime(java.util.Date deletedTime) {
		this.deletedTime = deletedTime;
	}


	public String getDeletedBy() {
		return deletedBy;
	}


	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}


	public Integer getDeleted() {
		return deleted;
	}


	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "EmailMsgTemplateVo [id=" + id + ", name=" + name + ", title=" + title + ", content=" + content + ", type=" + type
				+ ", msgPriority=" + priority + ", state=" + state + ", senderName=" + senderName + ", fromAddress="
				+ fromAddress + ", createdTime=" + createdTime + ", createdBy=" + createdBy + ", updatedTime=" + updatedTime
				+ ", updatedBy=" + updatedBy  + "]";
	}

}
