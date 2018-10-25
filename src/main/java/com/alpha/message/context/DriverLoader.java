package com.alpha.message.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alpha.message.common.enums.ChannelType;
import com.alpha.message.service.driver.BasicDriver;

@Component
public class DriverLoader implements ApplicationContextAware {

	private static Map<ChannelType, BasicDriver> drivers = new HashMap<ChannelType, BasicDriver>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, BasicDriver> map = applicationContext.getBeansOfType(BasicDriver.class);
		//log.info("DriverLoader load  All  Driver Start.......");
		map.forEach((key, value) -> {
			//log.info("DriverLoader Load Driver,ChannelType :{} ,DriverClass :{} ", value.getType(),
				//	value.getClass().getName());
			drivers.put(value.getType(), value);
		});
		//log.info("DriverLoader load  All  Driver End.......");
	}

	public BasicDriver getDriver(ChannelType type) {
		return drivers.get(type);
	}
}
