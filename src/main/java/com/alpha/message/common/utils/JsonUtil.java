package com.alpha.message.common.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class JsonUtil {

	public static Map<String, String> jsonToMap(String json) {
		return JSON.parseObject(json, new TypeReference<Map<String, String>>() {
		});
	}

	public static Map<String, Object> map(String json) {
		return JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
		});
	}
	
}
