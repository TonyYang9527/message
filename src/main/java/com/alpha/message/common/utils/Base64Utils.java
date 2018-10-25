package com.alpha.message.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class Base64Utils {

	public static String decode(String props) {
		String result = null;
		try {
			byte[] encodeProps = Base64.getMimeDecoder().decode(props);
			result = new String(encodeProps, "utf-8");
		} catch (UnsupportedEncodingException e) {
			//log.error("decode  Exception:{} ", e);
		}
		return result;
	}

	public static String encode(String props) {
		String result = null;
		try {
			result = Base64.getMimeEncoder().encodeToString(props.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			//log.error("encode  Exception:{} ", e);
		}
		return result;

	}
}
