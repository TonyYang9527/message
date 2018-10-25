package com.alpha.message.service.driver.push;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.ChannelType;
import com.alpha.message.common.utils.RegexUtil;
import com.alpha.message.dao.entiy.user.UserDevice;
import com.alpha.message.service.driver.BasicDriver;
import com.alpha.message.service.driver.entity.request.ChannelReq;
import com.alpha.message.service.driver.entity.request.PushRequest;
import com.alpha.message.service.driver.entity.response.ChannelResp;
import com.alpha.message.service.wrap.user.UserDeviceService;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushDriver implements BasicDriver {
	@Autowired
	private JPushClientManager pushManager;
	@Autowired
	private UserDeviceService userDeviceService;

	@Override
	public ChannelType getType() {
		return ChannelType.PUSH;
	}

	@Override
	public ChannelResp send(ChannelReq req) {
		PushRequest pushReq = (PushRequest) req;
		Boolean isSuccess = false;
		try {
			String title = pushReq.getTitle();
			String msgContent = pushReq.getContent();
			String contentType = pushReq.getContentType();
			Map<String, String> extras = pushReq.getExtras();
			String application = pushReq.getApplication();

			Set<Long> receiverSet = buildReceivers(pushReq.getReceivers());
			List<UserDevice> userDevices = userDeviceService.selectBySelective(receiverSet, application);
			if (CollectionUtils.isEmpty(userDevices)) {
				isSuccess = false;
				log.info("PushDriver  Send Push Message userDevices isEmpty ..... ");
				return new ChannelResp(pushReq.getId(), ChannelType.PUSH, isSuccess, "PushSuccess", null);
			}
			for (UserDevice device : userDevices) {
				PushPayload pushPayload = pushManager.buildPushPayload(device, title, msgContent, contentType, extras);
				if (pushPayload != null) {
					PushResult pushResult = pushManager.sendPush(device, pushPayload);
					log.info("**********PushDriver -- PushRequest :{},PushResult:{} ", pushReq, pushResult);
				}

			}
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			log.error("PushDriver  Send Push Message  Failed  Exception :{}", e);
			return new ChannelResp(pushReq.getId(), ChannelType.PUSH, isSuccess, "PushFailed", null);
		}
		String code = isSuccess ? "PushSuccess" : "PushFailed";
		return new ChannelResp(pushReq.getId(), ChannelType.PUSH, isSuccess, code, null);
	}

	private Set<Long> buildReceivers(String[] receiver) {
		Set<Long> set = new HashSet<Long>();

		if (ArrayUtils.isEmpty(receiver)) {
			return set;
		}
		for (int i = 0; i <= receiver.length - 1; i++) {
			if (!RegexUtil.checkNumber(receiver[i]))
				continue;
			else
				set.add(Long.valueOf(receiver[i]));
		}
		return set;
	}

}
