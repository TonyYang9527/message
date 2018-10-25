package com.alpha.message.service.driver.push;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alpha.message.context.JPushProperties;
import com.google.gson.JsonObject;

import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;

public class NotificationBuilder {


	public static Notification ios(JsonObject alert, Map<String, String> extras, JPushProperties props) {

		Notification notification = null;
		if (alert == null) {
			return notification;
		}
		IosNotification.Builder iosBuilder = IosNotification.newBuilder();
		iosBuilder.setAlert(alert);
		iosBuilder.setContentAvailable(props != null ? props.iosContentAvailable : false);
		iosBuilder.setMutableContent(props != null ? props.iosMutableContent : false);
		iosBuilder.setCategory(
				props != null ? (StringUtils.isNotBlank(props.iosCategory) ? props.iosCategory : null) : null);
		iosBuilder.addExtras((extras == null || extras.isEmpty()) ? null : extras);

		if (props != null && props.iosEnableBadge) {
			iosBuilder.incrBadge(1);
		} else {
			iosBuilder.disableBadge();
		}
		if (props != null && props.iosEnableSound) {
			iosBuilder.setSound(props.iosSound);
		} else {
			iosBuilder.disableSound();
		}
		return Notification.newBuilder().addPlatformNotification(iosBuilder.build()).build();
	}

	public static Notification android(JsonObject alert, Map<String, String> extras, JPushProperties props) {

		Notification notification = null;
		if (alert == null) {
			return notification;
		}

		AndroidNotification.Builder androidBuilder = AndroidNotification.newBuilder();
		androidBuilder.setAlert(alert.has("body") ? alert.get("body").getAsString() : null);
		androidBuilder.setTitle(alert.has("title") ? alert.get("title").getAsString() : null);
		androidBuilder.addExtras((extras == null || extras.isEmpty()) ? null : extras);
		androidBuilder.setCategory(
				props != null ? (StringUtils.isNotBlank(props.androidCategory) ? props.androidCategory : null) : null);
		androidBuilder.setStyle(props != null ? props.androidStyle : 0);
		androidBuilder.setAlertType(props != null ? props.androidAlertType : -1);
		androidBuilder.setPriority(props != null ? props.androidPriority : 0);

		return Notification.newBuilder().addPlatformNotification(androidBuilder.build()).build();
	}

	public static Notification winphone(JsonObject alert, String openPage, Map<String, String> extras) {
		Notification notification = null;
		if (alert == null) {
			return notification;
		}
		WinphoneNotification.Builder winphoneBuilder = WinphoneNotification.newBuilder();
		winphoneBuilder.setAlert(alert.has("body") ? alert.get("body").getAsString() : null);
		winphoneBuilder.setTitle(alert.has("title") ? alert.get("title").getAsString() : null);
		winphoneBuilder.setOpenPage(StringUtils.isNotBlank(openPage) ? openPage : null);
		winphoneBuilder.addExtras((extras == null || extras.isEmpty()) ? null : extras);

		return Notification.newBuilder().addPlatformNotification(winphoneBuilder.build()).build();
	}

}
