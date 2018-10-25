package com.alpha.message.service.wrap.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.utils.Base64Utils;
import com.alpha.message.common.utils.JsonUtil;
import com.alpha.message.common.utils.MessageAssembleUtil;
import com.alpha.message.dao.entiy.push.PushMessage;
import com.alpha.message.dao.entiy.push.PushMessageProperty;
import com.alpha.message.dao.entiy.push.PushMessageTemplate;
import com.alpha.message.service.driver.entity.request.PushRequest;
import com.alpha.message.service.driver.entity.response.ChannelResp;
import com.alpha.message.service.wrap.channel.ChannelServices;
import com.alpha.message.service.wrap.push.PushMessagePropertyService;
import com.alpha.message.service.wrap.push.PushMessageService;
import com.alpha.message.service.wrap.push.PushMessageTemplateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushTaskService {

	private static final String SPILT_REGEX = "\\|";
	@Autowired
	private PushMessagePropertyService pushMessagePropertyService;
	@Autowired
	private PushMessageService pushMessageService;
	@Autowired
	private PushMessageTemplateService pushMessageTemplateService;
	@Autowired
	private ChannelServices channelServices;

	public ChannelResp sendTask(Long messageId) {
		ChannelResp channelResp = null;
		try {
			// 1. get push message
			PushMessage pushMessage = getPushMessage(messageId);
			// 2. get message template
			PushMessageTemplate template = getPushMessageTemplate(pushMessage);
			// 3. get Email message Key/Value
			Map<String, Object> props = getProps(pushMessage);
			// 4. build full messages & send gateway
			Long id = pushMessage.getId();
			String content = MessageAssembleUtil.mustacheContent(template.getName(), template.getContent(), props);

			String title = null;
			String receivers = pushMessage.getReceivers();
			String application = pushMessage.getApplication();
			String contentType = pushMessage.getContentType();
			
			String extrasJson = Base64Utils.decode(pushMessage.getExtras());
			Map<String, String> extras = JsonUtil.jsonToMap(extrasJson);
			if (!props.isEmpty() && props.containsKey("title")) {
				title = props.get("title").toString();
			} else {
				if (StringUtils.isNotBlank(template.getTitle())) {
					title = MessageAssembleUtil.mustacheContent(template.getName(), template.getTitle(), props);
				} else {
					title = null;
				}
			}
			channelResp = sendPushToChannel(id, title, content, extras, receivers, application, contentType);
			// 5. Update Email Message State
			savePushSendResult(channelResp);
			log.info("PushTask   channelResp :{} ", channelResp);
		} catch (IOException e) {

		}
		return channelResp;
	}

	/**
	 * build Channel Request & Send Request
	 * 
	 * @param id
	 * @param title
	 * @param content
	 * @param extras
	 * @param receiver
	 * @param application
	 * @return the channel resp
	 */
	private ChannelResp sendPushToChannel(Long id, String title, String content, Map<String, String> extras,
			String receiver, String application, String contentType) {
		String[] receiverArrs = null;
		if (receiver != null && receiver.length() > 0) {
			receiverArrs = receiver.split(SPILT_REGEX);
		}
		PushRequest req = new PushRequest(id, title, content, extras, receiverArrs, application, contentType);
		ChannelResp resp = channelServices.request(req);
		log.info(" Channel SendPush  Message Request :{} ,Response :{}", req, resp);
		return resp;
	}

	/**
	 * Get PushMessage.
	 * 
	 * @param queuePriorityVo
	 * @return pushMessage
	 */
	private PushMessage getPushMessage(Long messageId) {
		return pushMessageService.getPushMessageById(messageId);
	}

	/**
	 * Get PushMessageTemplate.
	 * 
	 * @param PushMessage
	 * @return PushMessageTemplate
	 */
	private PushMessageTemplate getPushMessageTemplate(PushMessage pushMessage) {
		return pushMessageTemplateService.getMsgTemplate(pushMessage.getPushMessageTemplateId());
	}

	/**
	 * Get push param Key/Value .
	 * 
	 * @param PushMessage
	 * @return the props
	 * @throws UnsupportedEncodingException
	 */
	private Map<String, Object> getProps(PushMessage pushMessage) {
		PushMessageProperty property = pushMessagePropertyService.selectByPushMessageId(pushMessage.getId());
		if (property == null || StringUtils.isBlank(property.getPropValue())) {
			return new HashMap<String, Object>();
		}
		String propsJson = Base64Utils.decode(property.getPropValue());
		return JsonUtil.map(propsJson);
	}

	/**
	 * Update Push message State
	 * 
	 * @param savePushSendResult
	 */
	private void savePushSendResult(ChannelResp channelResp) {
		pushMessageService.updatePushSendResult(channelResp.getId(), channelResp.isSuccess());
	}
}
