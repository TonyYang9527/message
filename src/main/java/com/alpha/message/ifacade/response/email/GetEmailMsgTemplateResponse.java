package com.alpha.message.ifacade.response.email;

import com.alpha.message.ifacade.response.BaseResponse;
import com.alpha.message.model.vo.EmailMsgTemplateVo;

public class GetEmailMsgTemplateResponse extends BaseResponse {

	private static final long serialVersionUID = 9204651530106890717L;

    protected EmailMsgTemplateVo template;

    public EmailMsgTemplateVo getTemplate() {
        return template;
    }

    public void setTemplate(EmailMsgTemplateVo template) {
        this.template = template;
    }
}
