package com.alpha.message.service.driver;

import com.alpha.message.common.enums.ChannelType;
import com.alpha.message.service.driver.entity.request.ChannelReq;
import com.alpha.message.service.driver.entity.response.ChannelResp;

public interface BasicDriver {
	public ChannelResp send(ChannelReq req) ;
	public ChannelType    getType();
	
}
