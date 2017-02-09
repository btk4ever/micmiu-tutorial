package com.micmiu.tutorial.java.html;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 抓取上海小区地址
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 5/26/2015
 * Time: 14:14
 */
public class CrawlAddressMain {


	public static void main(String[] args) throws Exception {

		String url = "http://www.iwjw.com/sale/shanghai/p4/";
		CrawlAddressMain crawler = new CrawlAddressMain();
		crawler.crawlAddress(url);


	}

	public void crawlAddress(String url) throws Exception {
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
				.ignoreHttpErrors(true).timeout(3000).get();
		for (Element ele : doc.select("P.Cb")) {
			//System.out.println(ele.html());
			if (ele.text().contains("路")) {
				String[] arr = ele.text().split(" ");
				System.out.println(arr[arr.length - 1]);
			}
		}
	}

	public String getResponse(String url) throws IOException {
		try {
			Connection.Response response = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.ignoreHttpErrors(true)
					.timeout(3000)
					.execute();
			return response.body();
		} catch (IOException e) {
			throw e;
		}
	}
}
