package com.alpha.message.service.driver.push;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alpha.message.common.enums.Platforms;
import com.alpha.message.context.AppKeyProperties;
import com.alpha.message.context.AppKeyProperties.Credential;
import com.alpha.message.context.AppProperties;
import com.alpha.message.context.JPushProperties;
import com.alpha.message.dao.entiy.user.UserDevice;
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
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JPushClientManager {

	@Autowired
	private JPushProperties props;

	@Autowired
	private AppKeyProperties appKeys;

	@Autowired
	private AppProperties app;

	private ClientConfig buildClientConfig() {
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(props.retry);
		/*** Time unit is second default :10s ***/
		config.setConnectionTimeout(props.connectionTimeOut * 1000);
		/*** Time unit is second default:10 days ***/
		config.setTimeToLive(props.timeToLive);
		return config;
	}

	private JPushClient buildJPushClient(String application) {
		Credential credential = appKeys.getAppCredential(application);
		if (credential == null) {
			log.error("Jpush masterSecret && appKey is Empty ... Please check AppKeyProperties file ");
			return null;
		}
		return new JPushClient(credential.getMasterSecret(), credential.getAppKey(), null, this.buildClientConfig());
	}

	private Options buildOptions() {
		log.info("JPushClientManager  <<<<<<Apns>>>>> isProduction :{} ","production".equalsIgnoreCase(app.envName) ? props.isProduction : props.isDev);
		//return Options.newBuilder().setApnsProduction("production".equalsIgnoreCase(app.envName) ? props.isProduction : props.isDev).setTimeToLive(props.timeToLive).build();
		return Options.newBuilder().setApnsProduction("production".equalsIgnoreCase(app.envName) ? props.isProduction : props.isDev).setTimeToLive(props.timeToLive).build();
	}

	public PushPayload buildPushPayload(UserDevice device, String title, String msgContent, String contentType,
			Map<String, String> extras) {

		if (Platforms.Unknow.getValue().equalsIgnoreCase(device.getPlatform())
				|| StringUtils.isBlank(device.getRegistrationId())) {
			return null;
		}

		Builder payLoad = PushPayload.newBuilder();
		payLoad.setAudience(AudienceBuilder.registrationId(device.getRegistrationId()));
		if (Platforms.Android == Platforms.fromValue(device.getPlatform())) {
			payLoad.setPlatform(Platform.android());
			payLoad.setMessage(this.buildMessage(title, msgContent, contentType, extras));
		} else if (Platforms.IOS == Platforms.fromValue(device.getPlatform())) {

			payLoad.setPlatform(Platform.ios());
			payLoad.setNotification(
					this.buildNotification(device.getPlatform(), alertBuilder(title, msgContent), extras));
		} else if (Platforms.WinPhone == Platforms.fromValue(device.getPlatform())) {

			payLoad.setPlatform(Platform.winphone());
			payLoad.setNotification(
					this.buildNotification(device.getPlatform(), alertBuilder(title, msgContent), extras));
		}
		payLoad.setOptions(this.buildOptions());
		return payLoad.build();
	}

	public PushResult sendPush(UserDevice device, PushPayload payload) {
		PushResult result = null;
		try {
			JPushClient jpushClient = this.buildJPushClient(device.getApplication());
			
		
			if (jpushClient != null && payload != null) {
				result = jpushClient.sendPush(payload);
				log.info("******PushPayload****** PushResult:{} ", result);
			} else {
				log.error("******PushPayload Payload or JPushClient is Empty ");
			}
		} catch (APIConnectionException e) {
			log.error("****APIConnectionException***** PushException:{} ", e);
			return result;
		} catch (APIRequestException e) {
			log.error("****APIRequestException***** PushException:{} ", e);
			return result;
		}
		return result;
	}

	public Notification buildNotification(String platform, JsonObject alert, Map<String, String> extras) {
		Notification notification = null;
		if (Platforms.IOS == Platforms.fromValue(platform)) {
			notification = NotificationBuilder.ios(alert, extras, props);
		} else if (Platforms.Android == Platforms.fromValue(platform)) {
			notification = NotificationBuilder.android(alert, extras, props);
		} else if (Platforms.WinPhone == Platforms.fromValue(platform)) {
			notification = NotificationBuilder.winphone(alert, null, extras);
		} else {
			notification = null;
		}
		return notification;
	}

	public Message buildMessage(String title, String msgContent, String contentType, Map<String, String> extras) {
		if (StringUtils.isBlank(msgContent)) {
			return null;
		}
		return Message.newBuilder().setTitle(title).setMsgContent(msgContent).setContentType(contentType)
				.addExtras(extras).build();
	}

	private JsonObject alertBuilder(String title, String body) {
		JsonObject alert = new JsonObject();
		alert.addProperty("title", title);
		alert.addProperty("body", body);
		return alert;
	}
}
