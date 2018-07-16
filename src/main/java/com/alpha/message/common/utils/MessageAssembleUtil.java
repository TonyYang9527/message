package com.alpha.message.common.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

public class MessageAssembleUtil {

    /** The Constant regex1. */
    private static final String regexStart = "\\$\\{(";

    /** The Constant regex2. */
    private static final String regexEnd = ")\\}";
    
    
    /**
     * Get Template  content  &&   key -value  collections 
     * @param templateContent
     * @param props
     * @return the string
     */
    public static String assembleContent(String templateContent, Map<String, String> props) {
        Preconditions.checkNotNull(templateContent, "  TemplateContent  is  not empty");
        StringBuffer message = new StringBuffer(100);
        if (props.size() <= 0) {
            return templateContent;
        }
        /** get   key-value ***/
        String patternString = regexStart + StringUtils.join(props.keySet(), "|") + regexEnd;
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(templateContent);
        while (matcher.find()) {
            matcher.appendReplacement(message, props.get(matcher.group(1)));
        }
        matcher.appendTail(message);
        return message.toString();
    }
}
