package com.alpha.message.service.driver.push;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JPushDriver {

	private static String masterSecret = "3796c917c0765ae65cff4072";
	private static String appKey = "d1d036500c6fe9e863051875";

	public static PushPayload buildPushIOSAlert() {
		JsonObject jsonExtra = new JsonObject();
		jsonExtra.addProperty("title", "Music 🎶");
		jsonExtra.addProperty("body", "TOny yang setApnsProduction  true ");

		return PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(jsonExtra).setBadge(5)
								.setSound("happy").addExtra("from", "JPush").build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
	}

	public static PushPayload buildPushAndroidAlert() {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("message", "this is android messsge");

		JsonObject jsonExtra = new JsonObject();
		jsonExtra.addProperty("title", "55555555555ANYA6 🎶 ");
		jsonExtra.addProperty("body", "AndroidNotification this is  other message 45678");

		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder().setAlert("55555555555ANYA6 🎶")
								.setAlertType(1).setTitle(" Music 🎶").build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
	}

	public static PushPayload buildPushAndroidAlert2() {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("message", "this is android messsge");
		return PushPayload
				.newBuilder().setPlatform(Platform.android()).setAudience(Audience.all()).setNotification(Notification
						.android("Bob wants to play poker Notification.android 12345 ", "Game Request 12345", extras))
				.build();
	}

	public static PushPayload buildPushIOS_Android_Alert() {
		JsonObject jsonExtra = new JsonObject();
		jsonExtra.addProperty("title", "Game Request");
		jsonExtra.addProperty("body", "Bob wants to play poker");
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(jsonExtra).setBadge(5)
								.setSound("happy").addExtra("from", "JPush").build())
						.build())
				.setMessage(Message.content("this is  test message content "))
				.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();

	}

	public static void main(String[] args) {
		try {
			JPushClient jpushClient = new JPushClient(JPushDriver.masterSecret, JPushDriver.appKey, null,
					ClientConfig.getInstance());
			PushPayload payload = buildPushAndroidAlert();
			//PushPayload payload = buildPushIOSAlert();

			PushResult result = jpushClient.sendPush(payload);
			log.info("Got result - " + result);
		} catch (APIConnectionException e) {
			log.error("Connection error, should retry later.  Exception:{} ", e);

		} catch (APIRequestException e) {
			log.error("Should review the error, and fix the request.Exception:{}", e);
		}
	}

}