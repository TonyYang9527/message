package com.alpha.message.service.wrap.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.StateFlag;
import com.alpha.message.dao.entiy.user.UserDevice;
import com.alpha.message.dao.mapper.device.UserDeviceMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDeviceService {

	@Autowired
	protected UserDeviceMapper userDeviceMapper;

	public UserDevice insertSelective(Long userId, String deviceId, String platform, String registrationId,
			String application, String createdBy) {

		if (userId == null || StringUtils.isBlank(registrationId) || StringUtils.isBlank(platform)
				|| StringUtils.isBlank(application)) {
			return null;
		}

		UserDevice device = userDeviceMapper.checkUserDeviceBySelective(userId, registrationId);
		if (device == null) {
			device = new UserDevice();
			device.setUserId(userId == null ? null : userId);
			device.setDeviceId(StringUtils.isBlank(deviceId) ? null : deviceId);
			device.setPlatform(platform);
			device.setRegistrationId(registrationId);
			device.setCreatedTime(new Date());
			device.setApplication(application);
			device.setCreatedBy(StringUtils.isBlank(createdBy) ? "admin" : createdBy);
			// disable other registrationId
			disableUserDeviceByRegistrationId(registrationId, createdBy);
			userDeviceMapper.insertSelective(device);
		} else {
			// disable other registrationId
			disableUserDeviceByRegistrationId(registrationId, createdBy);
			// enable userId && registrationId
			device = enableUserDeviceBySelective(userId, registrationId, createdBy);
		}
		log.info("UserDeviceService -- Binding User Device  >>>>>>>>. UserDevice:{}", device);
		return device;
	}

	public UserDevice selectByPrimaryKey(Long id) {
		if (id == null) {
			return null;
		}
		return userDeviceMapper.selectByPrimaryKey(id);

	}

	public List<UserDevice> selectByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		return userDeviceMapper.selectByUserId(userId);
	}

	public UserDevice selectByRegistrationId(String registrationId) {
		if (StringUtils.isBlank(registrationId)) {
			return null;
		}
		return userDeviceMapper.selectByRegistrationId(registrationId);
	}

	public boolean updateDeviceId(Long id, String deviceId, String updateBy) {
		if (id == null || StringUtils.isBlank(deviceId)) {
			return false;
		}
		UserDevice device = new UserDevice();
		device.setId(id);
		device.setDeviceId(deviceId);
		device.setUpdatedBy(StringUtils.isBlank(updateBy) ? "admin" : updateBy);
		device.setUpdatedTime(new Date());
		userDeviceMapper.updateByPrimaryKey(device);
		return true;

	}

	public boolean updateRegistrationId(Long id, String registrationId, String updateBy) {
		if (id == null || StringUtils.isBlank(registrationId)) {
			return false;
		}
		UserDevice device = new UserDevice();
		device.setId(id);
		device.setRegistrationId(registrationId);
		device.setUpdatedBy(StringUtils.isBlank(updateBy) ? "admin" : updateBy);
		device.setUpdatedTime(new Date());
		userDeviceMapper.updateByPrimaryKey(device);
		return true;
	}

	public boolean disableUserDevice(Set<Long> ids, String operator) {
		if (CollectionUtils.isEmpty(ids)) {
			return false;
		}
		for (long id : ids) {
			if (userDeviceMapper.checkState(id, StateFlag.ENABLE.getValue())) {
				userDeviceMapper.changeState(id, StateFlag.DISABLE.getValue(), operator);
			}
		}
		return true;
	}

	public boolean enableUserDevice(Set<Long> ids, String operator) {
		if (CollectionUtils.isEmpty(ids)) {
			return false;
		}
		for (long id : ids) {
			if (userDeviceMapper.checkState(id, StateFlag.DISABLE.getValue())) {
				userDeviceMapper.changeState(id, StateFlag.ENABLE.getValue(), operator);
			}
		}
		return true;
	}

	public UserDevice bindUserDevice(Long userId, String registrationId) {
		if (userId == null || StringUtils.isBlank(registrationId)) {
			return null;
		}

		UserDevice device = userDeviceMapper.selectByRegistrationId(registrationId);
		if (device == null) {
			return null;
		}
		device.setUserId(userId);
		device.setUpdatedTime(new Date());
		device.setUpdatedBy(String.valueOf(userId));
		return userDeviceMapper.updateByPrimaryKey(device);
	}

	public UserDevice checkBySelective(String platform, String registrationId, String application) {
		return userDeviceMapper.checkBySelective(platform, registrationId, application);
	}

	public List<UserDevice> selectBySelective(Long userId, String application) {
		List<UserDevice> result = new ArrayList<UserDevice>();
		if (userId == null && StringUtils.isBlank(application)) {
			return result;
		}
		return userDeviceMapper.selectBySelective(userId, application);

	}

	public List<UserDevice> selectBySelective(Set<Long> userIds, String application) {
		List<UserDevice> result = new ArrayList<UserDevice>();
		if (CollectionUtils.isEmpty(userIds) && StringUtils.isBlank(application)) {
			return result;
		}
		return userDeviceMapper.selectBySelective(userIds, application);

	}

	public UserDevice checkUserDeviceBySelective(Long userId, String registrationId) {
		if (userId == null || StringUtils.isBlank(registrationId)) {
			return null;
		}
		return userDeviceMapper.checkUserDeviceBySelective(userId, registrationId);
	}

	public boolean disableUserDeviceByRegistrationId(String registrationId, String operator) {
		if (StringUtils.isBlank(registrationId)) {
			return false;
		}
		return userDeviceMapper.changeDeletedByRegistrationId(registrationId, DeleteFlag.VICTIM.getValue(), operator);
	}

	public UserDevice enableUserDeviceBySelective(Long userId, String registrationId, String operator) {
		if (userId == null || StringUtils.isBlank(registrationId)) {
			return null;
		}
		return userDeviceMapper.changeDeletedBySelective(userId, registrationId, DeleteFlag.SURVIVOR.getValue(),
				operator);
	}
	
	public UserDevice disableUserDeviceBySelective(Long userId, String registrationId, String operator) {
		if (userId == null || StringUtils.isBlank(registrationId)) {
			return null;
		}
		return userDeviceMapper.changeDeletedBySelective(userId, registrationId, DeleteFlag.VICTIM.getValue(),
				operator);
	}

	public UserDevice disenrollUserDevice(Long userId, String deviceId, String platform, String registrationId,
			String application, String createdBy) {
		if (userId == null || StringUtils.isBlank(registrationId)) {
			return null;
		}
		UserDevice device = userDeviceMapper.checkUserDeviceBySelective(userId, registrationId);
		if (device != null) {
			device = disableUserDeviceBySelective(userId, registrationId, createdBy);
		}
		log.info("UserDeviceService -- disenroll User Device  >>>>>>>>. UserDevice:{}", device);
		return device;
	}
}
