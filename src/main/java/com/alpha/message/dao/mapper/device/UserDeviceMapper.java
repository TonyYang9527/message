package com.alpha.message.dao.mapper.device;

import java.util.List;
import java.util.Set;

import com.alpha.message.dao.entiy.user.UserDevice;

public interface UserDeviceMapper {

	void insertSelective(UserDevice device);

	UserDevice selectByPrimaryKey(Long id);

	List<UserDevice> selectByUserId(Long userId);

	UserDevice selectByRegistrationId(String registrationId);

	UserDevice updateByPrimaryKey(UserDevice device);

	boolean checkState(Long id, int deleted);

	void changeState(Long id, int deleted, String updatedBy);

	UserDevice checkBySelective(String platform, String registrationId, String application);

	List<UserDevice> selectBySelective(Long userId, String application);
	
	List<UserDevice> selectBySelective(Set<Long> userIds, String application);

	UserDevice checkUserDeviceBySelective(Long userId,String registrationId);

	boolean changeDeletedByRegistrationId(String registrationId, int deleted, String updatedBy);
	
	UserDevice changeDeletedBySelective(Long userId,String registrationId, int deleted, String updatedBy);
}
