package com.alpha.message.ifacade.response.site;

import com.alpha.message.ifacade.response.BaseResponse;

public class UserSiteMessageResponse extends BaseResponse {

	private static final long serialVersionUID = -55694479968493548L;
	Integer total;
	Integer unread;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getUnread() {
		return unread;
	}

	public void setUnread(Integer unread) {
		this.unread = unread;
	}

}
