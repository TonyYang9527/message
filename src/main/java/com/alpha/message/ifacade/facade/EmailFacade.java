package com.alpha.message.ifacade.facade;

import com.alpha.message.ifacade.request.SendEmailRequest;
import com.alpha.message.ifacade.response.SendMessageResponse;

public interface EmailFacade {

	public SendMessageResponse sendEmail(SendEmailRequest request);	
}
