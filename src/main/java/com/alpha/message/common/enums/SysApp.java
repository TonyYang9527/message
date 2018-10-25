package com.alpha.message.common.enums;

public enum SysApp {

	Crewc("CREWC"), Mol("MOL"), Mo("MO"), Unknow("UNKNOW");

	private String value;

	SysApp(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static SysApp fromValue(String value) {
		for (SysApp flag : SysApp.values()) {
			if (flag.getValue().equals(value))
				return flag;
		}
		return SysApp.Unknow;
	}

}
