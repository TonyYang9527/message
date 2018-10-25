package com.alpha.message.dao.impl.device;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.bmo.ibackend.persistence.SQLQuery;
import com.alpha.message.common.enums.DeleteFlag;
import com.alpha.message.common.enums.SQLOperators;
import com.alpha.message.common.utils.SQLWhereBuilder;
import com.alpha.message.dao.entiy.user.UserDevice;
import com.alpha.message.dao.mapper.device.UserDeviceMapper;

@Repository
public class UserDeviceDao implements UserDeviceMapper {

	@Override
	public void insertSelective(UserDevice device) {
		device.saveOrUpdate();
	}

	@Override
	public UserDevice selectByPrimaryKey(Long id) {
		return UserDevice.findById(id);
	}

	@Override
	public List<UserDevice> selectByUserId(Long userId) {
		return UserDevice.where(" user_id = ?  and  deleted = ? ", userId, DeleteFlag.SURVIVOR.getValue()).all();
	}

	@Override
	public UserDevice selectByRegistrationId(String registrationId) {
		return UserDevice
				.where(" registration_id = ? and  deleted = ? ", registrationId, DeleteFlag.SURVIVOR.getValue())
				.first();
	}

	@Override
	public UserDevice updateByPrimaryKey(UserDevice device) {
		device.saveOrUpdate();
		return device;
	}

	@Override
	public boolean checkState(Long id, int deleted) {
		UserDevice device = UserDevice.where(" id =? and deleted=? ", id, deleted).first();
		return device == null ? false : true;
	}

	@Override
	public void changeState(Long id, int deleted, String updatedBy) {
		UserDevice device = this.selectByPrimaryKey(id);
		if (device != null) {
			device.setUpdatedTime(new Date());
			device.setUpdatedBy(StringUtils.isBlank(updatedBy) ? "admin" : updatedBy);
			device.setDeleted(deleted);
			device.setDeletedTime(new Date());
			device.saveOrUpdate();
		}
	}

	@Override
	public UserDevice checkBySelective(String platform, String registrationId, String application) {
		return UserDevice.where(" platform =?  and registration_id =? and  application =? and  deleted = ? ", platform,
				registrationId, application, DeleteFlag.SURVIVOR.getValue()).first();
	}

	@Override
	public List<UserDevice> selectBySelective(Long userId, String application) {
		StringBuffer sql = new StringBuffer();
		sql.append("   SELECT   *   FROM  user_device  t  ");
		SQLWhereBuilder sqlBuilder = SQLWhereBuilder.instance();
		sqlBuilder.and("  t.user_id ", SQLOperators.EqualTo, userId);
		sqlBuilder.and("  t.application  ", SQLOperators.EqualTo, application);
		sqlBuilder.and("  t.deleted  ", SQLOperators.EqualTo, DeleteFlag.SURVIVOR.getValue());
		sql.append(sqlBuilder.toString());
		return SQLQuery.sql(UserDevice.class, sql.toString()).all();
	}

	@Override
	public List<UserDevice> selectBySelective(Set<Long> userIds, String application) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   *   FROM  user_device  t  ");
		SQLWhereBuilder sqlBuilder = SQLWhereBuilder.instance();
		sqlBuilder.and("  t.user_id ", SQLOperators.In, userIds);
		sqlBuilder.and("  t.application  ", SQLOperators.EqualTo, application);
		sqlBuilder.and("  t.deleted  ", SQLOperators.EqualTo, DeleteFlag.SURVIVOR.getValue());
		sql.append(sqlBuilder.toString());
		return SQLQuery.sql(UserDevice.class, sql.toString()).all();
	}

	@Override
	public UserDevice checkUserDeviceBySelective(Long userId, String registrationId) {
		return UserDevice.where(" user_id =?  and registration_id = ? ", userId, registrationId).first();
	}

	@Override
	public boolean changeDeletedByRegistrationId(String registrationId, int deleted, String updatedBy) {
		List<UserDevice> devices = UserDevice.where(" registration_id = ? ", registrationId).all();
		if (CollectionUtils.isNotEmpty(devices)) {
			for (UserDevice device : devices) {
				device.setUpdatedTime(new Date());
				device.setUpdatedBy(StringUtils.isBlank(updatedBy) ? "admin" : updatedBy);
				device.setDeleted(deleted);
				device.setDeletedTime(new Date());
				device.saveOrUpdate();
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public UserDevice changeDeletedBySelective(Long userId, String registrationId, int deleted, String updatedBy) {
		UserDevice device = this.checkUserDeviceBySelective(userId, registrationId);
		if (device != null) {
			device.setUpdatedTime(new Date());
			device.setUpdatedBy(StringUtils.isBlank(updatedBy) ? "admin" : updatedBy);
			device.setDeleted(deleted);
			device.setDeletedTime(new Date());
			device.saveOrUpdate();
		}
		return device;
	}
}
