package com.alpha.message.common.enums;

public enum MsgPriority {

	HIGH( 20),
	NORMAL(40),
	LOW( 60);

	private int value;

	MsgPriority(int value) {
		this.value = value;
	}

	public int  getValue() {
		return value;
	}

	public static MsgPriority fromValue(int value) {
		for (MsgPriority flag : MsgPriority.values()) {
			if (flag.getValue() == value) {
				return flag;
			}
		}
		return null;
	}

}
