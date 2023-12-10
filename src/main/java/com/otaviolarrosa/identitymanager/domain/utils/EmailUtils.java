package com.otaviolarrosa.identitymanager.domain.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {
	private static final String expression = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final Pattern compiledPattern = Pattern.compile(expression);
	
	public static boolean isValid(String email) {
		Matcher matcher = compiledPattern.matcher(email);
		return matcher.matches();
	}
}
