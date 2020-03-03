package org.springblade.modules.iot.util;

public class StringUtil {

	public static boolean strIsNullOrEmpty(String s) {
		return (null == s || s.trim().length() < 1);
	}

	/**
	 * 字符串左侧补0
	 *
	 * @param code
	 * @param num
	 * @return
	 */
	public static String paddingLeft(String code, int len) {
		String result = "";
		int num = len - code.length();

		result = String.format("%0" + num + "d", 0) + code;

		return result;
	}

	public static String[] stringToArray(String s) {
		if (s.length() >= 2) {
			return s.substring(1, s.length() - 1).split(",");
		}
		return null;
	}


	public static void main(String[] args) {
//		System.out.println(paddingLeft("cd", 4));
		System.out.println(Integer.parseInt("0B00",16));
		System.out.println(Integer.parseInt("0900",16));
	}

}

