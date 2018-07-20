package com.alpha.message.common.utils;

import java.util.Collection;

import com.alpha.message.common.sql.Symbol;
import com.google.common.base.Joiner;

public class SQLUtils {
	private static final Joiner joiner = Joiner.on(Symbol.Delimiter.getValue());

	public static   String inJoin(Collection<?> values) {
		return joiner.skipNulls().join(values);
	}
}
