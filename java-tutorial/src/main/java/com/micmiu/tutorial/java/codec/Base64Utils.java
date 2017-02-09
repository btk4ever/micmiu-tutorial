package com.micmiu.tutorial.java.codec;

import org.apache.commons.codec.binary.Base64;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 9/22/2016
 * Time: 13:32
 */
public class Base64Utils {

	public static final String DEF_CHARSET = "utf-8";

	public static void main(String[] args) {
		String str = "";
	}

	public static String decode(byte[] bytes) {
		return new String(new Base64().decode(bytes));
	}

	public static String decode(String str) {
		return new String(new Base64().decode(str));
	}

	public static String decode(byte[] bytes, String charset) {
		try {
			return new String(new Base64().decode(bytes), charset);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encode(byte[] bytes) {
		return new Base64().encodeAsString(bytes);
	}


	public static String encode(String str, String charset) {
		try {
			return encode(str.getBytes(charset));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encode(String str) {
		return encode(str, DEF_CHARSET);
	}
}


