//package com.alpha.message.common.utils;
//
//import org.apache.commons.lang.StringUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
//public class RegexUtil {
//
//	public static final String A = "a";
//	public static final String HREF = "href";
//
//	public static final String Number = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
//
//	public static final Element getLabel(String content, String label) {
//		if (StringUtils.isBlank(content) || StringUtils.isBlank(label)) {
//			return null;
//		}
//		Document doc = Jsoup.parse(content);
//		return doc.select(label).first();
//	}
//
//	public static final String getLabelInnerValue(String content, String label) {
//		String result = null;
//		Element link = RegexUtil.getLabel(content, label);
//		if (link != null) {
//			result = link.hasText() ? link.text() : result;
//		} else {
//			result = content;
//		}
//		return result;
//	}
//
//	public static final String getLabelAttrValue(String content, String label, String attr) {
//		String result = null;
//		Element link = RegexUtil.getLabel(content, label);
//		if (link != null) {
//			result = link.hasAttr(attr) ? link.attr(attr) : null;
//		}
//		return result;
//	}
//
//	public static boolean checkNumber(String str) {
//		return StringUtils.isBlank(str) ? false : str.matches(Number);
//	}
//}
