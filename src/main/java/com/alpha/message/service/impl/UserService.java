package com.alpha.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.message.dao.entiy.user.UserDevice;
import com.alpha.message.ifacade.facade.UserDeviceFacade;
import com.alpha.message.ifacade.request.push.UserDeviceRequest;
import com.alpha.message.ifacade.request.push.DisableUserDeviceRequest;
import com.alpha.message.ifacade.request.push.EnableUserDeviceRequest;
import com.alpha.message.ifacade.request.push.UpdateDeviceIdRequest;
import com.alpha.message.ifacade.request.push.UpdateRegIdRequest;
import com.alpha.message.ifacade.response.push.UserDeviceResponse;
import com.alpha.message.ifacade.response.push.DisableUserDeviceResponse;
import com.alpha.message.ifacade.response.push.EnableUserDeviceResponse;
import com.alpha.message.ifacade.response.push.UpdateDeviceIdResponse;
import com.alpha.message.ifacade.response.push.UpdateRegIdResponse;
import com.alpha.message.service.wrap.user.UserDeviceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDeviceFacade {

	@Autowired
	private UserDeviceService userDeviceService;

	@Override
	public UserDeviceResponse enrollUserDevice(UserDeviceRequest request) {
		UserDeviceResponse resp = new UserDeviceResponse();
		if (request == null) {
			resp.setRetMsg("Bad Request.");
			resp.setRetCode(HttpStatus.BAD_REQUEST.value());
			return resp;
		}
		UserDevice device = userDeviceService.insertSelective(request.getUserId(), request.getDeviceId(),
				request.getPlatform(), request.getRegistrationId(), request.getApplication(), request.getCreatedBy());
		if (device == null) {
			resp.setRetMsg("Create User Device Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setId(device.getId());
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Create User Device Success");
		log.info(" Create User Device Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public UpdateDeviceIdResponse updateDeviceId(UpdateDeviceIdRequest request) {

		UpdateDeviceIdResponse resp = new UpdateDeviceIdResponse();
		if (request == null) {
			resp.setRetMsg("Bad Request.");
			resp.setRetCode(HttpStatus.BAD_REQUEST.value());
			return resp;
		}
		boolean result = userDeviceService.updateDeviceId(request.getId(), request.getDeviceId(),
				request.getUpdateBy());
		if (!result) {
			resp.setRetMsg("Update User Device DeviceId Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Update User Device DeviceId Success");
		log.info(" Update User Device DeviceId Success.Request:{}, Response:{} ", request, resp);
		return resp;

	}

	@Override
	public UpdateRegIdResponse updateRegistrationId(UpdateRegIdRequest request) {
		UpdateRegIdResponse resp = new UpdateRegIdResponse();
		if (request == null) {
			resp.setRetMsg("Bad Request.");
			resp.setRetCode(HttpStatus.BAD_REQUEST.value());
			return resp;
		}
		boolean result = userDeviceService.updateRegistrationId(request.getId(), request.getRegistrationId(),
				request.getUpdateBy());
		if (!result) {
			resp.setRetMsg("Update User Device RegistrationId Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Update User Device RegistrationId Success");
		log.info(" Update User Device RegistrationId Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public DisableUserDeviceResponse disableUserDevice(DisableUserDeviceRequest request) {
		DisableUserDeviceResponse resp = new DisableUserDeviceResponse();
		if (request == null) {
			resp.setRetMsg("Bad Request.");
			resp.setRetCode(HttpStatus.BAD_REQUEST.value());
			return resp;
		}
		boolean result = userDeviceService.disableUserDevice(request.getIds(), request.getUpdatedBy());
		if (!result) {
			resp.setRetMsg("Disable User Device Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Disable User Device  Success");
		log.info(" Disable User Device Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public EnableUserDeviceResponse enableUserDevice(EnableUserDeviceRequest request) {
		EnableUserDeviceResponse resp = new EnableUserDeviceResponse();
		if (request == null) {
			resp.setRetMsg("Bad Request.");
			resp.setRetCode(HttpStatus.BAD_REQUEST.value());
			return resp;
		}
		boolean result = userDeviceService.enableUserDevice(request.getIds(), request.getUpdatedBy());
		if (!result) {
			resp.setRetMsg("Enable User Device Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" Enable User Device  Success");
		log.info(" Enable User Device  Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

	@Override
	public UserDeviceResponse disenrollUserDevice(UserDeviceRequest request) {
		UserDeviceResponse resp = new UserDeviceResponse();
		if (request == null) {
			resp.setRetMsg("Bad Request.");
			resp.setRetCode(HttpStatus.BAD_REQUEST.value());
			return resp;
		}
		UserDevice device = userDeviceService.disenrollUserDevice(request.getUserId(), request.getDeviceId(),
				request.getPlatform(), request.getRegistrationId(), request.getApplication(), request.getCreatedBy());
		if (device == null) {
			resp.setRetMsg("disenroll User Device Failed.");
			resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resp;
		}
		resp.setId(device.getId());
		resp.setRetCode(HttpStatus.OK.value());
		resp.setRetMsg(" disenroll  User Device Success");
		log.info(" disenroll User Device Success.Request:{}, Response:{} ", request, resp);
		return resp;
	}

}
