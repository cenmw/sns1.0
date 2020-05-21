package com.cenmw.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * 
 * @version 1.0.0
 * @author 梁吉超
 * 
 */
public class TemplateUtils {
	private static final int bufferSize = 0x20000; // ~130K.

	public static String getPageBody(String filePath, String charsetName) {
		File input = new File(filePath);
		InputStream inStream = null;
		ByteBuffer byteData = null;
		try {
			inStream = new FileInputStream(input);
			byteData = readToByteBuffer(inStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parseByteData(byteData, charsetName);
	}

	private static ByteBuffer readToByteBuffer(InputStream inStream)
			throws IOException {
		byte[] buffer = new byte[bufferSize];
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(bufferSize);
		int read;
		while (true) {
			read = inStream.read(buffer);
			if (read == -1)
				break;
			outStream.write(buffer, 0, read);
		}
		ByteBuffer byteData = ByteBuffer.wrap(outStream.toByteArray());
		return byteData;
	}

	private static String parseByteData(ByteBuffer byteData, String charsetName) {
		String docData;
		docData = Charset.forName(charsetName).decode(byteData).toString();
		return docData;
	}

	/**
	 * 获取pattern表达式里的字符串
	 * 
	 * @param pattern
	 *            如<!--list-->([\\s\\S]*?)<!--/list-->
	 * @param pageBody
	 *            html字段串
	 * @return
	 */
	public static String getChannelBody(String pattern, String pageBody) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(pageBody);
		String pagebody = "";
		while (m.find()) {
			pagebody = m.group(m.groupCount());// 获取正则表达式包含的源代码
		}
		return pagebody;
	}

	public static String getParameters(String pattern, String pageBody) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(pageBody);
		String pagebody = "";
		while (m.find()) {
			pagebody = m.group(1);// 获取正则表达式包含的源代码
		}
		if (pagebody != null && pagebody.length() > 1) {
			pagebody = pagebody.substring(1);
		}
		return pagebody;
	}

	/**
	 * 如:key=list生成pattern:<!--list-->([\\s\\S]*?)<!--/list-->
	 * 但是可以用传参方式:<!--list?substr=3-->([\\s\\S]*?)<!--/list-->
	 * 
	 * @param key
	 * @return
	 */
	public static String createListPattern(String key) {
		return "<!--" + key + "([^-]*)-->([\\s\\S]*?)<!--/" + key + "-->";
	}

	/**
	 * 获取模板的全部页面源代码
	 * 
	 * @param filePath
	 *            模板绝对地址
	 * @param charsetname
	 *            获取模板的转码方式
	 * @return
	 */
	public static String getTemplateHTML(String filePath, String charsetname) {
		return getPageBody(filePath, charsetname);
	}

	/**
	 * 默认以UTF-8转码获取页面源代码
	 * 
	 * @param filePath
	 *            模板绝对地址
	 * @return
	 */
	public static String getTemplateHTML(String filePath) {
		return getPageBody(filePath, "UTF-8");
	}

	/**
	 * 通过request和相对路径获取模板的绝对地址 来取得模板的源代码字符串
	 * 
	 * @param request
	 * @param relativePath
	 *            模板相对地址
	 * @param charsetname
	 *            //转码方式
	 * @return
	 */
	public static String getTemplateHTML(HttpServletRequest request,
			String relativePath, String charsetname) {
		String filePath = request.getRealPath(relativePath);
		return getTemplateHTML(filePath, charsetname);
	}

	public static String getTemplateHTML(HttpServletRequest request,
			String relativePath) {
		return getTemplateHTML(request, relativePath, "UTF-8");
	}

	/**
	 * 根据获取的源代码字符串生成文件
	 * 
	 * @param html
	 *            源代码字符串
	 * @param outPath
	 *            输出的文件路径 如:D:\\index.shtml
	 */
	public static void generateFile(String html, String outPath) {
		File file = new File(outPath);
		try {
			FileUtils.writeStringToFile(file, html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getText(String html) {
		html = html == null ? "" : html;
		String text = Jsoup.clean(html, Whitelist.none());
		text = text.replaceAll("&nbsp;", " ");
		return text;
	}
}
