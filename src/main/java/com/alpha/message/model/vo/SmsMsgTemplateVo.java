package com.alpha.message.model.vo;

import java.io.Serializable;
import java.util.Date;

public class SmsMsgTemplateVo implements Serializable {

	private static final long serialVersionUID = 2712563207697216727L;

	private Long id;

	private String name;

	private String content;

	private Integer type;

	private Integer msgPriority;

	private Integer state;

	private String description;

	private Date createdTime;

	private String createdBy;

	private Date updatedTime;

	private String updatedBy;

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

	public Integer getMsgPriority() {
		return msgPriority;
	}

	public void setMsgPriority(Integer msgPriority) {
		this.msgPriority = msgPriority;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "SmsMsgTemplateVo [id=" + id + ", name=" + name + ", content=" + content + ", type=" + type + ", msgPriority="
				+ msgPriority + ", state=" + state + ", description=" + description
				+ ", createdTime=" + createdTime + ", createdBy=" + createdBy + ", updatedTime=" + updatedTime + ", updatedBy="
				+ updatedBy + "]";
	}

}
