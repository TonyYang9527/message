package com.alpha.message.model.enums;

public enum MsgType {

	// SMS
	SMS( 1),
	// EMAIL
	EMAIL(2),
	// push
	PUSH( 3),
	// messageBox
	SITE_MSG(4);

	private int value;

	MsgType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static MsgType fromValue(int value) {
		for (MsgType flag : MsgType.values()) {
			if (flag.getValue() == value) {
				return flag;
			}
		}
		return null;
	}
}
