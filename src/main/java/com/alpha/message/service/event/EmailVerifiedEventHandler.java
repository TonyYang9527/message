package com.alpha.message.service.event;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bmo.ibackend.event.EventListener;
import com.bmo.ibackend.event.EventName;
import com.bmo.ibackend.model.BmoEvent;
import com.alpha.message.dao.entiy.user.UserEmailWhitelist;
import com.alpha.message.model.vo.VerifiedEmailEventData;
import com.alpha.message.service.wrap.user.UserEmailWhitelistService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@EventName("UserEmailVerified")
@Slf4j
@Transactional
public class EmailVerifiedEventHandler implements EventListener<BmoEvent> {

	@Autowired
	private UserEmailWhitelistService whitelistService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onEvent(BmoEvent event) {
		try {
			log.info("EmailVerifiedEventHandler -- VerifiedEmail Event. event:{}", event);
			JsonNode node = mapper.readValue(event.getData(), JsonNode.class);
			VerifiedEmailEventData eventData = new VerifiedEmailEventData();
			if (node.isObject()) {
				eventData = JSON.parseObject(event.getData(), VerifiedEmailEventData.class);
			}
			if (eventData != null) {
				UserEmailWhitelist verifiedEmail = whitelistService.insertSelective(eventData.getUserId(),
						eventData.getEmail());
				log.info("EmailVerifiedEventHandler -- VerifiedEmail Event. verifiedEmail:{}", verifiedEmail);
			}
		} catch (IOException e) {
			log.error("EmailVerifiedEventHandler -- VerifiedEmail Event. Event:{},Exception:{}", event, e);
		}
	}

}
