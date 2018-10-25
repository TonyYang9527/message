package com.alpha.message.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	
	@Value("${env.name}")
	public String envName;
	
	@Value("${message.expire.second.in.tencent}")
	public Long expireSecond;

	@Value("${android.access.id.in.tencent}")
	public Long androidAccessId;

	@Value("${ios.access.id.in.tencent}")
	public String iosAccessId;

}
