package com.alpha.message.ifacade.facade;

import com.alpha.message.ifacade.request.sms.CreateSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.DeleteSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.DisableSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.EnableSmsMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.sms.SendSmsRequest;
import com.alpha.message.ifacade.request.sms.UpdateSmsMsgTemplateRequest;
import com.alpha.message.ifacade.response.sms.CreateSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.DeleteSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.DisableSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.EnableSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.GetSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.ListSmsMsgTemplateResponse;
import com.alpha.message.ifacade.response.sms.SendSmsResponse;
import com.alpha.message.ifacade.response.sms.UpdateSmsMsgTemplateResponse;

public interface SmsFacade {

	public SendSmsResponse sendSms(SendSmsRequest request);

	public CreateSmsMsgTemplateResponse createSmsMsgTemplate(CreateSmsMsgTemplateRequest request);

	public DeleteSmsMsgTemplateResponse deleteSmsMsgTemplate(DeleteSmsMsgTemplateRequest request);

	public DisableSmsMsgTemplateResponse disableSmsMsgTemplate(DisableSmsMsgTemplateRequest request);

	public EnableSmsMsgTemplateResponse enableSmsMsgTemplate(EnableSmsMsgTemplateRequest request);

	public UpdateSmsMsgTemplateResponse updateSmsMsgTemplate(UpdateSmsMsgTemplateRequest request);

	public GetSmsMsgTemplateResponse getSmsMsgTemplate(Long smsTemplateId);

	public ListSmsMsgTemplateResponse listSmsMsgTemplate(ListMsgTemplateRequest request);
}
