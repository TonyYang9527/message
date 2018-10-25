package com.alpha.message.service.driver.sms;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alpha.message.common.enums.ChannelType;
import com.alpha.message.common.enums.SmsChannel;
import com.alpha.message.common.utils.DateUtils;
import com.alpha.message.service.driver.BasicDriver;
import com.alpha.message.service.driver.entity.request.ChannelReq;
import com.alpha.message.service.driver.entity.request.SmsChannelReq;
import com.alpha.message.service.driver.entity.response.ChannelResp;
import com.google.common.collect.Maps;

@Slf4j
@Component
public class SmsDriver implements BasicDriver {

	protected static final int DEFAULT_PRIORITY = 5;
	protected int threshhold = 5000;
	private static final String START_TAG = "<error>";
	private static final String END_TAG = "</error>";
	private static final String SIGNATURE = "Brightoil Marine Online";

	@Value("${sms.emay.em.no}")
	private String serialNo;

	@Value("${sms.emay.em.password}")
	private String serialPassword;

	@Value("${sms.emay.em.key}")
	private String key;

	@Value("${sms.emay.em.send.url}")
	protected String sendUrl;

	@Value("${sms.emay.em.sendtime.url}")
	protected String sendTimeUrl;

	@Value("${sms.emay.em.register.url}")
	protected String registerUrl;

	@Value("${sms.emay.em.receive.url}")
	protected String receiveUrl;

	@Value("${sms.emay.em.queryBalance.url}")
	protected String queryBalanceUrl;

	@Override
	public ChannelType getType() {
		return ChannelType.SMS;
	}

	@Override
	public ChannelResp send(ChannelReq req) {
		Object[] result = null;
		SmsChannelReq smsReq = (SmsChannelReq) req;
		Long id = smsReq.getId();
		String[] mobiles = smsReq.getTarget();
		String content = smsReq.getContent();
		SmsChannel smsChannel = smsReq.getSmsChannel();
		switch (smsChannel) {
		case SMS_SING:
			result = this.send(id, mobiles, SIGNATURE + content, null);
			break;
		case SMS_STAR:
			break;
		default:
			break;
		}
		Boolean isSuccess = (Boolean) result[0];
		String code = (String) result[1];
		return new ChannelResp(id, ChannelType.SMS, isSuccess, code, null);
	}

	/**
	 * SmsProxy Send SMS Message
	 * 
	 * @param id
	 * @param mobiles
	 * @param content
	 * @param scheduleUtcTime
	 * @return Object[2], obj[0] : success or failed flag, obj[1]: return result
	 */
	protected Object[] send(long id, String[] mobiles, String content, Long scheduleUtcTime) {
		long start = System.currentTimeMillis();
		SMSAgentParams ep = initSMSAgentParams();
		String result = null;
		Map<String, String> params = initBasicSMSAgentParams();
		params.put("phone", StringUtils.join(mobiles, ","));
		params.put("message", content);
		if (scheduleUtcTime != null && scheduleUtcTime > System.currentTimeMillis()) {
			params.put("sendtime", DateUtils.format(new Date(scheduleUtcTime), DateUtils.DATE_PATTERN.YYYYMMDDHHMMSS));
		}
		params.put("seqid", String.valueOf(id));
		params.put("smspriority", String.valueOf(DEFAULT_PRIORITY));

		if (scheduleUtcTime == null)
			result = post(ep.getSendUrl(), params);
		else
			result = post(ep.getSendTimeUrl(), params);

		result = result.substring(result.indexOf(START_TAG) + START_TAG.length(), result.indexOf(END_TAG));
		boolean checkResult = checkResult(result, id, System.currentTimeMillis() - start);
		return new Object[] { checkResult, result };
	}

	/**
	 * Post SMS Message to SMS Agent
	 * 
	 * @param url
	 * @param params
	 * @return the string
	 */
	protected String post(String url, Map<String, String> params) {
		try {
			HttpPost httppost = new HttpPost(url);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : params.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// set Charse
			httppost.setEntity(new UrlEncodedFormEntity(list, StandardCharsets.UTF_8));
			HttpResponse response = HttpClientBuilder.create().build().execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity).trim();
					return result;
				}
			}
		} catch (Exception e) {
			log.error("Post  SMS Messages  to  SMSAgent", params, e);
		}
		return null;
	}

	/**
	 * init SMS Agent Params
	 * 
	 * @return the emay params
	 */
	protected SMSAgentParams initSMSAgentParams() {
		SMSAgentParams ep = new SMSAgentParams();
		ep.setKey(key);
		ep.setSerialNo(serialNo);
		ep.setSerialPassword(serialPassword);
		ep.setSendUrl(sendUrl);
		ep.setSendTimeUrl(sendTimeUrl);
		ep.setRegisterUrl(registerUrl);
		ep.setReceiveUrl(receiveUrl);
		ep.setQueryBalanceUrl(queryBalanceUrl);
		ep.setRegisterUrl(registerUrl);
		ep.setReceiveUrl(receiveUrl);
		ep.setQueryBalanceUrl(queryBalanceUrl);
		return ep;
	}

	/**
	 * init Basic SMS Agent Params
	 */
	protected Map<String, String> initBasicSMSAgentParams() {
		Map<String, String> basicParams = Maps.newHashMap();
		SMSAgentParams ep = initSMSAgentParams();
		basicParams.put("cdkey", ep.getSerialNo());
		basicParams.put("password", ep.getKey());
		return basicParams;
	}

	/**
	 * Check Send SMS Result.
	 * 
	 * @param result
	 * @param id
	 * @param time
	 * @return true, if successful
	 */
	protected boolean checkResult(String result, long id, long time) {
		boolean isSuccess = false;
		if ("0".equals(result)) {
			log.info("SmsProxy CheckResult  Send SMS  Success   costTime/seconds :{} ,  id :{} , resultCode:{}  ",
					(time / 1000), id, result);
			isSuccess = true;
		} else if ("17".equals(result)) {
			log.error(
					"SmsProxy CheckResult  Send SMS  Failed , Reason  :  Account   DisAvailable , id :{} , resultCode:{} ",
					id, result);
		} else if ("101".equals(result)) {
			log.error(
					"SmsProxy  CheckResult Send SMS  Failed , Reason :  Client  network  error .  id :{} , resultCode:{} ",
					id, result);
		} else if ("305".equals(result)) {
			log.error(
					"SmsProxy  CheckResult Send SMS  Failed , Reason :   Server return   error . id :{} , resultCode:{} ",
					id, result);
		} else if ("307".equals(result)) {
			log.error(
					"SmsProxy  CheckResult Send SMS  Failed , Reason :  Moblie number is invaild.  id :{} , resultCode:{} ",
					id, result);
		} else if ("303".equals(result)) {
			log.error(
					"SmsProxy  CheckResult Send SMS  Failed , Reason :  Client network error  sent time out .  id :{} , resultCode:{} ",
					id, result);
		} else if ("-1".equals(result)) {
			log.error(
					"SmsProxy CheckResult  Send SMS  Failed , Reason :   SMS content too long . id :{} , resultCode:{}",
					id, result);
		} else {
			log.error("SmsProxy  CheckResult  Send SMS  Failed , Reason :   Unknown error . id :{} , resultCode:{}", id,
					result);
		}
		if (time > threshhold) {
			// TimeOut Warning
			log.error(
					"SmsProxy  CheckResult  Send SMS  Failed , Reason : Send TimeOut .  Cost time :{} ,  threshhold: {}    id :{} , resultCode:{} ",
					time, threshhold, id, result);
		}
		return isSuccess;
	}
}
