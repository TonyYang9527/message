package com.alpha.message.ifacade.facade;

import java.io.IOException;

import com.alpha.message.ifacade.request.email.CreateEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.DeleteEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.DisableEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.EnableEmailMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.email.SendEmailRequest;
import com.alpha.message.ifacade.request.email.UpdateEmailMsgTemplateRequest;
import com.alpha.message.ifacade.response.email.CreateEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.DeleteEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.DisableEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.EnableEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.GetEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.ListEmailMsgTemplateResponse;
import com.alpha.message.ifacade.response.email.SendMessageResponse;
import com.alpha.message.ifacade.response.email.UpdateEmailMsgTemplateResponse;

public interface EmailFacade {

	public SendMessageResponse sendEmail(SendEmailRequest request)  throws IOException;

	public CreateEmailMsgTemplateResponse createEmailMsgTemplate(CreateEmailMsgTemplateRequest request);

	public DeleteEmailMsgTemplateResponse deleteEmailMsgTemplate(DeleteEmailMsgTemplateRequest request);

	public DisableEmailMsgTemplateResponse disableEmailMsgTemplate(DisableEmailMsgTemplateRequest request);

	public EnableEmailMsgTemplateResponse enableEmailMsgTemplate(EnableEmailMsgTemplateRequest request);

	public UpdateEmailMsgTemplateResponse updateEmailMsgTemplate(UpdateEmailMsgTemplateRequest request);

	public GetEmailMsgTemplateResponse getEmailMsgTemplate(Long emailTemplateId);

	public ListEmailMsgTemplateResponse listEmailMsgTemplate(ListMsgTemplateRequest request);
}
