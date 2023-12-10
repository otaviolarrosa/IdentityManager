package com.otaviolarrosa.identitymanager.domain.utils;

public class StringUtils {
	public static boolean StringIsNullOrEmptyOrWhitespace(String str) {
		return (str.equals(null) || str.equals("") || str.equals(" "));
	}
	
	public static boolean HasMinimumLenght(String str, int minimunLenght) {
		return str.length() >= minimunLenght;
	}
}
