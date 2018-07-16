package com.alpha.message.common.utils;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Joiner;

public class CollectionToStringUtils {

	private static String DELIMITER = ",";
	private static final Joiner joiner = Joiner.on(DELIMITER);
	
	public static String transform(Set<Integer> sets) {
		
		if (CollectionUtils.isEmpty(sets)) {
			return null;
		}
		return joiner.skipNulls().join(sets);
	}

}
