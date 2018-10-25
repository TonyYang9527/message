package com.alpha.message.common.enums;

public enum SiteState {

	SEND((byte) 0),

	CREATE((byte) 1);

	private byte value;

	SiteState(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	public static SiteState fromValue(byte value) {
		for (SiteState flag : SiteState.values()) {
			if (flag.getValue() == value) {
				return flag;
			}
		}
		return null;
	}
}
