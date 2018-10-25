package com.alpha.message.service.event;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bmo.ibackend.event.EventListener;
import com.bmo.ibackend.event.EventName;
import com.bmo.ibackend.model.BmoEvent;
import com.alpha.message.ifacade.facade.UserDeviceFacade;
import com.alpha.message.ifacade.request.push.UserDeviceRequest;
import com.alpha.message.ifacade.response.push.UserDeviceResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@EventName("SyncCreatePushUser")
@Slf4j
@Transactional
public class EnrollDeviceEventHandler implements EventListener<BmoEvent> {

	@Autowired
	private UserDeviceFacade userDevice;

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onEvent(BmoEvent event) {
		try {
			log.info("PushBindEventHandler -- <<<<<<<<<Push Bind Event>>>>>>>>. event:{}", event);
			JsonNode node = mapper.readValue(event.getData(), JsonNode.class);
			UserDeviceResponse response = null;
			UserDeviceRequest request = null;
			if(node.isObject()) {
				request = JSON.parseObject(event.getData(), UserDeviceRequest.class);
			}
			if (request != null) {
				response = userDevice.enrollUserDevice(request);
			}
			log.info("PushBindEventHandler -- Push Bind Event. Request:{},Response:{}", request, response);
		} catch (IOException e) {
			log.error("PushBindEventHandler -- Push Bind Event. Event:{},Exception:{}", event, e);
		}

	}

}
