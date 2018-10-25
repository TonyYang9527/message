package com.alpha.message.common.enums;

public enum Platforms {

	Android("ANDROID"), IOS("IOS"), WinPhone("WINPHONE"),Web("WEB"), Unknow("UNKNOW");

	private String value;

	Platforms(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static Platforms fromValue(String value) {
		for (Platforms flag : Platforms.values()) {
			if (flag.getValue().equals(value))
				return flag;
		}
		return Platforms.Unknow;
	}

}
