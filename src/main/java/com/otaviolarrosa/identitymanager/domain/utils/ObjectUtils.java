package com.otaviolarrosa.identitymanager.domain.utils;

public class ObjectUtils {
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
}
