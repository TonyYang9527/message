package com.alpha.message.common.enums;

public enum DeleteFlag {
	SURVIVOR(0),

	VICTIM(1);

	private int value;

	DeleteFlag(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static DeleteFlag fromValue(int value) {
		for (DeleteFlag flag : DeleteFlag.values()) {
			if (flag.getValue() == value) {
				return flag;
			}
		}
		return null;
	}
}
