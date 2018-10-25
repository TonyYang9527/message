package com.alpha.message.dao.impl.device;

import org.springframework.stereotype.Repository;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.dao.entiy.user.UserEmailWhitelist;
import com.alpha.message.dao.mapper.device.UserEmailWhitelistMapper;

@Repository
public class UserEmailWhitelistDao implements UserEmailWhitelistMapper {

	@Override
	public void insertSelective(UserEmailWhitelist whiteList) {
		whiteList.saveOrUpdate();
	}

	@Override
	public UserEmailWhitelist isVerifiedEmail(String email) {
		return UserEmailWhitelist.where(" email = ?  and  deleted = ? ", email, DeleteFlag.SURVIVOR.getValue()).first();
	}

}
