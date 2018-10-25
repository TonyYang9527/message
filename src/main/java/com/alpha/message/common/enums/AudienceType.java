package com.alpha.message.common.enums;

public enum AudienceType {

	// 0:registrationId,1:alias,2:tag,3:all 4:multi
	REGID(0), ALIAS(1), TAG(2), ALL(3), MULTI(4);

	private int value;

	AudienceType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static AudienceType fromValue(int value) {
		for (AudienceType flag : AudienceType.values()) {
			if (flag.getValue() == value)
				return flag;
		}
		return null;
	}

}
