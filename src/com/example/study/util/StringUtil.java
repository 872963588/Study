package com.example.study.util;

public class StringUtil {

	public static boolean isEmpty(String data) {
		if (data == null || "".equals(data)) {
			return true;
		}
		return false;
	}
}
