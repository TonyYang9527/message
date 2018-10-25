package com.alpha.message.common.enums;
public enum StateFlag {

	ENABLE(0),
	DISABLE(1);

	private int value;

	StateFlag(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static StateFlag fromValue(int value) {
		for (StateFlag flag : StateFlag.values()) {
			if (flag.getValue() == value) {
				return flag;
			}
		}
		return null;
	}
}
