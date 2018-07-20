package com.alpha.message.common.sql;

public enum Symbol {
	Delimiter(","),
	Vertical("|");
	private String value;

	Symbol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Symbol fromValue(String value) {
		for (Symbol flag : Symbol.values()) {
			if (flag.getValue().equalsIgnoreCase(value)) {
				return flag;
			}
		}
		return null;
	}

}
