package com.alpha.message.service.driver.entity.request;

import java.io.File;
import java.util.List;

import com.alpha.message.common.enums.ChannelType;

public class EmailChannelReq extends ChannelReq {

	private static final long serialVersionUID = 5270502773229708322L;
	private final String from;
	private final String title;
	private final String senderName;
	private final Boolean isHtmlText;
	private final List<File> attachmentFiles;
	private final Boolean checkSkip;

	public EmailChannelReq(Long id, String[] target, String content, String from, String title, String senderName,
			Boolean isHtmlText,List<File> attachmentFiles ,Boolean checkSkip) {
		super.setId(id);
		super.setContent(content);
		super.setTarget(target);
		super.setChannelType(ChannelType.EMAIL);
		this.from = from;
		this.title = title;
		this.senderName = senderName;
		this.isHtmlText = isHtmlText;
		this.attachmentFiles = attachmentFiles;
		this.checkSkip = checkSkip;
	}

	public String getFrom() {
		return from;
	}

	public String getTitle() {
		return title;
	}

	public String getSenderName() {
		return senderName;
	}

	public Boolean getIsHtmlText() {
		return isHtmlText;
	}

	public List<File> getAttachmentFiles() {
		return attachmentFiles;
	}

	public Boolean getCheckSkip() {
		return checkSkip;
	}
	
}
