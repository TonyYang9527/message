package com.alpha.message.service.wrap.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.context.DriverLoader;
import com.alpha.message.service.driver.entity.request.ChannelReq;
import com.alpha.message.service.driver.entity.response.ChannelResp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChannelServices {

	@Autowired
	protected DriverLoader loader;

	public ChannelResp request(ChannelReq req) {
		if (req.getChannelType() == null) {
			log.error("ChannelServices ChannelType  is Null.");
			return null;
		}
		return loader.getDriver(req.getChannelType()).send(req);
	}

}
