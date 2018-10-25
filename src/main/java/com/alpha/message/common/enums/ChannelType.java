package com.alpha.message.common.enums;

public enum ChannelType {
	SMS(1), EMAIL(2), PUSH(3), SITE(4);

	private int value;

	ChannelType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ChannelType fromValue(int value) {
		for (ChannelType flag : ChannelType.values()) {
			if (flag.getValue() == value)
				return flag;
		}
		return null;
	}

}