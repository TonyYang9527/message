package com.alpha.message.ifacade.response.site;

import java.util.List;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.UserSiteMessage;

public class UserSiteMsgResponse extends BaseResponse {
	private static final long serialVersionUID = 3528178478433569843L;

	List<UserSiteMessage> messages;

	public List<UserSiteMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<UserSiteMessage> messages) {
		this.messages = messages;
	}

}
