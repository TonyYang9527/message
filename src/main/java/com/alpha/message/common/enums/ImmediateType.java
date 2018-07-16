package com.alpha.message.common.enums;


public enum ImmediateType {

	IMMEDIATE( 0),
	SCHEDULE(1);

	private int value;

	ImmediateType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ImmediateType fromValue(int value) {
		for (ImmediateType flag : ImmediateType.values()) {
			if (flag.getValue() == value) {
				return flag;
			}
		}
		return null;
	}
}
