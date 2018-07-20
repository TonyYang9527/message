package com.alpha.message.driver;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DriverLoader implements ApplicationContextAware {
	private static Map<DriverType, BasicDriver> drivers = new HashMap<DriverType, BasicDriver>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, BasicDriver> map = applicationContext.getBeansOfType(BasicDriver.class);
	  	map.forEach((key, value) -> drivers.put(value.getType(), value));
	}

	public static <T extends BasicDriver> T getDriver(DriverType type) {
		return (T) drivers.get(type);
	}
}
