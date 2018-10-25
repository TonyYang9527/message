package com.alpha.message.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JPushProperties {

	@Value("${jpush.push.client.retry}")
	public int retry;

	@Value("${jpush.push.client.connection.timeout}")
	public int connectionTimeOut; // Time unit is second

	@Value("${jpush.push.client.apns.production}")
	public boolean isProduction;
	
	@Value("${jpush.push.client.apns.dev}")
	public boolean isDev;

	@Value("${jpush.push.client.time.to.live}")
	public long timeToLive; // Time unit is second

	/***** Notification IOS *****/
	@Value("${jpush.push.notification.ios.sound}")
	public String iosSound;

	@Value("${jpush.push.notification.ios.content.available}")
	public boolean iosContentAvailable;

	@Value("${jpush.push.notification.ios.mutable.content}")
	public boolean iosMutableContent;

	@Value("${jpush.push.notification.ios.enable.badge}")
	public boolean iosEnableBadge;

	@Value("${jpush.push.notification.ios.enable.sound}")
	public boolean iosEnableSound;

	@Value("${jpush.push.notification.ios.category}")
	public String iosCategory;

	/***** Notification Android *****/
	@Value("${jpush.push.notification.android.style}")
	public int androidStyle;

	@Value("${jpush.push.notification.android.alert.type}")
	public int androidAlertType;

	@Value("${jpush.push.notification.android.priority}")
	public int androidPriority;

	@Value("${jpush.push.notification.android.category}")
	public String androidCategory;

}
