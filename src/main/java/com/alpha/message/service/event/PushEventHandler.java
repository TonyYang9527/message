package com.alpha.message.service.event;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bmo.ibackend.event.EventListener;
import com.bmo.ibackend.event.EventName;
import com.bmo.ibackend.model.BmoEvent;
import com.alpha.message.ifacade.facade.PushFacade;
import com.alpha.message.ifacade.request.push.SendPushRequest;
import com.alpha.message.ifacade.response.push.SendMessageResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;




@Component
@EventName("PushMessage")
@Slf4j
@Transactional
public class PushEventHandler implements EventListener<BmoEvent> {

	@Autowired
	private PushFacade pushService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onEvent(BmoEvent event) {
		try {
			log.info("PushEventHandler -- <<<<<<<<<Push Message Event >>>>>>>>. event:{}", event);
			JsonNode node = mapper.readValue(event.getData(), JsonNode.class);
			SendMessageResponse response = null;
			SendPushRequest request = null;
			if(node.isObject()) {
				request = JSON.parseObject(event.getData(), SendPushRequest.class);
			}
			if (request != null) {
				response = pushService.sendPush(request);
			}
			log.info("PushEventHandler -- Push Message Event. Request:{},Response:{}", request, response);
		} catch (IOException e) {
			log.error("PushEventHandler -- Push Message Event. Event:{},Exception:{}", event, e);
		}
	}

}
