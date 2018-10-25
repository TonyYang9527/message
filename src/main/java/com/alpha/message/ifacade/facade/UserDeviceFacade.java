package com.alpha.message.ifacade.facade;

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

public interface UserDeviceFacade {

	public UserDeviceResponse enrollUserDevice(UserDeviceRequest request);

	public UpdateDeviceIdResponse updateDeviceId(UpdateDeviceIdRequest request);

	public UpdateRegIdResponse updateRegistrationId(UpdateRegIdRequest request);

	public DisableUserDeviceResponse disableUserDevice(DisableUserDeviceRequest request);

	public EnableUserDeviceResponse enableUserDevice(EnableUserDeviceRequest request);
	
	public UserDeviceResponse  disenrollUserDevice(UserDeviceRequest request);
}
