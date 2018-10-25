package com.alpha.message.ifacade.facade;

import java.io.IOException;

import com.alpha.message.ifacade.request.push.CreatePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.DeletePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.DisablePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.EnablePushMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.ListMsgTemplateRequest;
import com.alpha.message.ifacade.request.push.SendPushRequest;
import com.alpha.message.ifacade.request.push.UpdatePushMsgTemplateRequest;
import com.alpha.message.ifacade.response.push.CreatePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.DeletePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.DisablePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.EnablePushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.GetPushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.ListPushMsgTemplateResponse;
import com.alpha.message.ifacade.response.push.SendMessageResponse;
import com.alpha.message.ifacade.response.push.UpdatePushMsgTemplateResponse;

public interface PushFacade {
	
	public SendMessageResponse sendPush(SendPushRequest request) throws IOException;

	public CreatePushMsgTemplateResponse createPushMsgTemplate(CreatePushMsgTemplateRequest request);

	public DeletePushMsgTemplateResponse deletePushMsgTemplate(DeletePushMsgTemplateRequest request);

	public DisablePushMsgTemplateResponse disablePushMsgTemplate(DisablePushMsgTemplateRequest request);

	public EnablePushMsgTemplateResponse enablePushMsgTemplate(EnablePushMsgTemplateRequest request);

	public UpdatePushMsgTemplateResponse updatePushMsgTemplate(UpdatePushMsgTemplateRequest request);

	public GetPushMsgTemplateResponse getPushMsgTemplate(Long pushTemplateId);

	public ListPushMsgTemplateResponse listPushMsgTemplate(ListMsgTemplateRequest request);
}
