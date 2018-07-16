package com.alpha.message.common.enums;


/**
 * Device Type  Enum
 */

public enum DeviceType {

	ANDROID(1), 
	IOS(0);
	
	private DeviceType(int value) {
		this.value = value;
	}

	private int value;

	public int getValue() {
		return value;
	}
}
