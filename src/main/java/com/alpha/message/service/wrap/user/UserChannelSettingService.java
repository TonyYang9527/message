package com.alpha.message.service.wrap.user;

import java.util.Date;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.StateFlag;
import com.alpha.message.dao.entiy.user.UserChannelSetting;
import com.alpha.message.dao.mapper.device.UserChannelSettingMapper;
import com.alpha.message.model.vo.UserChannelVo;

@Service
public class UserChannelSettingService {

	@Autowired
	protected UserChannelSettingMapper userChannelMapper;

	public UserChannelSetting insertSelective(Long userId, UserChannelVo channel, String createdBy) {
		if (userId == null || channel == null) {
			return null;
		}
		UserChannelSetting userSetting = userChannelMapper.selectByUserId(userId);
		if (userSetting != null) {
			return userSetting;
		}
		UserChannelSetting setting = new UserChannelSetting();
		setting.setUserId(userId);
		setting.setSms(channel.getSms() == null ? Boolean.FALSE : channel.getSms());
		setting.setEmail(channel.getEmail() == null ? Boolean.FALSE : channel.getEmail());
		setting.setPush(channel.getPush() == null ? Boolean.FALSE : channel.getPush());
		setting.setInbox(channel.getInbox() == null ? Boolean.FALSE : channel.getInbox());
		setting.setCreatedTime(new Date());
		setting.setCreatedBy(StringUtils.isBlank(createdBy) ? "admin" : createdBy);
		userChannelMapper.insertSelective(setting);

		return setting;

	}

	public boolean updateUserChannelSetting(Long userId, UserChannelVo channel, String updateBy) {
		if (userId == null || channel == null) {
			return false;
		}
		UserChannelSetting userSetting = userChannelMapper.selectByUserId(userId);
		if (userSetting == null) {
			return false;
		}
		
		if (channel.getSms() != null) {
			userSetting.setSms(channel.getSms());
		}
		if (channel.getEmail() != null) {
			userSetting.setSms(channel.getEmail());
		}
		if (channel.getPush() != null) {
			userSetting.setPush(channel.getPush());
		}
		if (channel.getInbox() != null) {
			userSetting.setInbox(channel.getInbox());
		}
		userSetting.setUpdatedTime(new Date());
		userSetting.setUpdatedBy(StringUtils.isBlank(updateBy) ? "admin" : updateBy);
		userChannelMapper.updateByPrimaryKey(userSetting);
		return true;

	}

	public UserChannelSetting selectByPrimaryKey(Long id) {
		if (id == null) {
			return null;
		}
		return userChannelMapper.selectByPrimaryKey(id);
	}

	public UserChannelSetting selectByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		return userChannelMapper.selectByUserId(userId);
	}

	public boolean disableUserChannelSetting(Set<Long> ids, String operator) {
		if (CollectionUtils.isEmpty(ids)) {
			return false;
		}
		for (long id : ids) {
			if (userChannelMapper.checkState(id, StateFlag.ENABLE.getValue())) {
				userChannelMapper.changeState(id, StateFlag.DISABLE.getValue(), operator);
			}
		}
		return true;
	}

	public boolean enableUserChannelSetting(Set<Long> ids, String operator) {
		if (CollectionUtils.isEmpty(ids)) {
			return false;
		}
		for (long id : ids) {
			if (userChannelMapper.checkState(id, StateFlag.DISABLE.getValue())) {
				userChannelMapper.changeState(id, StateFlag.ENABLE.getValue(), operator);
			}
		}
		return true;
	}

}
