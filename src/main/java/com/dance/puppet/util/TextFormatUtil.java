package com.dance.puppet.util;

import java.util.regex.Pattern;

public class TextFormatUtil {
	public static String escape(String subjectString) {
		Pattern alphaNumeric = Pattern.compile("([^a-zA-z0-9])");
		return alphaNumeric.matcher(subjectString).replaceAll("\\\\$1");
	}
}
