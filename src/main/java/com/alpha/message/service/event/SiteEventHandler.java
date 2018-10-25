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
import com.alpha.message.ifacade.facade.SiteFacade;
import com.alpha.message.ifacade.request.site.SiteMsgRequest;
import com.alpha.message.ifacade.response.site.CreateSiteMsgResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@EventName("SendSite")
@Slf4j
@Transactional
public class SiteEventHandler implements EventListener<BmoEvent> {
	@Autowired
	private SiteFacade siteService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onEvent(BmoEvent event) {
		try {
			log.info("SiteEventHandler -- Site Message Event. event:{}", event);
			JsonNode node = mapper.readValue(event.getData(), JsonNode.class);
			List<SiteMsgRequest> requests = null;
			SiteMsgRequest request = null;

			if (node.isArray()) {
				requests = JSON.parseArray(event.getData(), SiteMsgRequest.class);
			} else if (node.isObject()) {
				request = JSON.parseObject(event.getData(), SiteMsgRequest.class);
			}

			if (CollectionUtils.isEmpty(requests)) {
				requests = new ArrayList<SiteMsgRequest>();
			}
			if (request != null) {
				requests.add(request);
			}
			CreateSiteMsgResponse response = null;
			for (SiteMsgRequest req : requests) {
				response = siteService.createSiteMsg(req);
				log.info("SiteEventHandler -- Site Message Event. Request:{},Response:{}", req, response);
			}
		} catch (Exception e) {
			log.error("SiteEventHandler -- Site Message Event. Failed.Event:{},Exception:{}", event, e);
		}
	}

}
