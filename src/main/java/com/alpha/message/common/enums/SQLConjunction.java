package com.alpha.message.common.enums;
public enum SQLConjunction {

	WHERE(" WHERE "),
	AND(" AND  "),
	OR(" OR ");

	private String value;

	SQLConjunction(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SQLConjunction fromValue(String value) {
		for (SQLConjunction flag : SQLConjunction.values()) {
			if (flag.getValue().equalsIgnoreCase(value))
				return flag;
		}
		return null;
	}
}
