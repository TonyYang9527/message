//package com.alpha.message.common.utils;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.util.Map;
//
//import com.github.mustachejava.DefaultMustacheFactory;
//import com.github.mustachejava.Mustache;
//import com.github.mustachejava.MustacheFactory;
//import com.google.common.base.Preconditions;
//
//
//public class MessageAssembleUtil {
//
//	public static String mustacheContent(String templateName, String templateContent, Map<String, Object> props)
//			throws IOException {
////		Preconditions.checkNotNull(templateContent, " TemplateContent  is  not empty");
////		//MustacheFactory mf = new DefaultMustacheFactory();
////		//Mustache mustache = mf.compile(new StringReader(templateContent), templateName);
////		StringWriter writer = new StringWriter();
////		mustache.execute(writer, props).flush();
////		log.info("mustacheContent templateName:{} ,props:{} ",templateName,props);
//		return writer.toString();
//	}
//}
