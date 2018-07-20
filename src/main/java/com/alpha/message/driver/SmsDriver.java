package com.alpha.message.driver;

import org.springframework.stereotype.Component;
import com.alpha.message.service.channel.entity.ChannelReq;
import com.alpha.message.service.channel.entity.ChannelResp;

@Component
public class SmsDriver implements BasicDriver {

	@Override
	public ChannelResp send(ChannelReq req) {
		return null;
	}

	@Override
	public DriverType getType() {
		return DriverType.SMS;
	}

}
