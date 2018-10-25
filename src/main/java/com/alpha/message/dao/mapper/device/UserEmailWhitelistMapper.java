package com.alpha.message.dao.mapper.device;

import com.alpha.message.dao.entiy.user.UserEmailWhitelist;

public interface UserEmailWhitelistMapper {

	void insertSelective(UserEmailWhitelist whiteList);

	UserEmailWhitelist isVerifiedEmail(String email);
	
}
