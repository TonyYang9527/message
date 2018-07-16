package com.alpha.message.common.enums;

public enum SmsChannel {
	SMS_SING((byte) 0, "Singtel"), SMS_STAR((byte) 1, "Starhub");

	private byte value;
	private String description;

	private SmsChannel(byte value, String description) {
		this.value = value;
		this.description = description;
	}

	public byte getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static SmsChannel fromValue(byte value) {
		for (SmsChannel flag : SmsChannel.values()) {
			if (flag.getValue() == value) {
				return flag;
			}
		}
		return null;
	}

}
