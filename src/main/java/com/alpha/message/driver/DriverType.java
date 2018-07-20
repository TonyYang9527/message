package com.alpha.message.driver;

public enum DriverType {

	EMAIL("email"),
	SMS("sms");

	private String type;

	DriverType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static DriverType fromValue(String type) {
		for (DriverType dtype : DriverType.values()) {
			if (dtype.getType().equalsIgnoreCase(type)) {
				return dtype;
			}
		}
		return null;
	}
}