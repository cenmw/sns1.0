package com.cenmw.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.http.client.BufferingClientHttpRequestFactory;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

public class HttpURLConnectionUtils {
	public static void sendURL(String urlstr) throws IOException {
		URL url = new URL(urlstr);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
		http.setRequestProperty("content-type", "text/html");
		http.setRequestProperty("Accept-Charset", "utf-8");
		http.setDoOutput(true);
		http.setDoInput(true);
		http.connect();
		System.out.println("-------------------Begin---------------------");
		int res = 0;
		res = http.getResponseCode();
		System.out.println("res=" + res);
		http.disconnect();
		System.out.println("-------------------End---------------------");
	}

	// http://www.dream798.com/default.php?page=Display_Info&id=297
	public static String getURLresult(String urlstr) throws IOException {
		URL url = new URL(urlstr);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
		http.setRequestProperty("content-type", "text/html");
		http.setRequestProperty("Accept-Charset", "utf-8");
		http.setUseCaches(false);
		http.setDoOutput(true);
		http.setDoInput(true);
		http.connect();
		int res = 0;
		res = http.getResponseCode();
		StringBuffer buffer = new StringBuffer();
		if (res == 200) {
			InputStream u = http.getInputStream();// 获取servlet返回值，接收
			BufferedReader in = new BufferedReader(new InputStreamReader(u,"utf-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		}
		http.disconnect();
		String s=buffer.toString();
		return s;
	}
}
