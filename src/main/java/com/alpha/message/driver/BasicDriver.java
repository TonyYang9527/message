package com.alpha.message.driver;

import com.alpha.message.service.channel.entity.ChannelReq;
import com.alpha.message.service.channel.entity.ChannelResp;

public interface BasicDriver {
	public ChannelResp send(ChannelReq req);

	public DriverType getType();

}
