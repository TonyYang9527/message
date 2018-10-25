package com.alpha.message.service.wrap.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.dao.entiy.user.UserEmailWhitelist;
import com.alpha.message.dao.mapper.device.UserEmailWhitelistMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserEmailWhitelistService {

	@Autowired
	protected UserEmailWhitelistMapper userEmailmapper;

	public UserEmailWhitelist insertSelective(Long userId, String email) {

		if (StringUtils.isBlank(email)) {
			return null;
		}

		UserEmailWhitelist userEmail = userEmailmapper.isVerifiedEmail(email);
		if (this.isVerifiedEmail(userEmail)) {
			return userEmail;
		}

		userEmail = new UserEmailWhitelist();
		userEmail.setUserId(userId);
		userEmail.setEmail(email);
		userEmail.setCreatedTime(new Date());
		userEmail.setCreatedBy("admin");
		userEmail.setDeleted(DeleteFlag.SURVIVOR.getValue());
		userEmailmapper.insertSelective(userEmail);

		log.info("UserEmailWhitelistService --Sync Verified Email >>>>>>>> userEmail:{}", userEmail);
		return userEmail;
	}

	private boolean isVerifiedEmail(UserEmailWhitelist userEmail) {
		return userEmail != null ? true : false;
	}

	public List<String> filterUnverifiedEmail(List<String> emails) {
		List<String> result = new ArrayList<String>();
		if (CollectionUtils.isEmpty(emails)) {
			return result;
		}
		for (String email : emails) {
			UserEmailWhitelist userEmail = userEmailmapper.isVerifiedEmail(email);
			if (this.isVerifiedEmail(userEmail)) {
				result.add(email);
			} else {
				log.warn("UserEmailWhitelistService --Unverified Email>>>>>>>> userEmail:{}", email);
			}
		}
		return result;
	}

	public String[] filterUnverifiedEmail(String[] emails) {
		if (ArrayUtils.isEmpty(emails)) {
			return null;
		}
		List<String> result = filterUnverifiedEmail(Arrays.asList(emails));
		return CollectionUtils.isEmpty(result) ? null : result.stream().toArray(String[]::new);
	}
}
