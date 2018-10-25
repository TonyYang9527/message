package com.alpha.message.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.message.ifacade.facade.UserDeviceFacade;
import com.alpha.message.ifacade.request.push.UserDeviceRequest;
import com.alpha.message.ifacade.response.push.UserDeviceResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("device")
@RestController
@RequestMapping("/device")
public class UserDeviceController {

	@Autowired
	private UserDeviceFacade deviceService;

	@ApiOperation("enroll user device ")
	@PostMapping(value = "/enroll")
	public UserDeviceResponse enroll(@RequestBody UserDeviceRequest request) throws IOException {
		log.info("UserDeviceController  enroll user device  request:{}", request);
		return deviceService.enrollUserDevice(request);
	}
}
