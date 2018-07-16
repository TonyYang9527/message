package com.alpha.message.common.enums;

public enum MsgType {

	// sms
	SMS((byte) 1),
	// email
	EMAIL((byte) 2),
	// push
	PUSH((byte) 3),
	// messageBox
	SITE((byte) 4);

	private byte value;

	MsgType(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}
	
	public static MsgType fromValue(byte value) {
		for (MsgType flag : MsgType.values()) {
			if (flag.getValue() == value)
				return flag;
		}
	 	return null;
	}
}
