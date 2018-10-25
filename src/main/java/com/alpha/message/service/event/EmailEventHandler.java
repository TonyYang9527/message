package com.alpha.message.service.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bmo.ibackend.event.EventListener;
import com.bmo.ibackend.event.EventName;
import com.bmo.ibackend.model.BmoEvent;
import com.alpha.message.ifacade.facade.EmailFacade;
import com.alpha.message.ifacade.request.email.SendEmailRequest;
import com.alpha.message.ifacade.response.email.SendMessageResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@EventName("SendEmail")
@Slf4j
@Transactional
public class EmailEventHandler implements EventListener<BmoEvent> {
	@Autowired
	private EmailFacade emailService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onEvent(BmoEvent event) {
		try {
			log.info("EmailEventHandler -- Email Message Event. event:{}", event);
			JsonNode node = mapper.readValue(event.getData(), JsonNode.class);
			List<SendEmailRequest> requests = null;
			SendEmailRequest request = null;
			if (node.isArray()) {
				requests = JSON.parseArray(event.getData(), SendEmailRequest.class);
			} else if (node.isObject()) {
				request = JSON.parseObject(event.getData(), SendEmailRequest.class);
			}

			if (CollectionUtils.isEmpty(requests)) {
				requests = new ArrayList<SendEmailRequest>();
			}
			if (request != null) {
				requests.add(request);
			}

			SendMessageResponse response = null;

			for (SendEmailRequest req : requests) {
				response = emailService.sendEmail(req);
				log.info("EmailEventHandler -- Email Message Event. Request:{},Response:{}", req, response);
			}

		} catch (Exception e) {
			log.error("EmailEventHandler -- Email Message Event. Event:{},Exception:{}", event, e);
		}
	}

}