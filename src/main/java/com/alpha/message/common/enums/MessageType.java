package com.alpha.message.common.enums;

public enum MessageType {

	Default(0);
	
	private int value;

	MessageType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static MessageType fromValue(int value) {
		for (MessageType flag : MessageType.values()) {
			if (flag.getValue() == value)
				return flag;
		}
		return null;
	}

}
