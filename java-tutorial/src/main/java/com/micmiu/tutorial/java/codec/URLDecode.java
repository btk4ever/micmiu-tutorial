package com.micmiu.tutorial.java.codec;

import java.net.URLDecoder;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/30/2014
 * Time: 16:20
 */
public class URLDecode {

	private static final String[] encodes = new String[]{"UTF-8", "GBK", "GB2312", "ISO-8859-1", "UTF-16BE", "UTF-16LE", "UTF-16"};

	public static void main(String[] args) throws Exception {
		String str = "%AF%AB%CB%00%00%00%00%20%94%E1%E8%81%00%00%00%01%00%00%00%00%00%00%00%01%00%00%00%08RootPOA%00%00%00%00%08%00%00%00%01%00%00%00%00%14";

		for (String encode : encodes) {
			String ret = URLDecoder.decode(str, encode);
			System.out.println(encode + " --> " + ret);
		}

	}
}
