package com.alpha.message.common.sql;

public enum SQLOperators {
	EqualTo(" = "),
	GreaterThan(" > "),
	NotEqualTo(" <> "),
	GreaterThanOrEqualTo(" >= "),
	LessThan(" < "),
	In(" in "),
	Like(" like "),
	Not_In(" not  in ");

	private String value;

	SQLOperators(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SQLOperators fromValue(String value) {
		for (SQLOperators flag : SQLOperators.values()) {
			if (flag.getValue().equalsIgnoreCase(value))
				return flag;
		}
		return null;
	}

}
