package com.alpha.message.ifacade.response.email;

import com.alpha.message.ifacade.response.BaseResponse;

public class CreateEmailMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = 5908751785033295246L;

	protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
