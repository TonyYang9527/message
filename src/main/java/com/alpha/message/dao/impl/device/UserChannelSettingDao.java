package com.alpha.message.dao.impl.device;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.dao.entiy.user.UserChannelSetting;
import com.alpha.message.dao.entiy.user.UserDevice;
import com.alpha.message.dao.mapper.device.UserChannelSettingMapper;

@Repository
public class UserChannelSettingDao implements UserChannelSettingMapper {

	@Override
	public UserChannelSetting selectByPrimaryKey(Long id) {
		return UserChannelSetting.findById(id);
	}

	@Override
	public void insertSelective(UserChannelSetting setting) {
		setting.saveOrUpdate();
	}

	@Override
	public UserChannelSetting selectByUserId(Long userId) {
		return UserChannelSetting.where(" user_id = ?  and  deleted = ? ", userId, DeleteFlag.SURVIVOR.getValue())
				.first();
	}

	@Override
	public boolean checkState(Long id, int deleted) {
		UserDevice device = UserDevice.where(" id =?  and  deleted = ? ", id, deleted).first();
		return device == null ? false : true;
	}

	@Override
	public void changeState(Long id, int deleted, String updatedBy) {
		UserChannelSetting setting = this.selectByPrimaryKey(id);
		if (setting != null) {
			setting.setDeleted(deleted);
			setting.setDeletedTime(new Date());
			setting.setUpdatedTime(new Date());
			setting.setUpdatedBy(StringUtils.isBlank(updatedBy) ? "admin" : updatedBy);
			setting.saveOrUpdate();
		}

	}

	@Override
	public UserChannelSetting updateByPrimaryKey(UserChannelSetting setting) {
		setting.saveOrUpdate();
		return setting;
	}

}
