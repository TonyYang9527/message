package com.alpha.message.dao.mapper.device;

import com.alpha.message.dao.entiy.user.UserChannelSetting;

public interface UserChannelSettingMapper {

	UserChannelSetting selectByPrimaryKey(Long id);

	UserChannelSetting selectByUserId(Long userId);

	void insertSelective(UserChannelSetting setting);

	boolean checkState(Long id, int deleted);

	void changeState(Long id, int deleted, String updatedBy);

	UserChannelSetting updateByPrimaryKey(UserChannelSetting setting);
}
