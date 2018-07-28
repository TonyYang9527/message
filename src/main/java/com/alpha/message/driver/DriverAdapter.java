package com.alpha.message.driver;

import org.springframework.beans.factory.annotation.Autowired;

import com.alpha.message.service.channel.entity.ChannelReq;
import com.alpha.message.service.channel.entity.ChannelResp;

public class DriverAdapter {
	@Autowired
	private DriverLoader driverLoader;

	public ChannelResp send(ChannelReq req) {
		driverLoader.getDriver(DriverType.EMAIL).send(req);
		return null;
	}
}
