package com.cenmw.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class StringUtil {

	/**
	 * 指定字符从居中开始隐藏
	 * 
	 * @param str
	 *            原字符串
	 * @param count
	 *            隐藏字符个数
	 * @param replacement
	 *            替换字符
	 * @return
	 */
	public static String centerHidden(String str, int count, char replacement) {
		if (str == null || count < 1) {
			return str;
		}
		char[] chs = str.toCharArray();
		int offset = 0;
		if (chs.length - count > 0) {
			offset = (chs.length - count) / 2;
		}
		int end = Math.min(offset + count, chs.length);
		while (offset < end) {
			chs[offset++] = replacement;
		}
		return new String(chs);
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean tag = true;
		final String emailpattern = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(emailpattern);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 验证手机
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		boolean tag = true;
		final String emailpattern = "^[1][3,5,8][0-9]{9}$";
		final Pattern pattern = Pattern.compile(emailpattern);
		final Matcher mat = pattern.matcher(mobile);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}
	
	public static String notNull(String s) {
		return s == null ? "" : s.trim();
	}

	public static boolean notNullStr(String s) {
		if (notNull(s).length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Vector<String> splitBySpace(String source) {
		String[] keys = source.trim().split(" ");
		int nTLength = keys.length;
		Vector<String> vector = new Vector<String>();
		for (int i = 0; i < nTLength; i++)
			if ((!keys[i].trim().equals("")) && (vector.indexOf(keys[i]) == -1))
				vector.add(keys[i].trim());
		return vector;
	}

	/**
	 * 对关键词进行标红
	 * 
	 * @param sw
	 * @param word
	 * @return
	 */
	public static String SimpleHighlighter(String sw, String word) {
		for (String k : splitBySpace(sw)) {
			word = word.replaceAll(k, "<font color=red>" + k + "</font>");
		}
		return word;
	}

	public static String SimpleHighlighter(String sw, String word, int len) {
		word = getPartString(word, len);
		for (String k : splitBySpace(sw)) {
			word = word.replaceAll(k, "<font color=red>" + k + "</font>");
		}
		return word;
	}

	/**
	 * 截取字符串，一个汉字当两个长度来算
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getPartString(String str, int len) {
		return getPartString(str, len, true);
	}

	public static int getGBKLen(String str) {
		byte b[];
		try {
			b = str.getBytes("GBK");
			return b.length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getPartString(String str, int len, boolean addstr) {
		boolean paddingSpace = true;
		String valueTemp = "";
		// 指定的长度下，考虑字符的全角半角，最后的汉字。
		try {
			if (str.getBytes("GBK").length > len) {
				for (char c : str.toCharArray()) {
					if (valueTemp.getBytes("GBK").length <= len) {
						valueTemp += c;
						if (valueTemp.getBytes("GBK").length == len) {
							break;

						} else if (valueTemp.getBytes("GBK").length > len) {
							char[] charTemp = valueTemp.toCharArray();
							valueTemp = "";

							for (int i = 0; i < charTemp.length - 1; i++) {
								valueTemp += charTemp[i];
							}

							break;
						}
					}
				}
			} else {
				valueTemp = str;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 指定的长度下，长度不足的情况是否要在右边补空格。

		if (paddingSpace) {
			StringBuffer valueBuffer = new StringBuffer(valueTemp);
			for (int i = 0; i < len - valueTemp.getBytes().length; i++) {
				valueBuffer.append(" ");
			}
			try {
				if (addstr && str.getBytes("GBK").length > len) {
					valueBuffer.append("...");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return valueBuffer.toString();
		}
		return valueTemp;
	}

	/**
	 * SQL Server将带有空格的字符替换%来检
	 * 
	 * @param sw
	 * @return
	 */
	public static String praseSearchSQL(String sw) {
		// 替换半角空格
		sw = sw.replaceAll(" ", "%");
		// 替换全角空格
		sw = sw.replaceAll("　", "%");
		return "%" + sw + "%";
	}

	/**
	 * 判断字符串是否为空或都为空字符串
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}

	public static String[] getIds(String ids) {
		return ids.split(",");
	}

	/**
	 * 替换字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String StrReplace(String s, String s1, String s2) {
		String s3 = "";
		boolean flag = false;
		int i1 = 0;
		int j = s1.length();
		if (s.indexOf(s1) > -1)
			while (s.indexOf(s1, i1) > -1) {
				int i = s.length();
				if (s.indexOf(s1) == 0) {
					s = s2 + s.substring(j, i);
				} else {
					int k = s.indexOf(s1);
					int l = k + j;
					s = s.substring(0, k) + s2 + s.substring(l, i);
					i1 = ((k + s2.length()) - j) + 1;
				}
			}
		s3 = s;
		return s3;
	}

	public static int getFixID(String s) {
		int i = 0;
		if (s == null)
			s = "0";
		try {
			i = (new Integer(s)).intValue();
		} catch (Exception exception) {
			System.out.print("ID:" + s + "\u65E0\u6CD5\u4FEE\u6B63!");
		}
		return i;
	}

	public static int getFixID(Integer integer) {
		int i = 0;
		if (integer != null)
			i = integer.intValue();
		return i;
	}

	public static Integer getFixInteger(String s) {
		Integer integer = new Integer(0);
		if (s == null)
			s = "0";
		integer = new Integer(s);

		return integer;
	}

	public static int getFixInt(String s) {
		Object obj = null;
		Object obj1 = null;
		int i = -1;
		String s1 = "";
		try {
			Pattern pattern = Pattern.compile("[^0-9]");
			Matcher matcher = pattern.matcher(s);
			s1 = matcher.replaceAll("");
			if ("".equals(s1))
				s1 = "-1";
		} catch (Exception exception) {
			s1 = "0";
		}
		i = Integer.parseInt(s1);
		return i;
	}

	public static String RemoveScripts(String s) {
		Object obj = null;
		Object obj1 = null;
		String s1 = "";
		if (!"".equals(s) && s != null) {
			Pattern pattern = Pattern.compile("\r\n");
			Matcher matcher = pattern.matcher(s);
			s1 = matcher.replaceAll("");
			pattern = Pattern.compile("(<script)([^>]*>.*?)(<\\/script>)", 2);
			matcher = pattern.matcher(s1);
			s1 = matcher.replaceAll("");
		}
		return s1;
	}

	public static String RemoveHTMLHaveEnter(String s) {
		Object obj = null;
		Object obj1 = null;
		String s1 = "";
		if (!"".equals(s) && s != null) {
			Pattern pattern = Pattern.compile("(\\<.*?[\\>])", 2);
			Matcher matcher = pattern.matcher(s1);
			s1 = matcher.replaceAll("");
		}
		return s1;
	}

	public static String RemoveHTML(String s) {
		Object obj = null;
		Object obj1 = null;
		String s1 = "";
		if (!"".equals(s) && s != null) {
			Pattern pattern = Pattern.compile("\r\n");
			Matcher matcher = pattern.matcher(s);
			s1 = matcher.replaceAll("");
			pattern = Pattern.compile("(\\<.*?[\\>])", 2);
			matcher = pattern.matcher(s1);
			s1 = matcher.replaceAll("");
		}
		return s1;
	}

	public static String getFixParameterString(String s) {
		Pattern pattern = null;
		Matcher matcher = null;
		String s1 = "";
		pattern = Pattern.compile("[^a-zA-Z0-9]");
		matcher = pattern.matcher(s);
		s1 = matcher.replaceAll("");
		return s1;
	}

	public static String getFixString(String s) {
		Object obj = null;
		Object obj1 = null;
		String s1 = "";
		try {
			Pattern pattern = Pattern.compile("[^a-zA-Z]");
			Matcher matcher = pattern.matcher(s);
			s1 = matcher.replaceAll("");
		} catch (Exception exception) {
		}
		return s1;
	}

	public static int getInteger(String s) {
		Object obj = null;
		Object obj1 = null;
		int i = -1;
		String s1 = "";
		try {
			Pattern pattern = Pattern.compile("[^0-9]");
			Matcher matcher = pattern.matcher(s);
			s1 = matcher.replaceAll("");
			if ("".equals(s1))
				s1 = "-1";
		} catch (Exception exception) {
			s1 = "0";
		}
		i = Integer.parseInt(s1);
		return i;
	}

	public static String getDate() {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return simpledateformat.format(date);
	}

	public static String getFormatDate(String s) throws ParseException,
			java.text.ParseException {
		if (s == null) {
			return s = "";
		} else {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(
					"yyyy-MM-dd");
			Date date = simpledateformat.parse(s);
			return simpledateformat.format(date);
		}
	}

	public static Date getFormatDate(String s, String format)
			throws ParseException, java.text.ParseException {
		if (s == null) {
			return new Date();
		} else {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
			return simpledateformat.parse(s);
		}
	}

	public static String HTMLEncoding(String s) {
		s = StrReplace(s, "<", "&lt;");
		s = StrReplace(s, ">", "&gt;");
		s = StrReplace(s, "\r\n", "<BR>");
		s = StrReplace(s, " ", "&nbsp;");
		return s;
	}

	public static String HTMLDecoding(String s) {
		s = StrReplace(s, "&lt;", "<");
		s = StrReplace(s, "&gt;", ">");
		s = StrReplace(s, "<BR>", "\r\n");
		s = StrReplace(s, "&nbsp;", " ");
		return s;
	}

	public static String HTMLEncode(String s) {
		if (s == null)
			return "";
		StringBuffer stringbuffer = null;
		char ac[] = null;
		int i = 0;
		int j = s.length();
		int k = 0;
		do {
			if (k >= j)
				break;
			char c = s.charAt(k);
			switch (c) {
			case 0: // '\0'
			case 34: // '"'
			case 38: // '&'
			case 60: // '<'
			case 62: // '>'
				if (stringbuffer == null) {
					ac = s.toCharArray();
					stringbuffer = new StringBuffer(j + 10);
				}
				if (k > i)
					stringbuffer.append(ac, i, k - i);
				i = k + 1;
				switch (c) {
				case 38: // '&'
					stringbuffer.append("&");
					break;

				case 60: // '<'
					stringbuffer.append("<");
					break;

				case 62: // '>'
					stringbuffer.append(">");
					break;

				case 34: // '"'
					stringbuffer.append("\"");
					break;
				}
				break;
			}
			k++;
		} while (true);
		if (stringbuffer == null) {
			return s;
		} else {
			stringbuffer.append(ac, i, j - i);
			return stringbuffer.toString();
		}
	}

	public static String URLEncode(String s)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(s, "UTF-8");
	}

	public static String URLDecoder(String s)
			throws UnsupportedEncodingException {
		return java.net.URLDecoder.decode(s, "UTF-8");
	}

	public static String URLEncode2(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < s.length(); i++)
			switch (s.charAt(i)) {
			case 32: // ' '
				stringbuffer.append("%20");
				break;

			case 43: // '+'
				stringbuffer.append("%2b");
				break;

			case 39: // '\''
				stringbuffer.append("%27");
				break;

			case 47: // '/'
				stringbuffer.append("%2F");
				break;

			case 46: // '.'
				stringbuffer.append("%2E");
				break;

			case 60: // '<'
				stringbuffer.append("%3c");
				break;

			case 62: // '>'
				stringbuffer.append("%3e");
				break;

			case 35: // '#'
				stringbuffer.append("%23");
				break;

			case 37: // '%'
				stringbuffer.append("%25");
				break;

			case 38: // '&'
				stringbuffer.append("%26");
				break;

			case 123: // '{'
				stringbuffer.append("%7b");
				break;

			case 125: // '}'
				stringbuffer.append("%7d");
				break;

			case 92: // '\\'
				stringbuffer.append("%5c");
				break;

			case 94: // '^'
				stringbuffer.append("%5e");
				break;

			case 126: // '~'
				stringbuffer.append("%73");
				break;

			case 91: // '['
				stringbuffer.append("%5b");
				break;

			case 93: // ']'
				stringbuffer.append("%5d");
				break;

			case 61: // '='
				stringbuffer.append("%3D");
				// fall through

			default:
				stringbuffer.append(s.charAt(i));
				break;
			}

		return stringbuffer.toString();
	}

	public static String getFixValue(String s)
			throws UnsupportedEncodingException {
		return getFixValue(s, "");
	}

	public static String getFixValue(String s, String s1)
			throws UnsupportedEncodingException {
		if (s == null)
			s = "";
		if (s1 == null)
			s1 = "";
		String s2 = new String(s.getBytes("ISO-8859-1"), "utf-8");
		s2 = s2.trim();
		s2 = StrReplace(s2, "$", "\uFF04");
		if (s1.equals("") || s1.toUpperCase().equals("SQLSERVER"))
			s2 = s2.replaceAll("'", "''");
		return s2;
	}

	public static String getFixGBKValue(String s)
			throws UnsupportedEncodingException {
		if (s == null)
			s = "";
		String s1 = new String(s.getBytes("8859_1"), "utf-8");
		s1 = s1.trim();
		s1 = s1.replaceAll("'", "''");
		s1 = StrReplace(s1, "$", "\uFF04");
		return s1;
	}

	public static String getFixValue(int i) {
		String s = String.valueOf(i);
		return s;
	}

	/**
	 * 
	 * @param s
	 *            : 写入文件的内容
	 * @param s1
	 *            ：要生成的文件名称
	 * @param s2
	 *            ：生成的文件路径
	 * @throws IOException
	 */
	public static void writeFile(String s, String s1, String s2)
			throws IOException {
		File file = new File(s2);
		if (!file.exists())
			file.mkdirs();
		FileOutputStream fileoutputstream = new FileOutputStream(s2 + "/" + s1);
		OutputStreamWriter outputstreamwriter = new OutputStreamWriter(
				fileoutputstream, "UTF-8");
		outputstreamwriter.write(s);
		outputstreamwriter.close();
	}

	public static String getFormattedDate(String s, String s1)
			throws java.text.ParseException {
		if (s == null)
			return s = "";
		if (s1 == null || s1 == "")
			s1 = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s1);
		Date date = null;
		try {
			date = simpledateformat.parse(s);
		} catch (ParseException parseexception) {
			return "";
		}
		return simpledateformat.format(date);
	}

	public static String getFormattedDate(String s)
			throws java.text.ParseException {
		return getFormattedDate(s, "");
	}

	public static String getNoTransformValue(String s) {
		if (s == null)
			s = "";
		String s1 = s;
		s1 = s1.replaceAll("'", "''");
		return s1;
	}

	public static int add(int a, int b) {
		return a + b;
	}

	private final static String CUTPOINT = "<img src=\"/common/kindeditor/plugins/point.gif\" />";// 内容分割点

	/**
	 * 内容分类
	 * 
	 * @return
	 */
	public static String contentCut(String body) {
		String contentcut = "";
		if (body.indexOf(CUTPOINT) > 0) {
			String arr[] = body.split(CUTPOINT);
			contentcut = arr[0];
		}
		return contentcut;
	}

	/**
	 * 支掉内容里的分割点
	 * 
	 * @param body
	 * @return
	 */
	public static String replaceContentCut(String body) {
		return body = body.replaceAll(CUTPOINT, "");
	}

	/**
	 * 按照长度len来随即生成一个随机字符串组合
	 * 
	 * @param body
	 * @return
	 */
	public static String getRandom(int len) {
		Random random = new Random();
		String base = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefhijklmnpqrstuvwxyz";
		int length = base.length();
		StringBuffer randomCode = new StringBuffer();
		for (int i = 0; i < len; i++) { // 旋转图形
			int start = random.nextInt(length);
			String strRand = base.substring(start, start + 1);
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	/**
	 * 随机取出basenum数之内的n个不同的数
	 * 
	 * @param basenum
	 * @param n
	 * @return
	 */
	public static List getRanddomNosame(int basenum, int n) {
		List list = new ArrayList();
		int max = basenum;
		Random rand = new Random();
		boolean[] bool = new boolean[max];
		int num = 0;
		for (int i = 0; i < n; i++) {
			do {
				// 如果产生的数相同继续循环
				num = rand.nextInt(max);
			} while (bool[num]);

			bool[num] = true;

			list.add(num);

		}
		return list;
	}

	public static boolean checkHTTP(String str) {
		String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// 符号转换
	public static String fhzh(String s) {
		s = s.replaceAll("&nbsp;", " ");
		s = s.replaceAll("&quot;", "'");
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&lsquo;", "’");
		s = s.replaceAll("&rsquo;", "‘");
		s = s.replaceAll("&ldquo;", "“");
		s = s.replaceAll("&rdquo;", "”");
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&amp;", "&");

		return s;
	}

	/**
	 * 解决 replaceAll 替换字符串时对正则$ \ 特殊符号报错问题
	 * 
	 * @param pattern
	 *            非正则表达式 为指定字符串
	 * @param source
	 * @param newstr
	 * @return
	 */
	public static String replaceStrAll(String pattern, String source,
			String newstr) {
		while (source.indexOf(pattern) >= 0) {
			source = source.replace(pattern, newstr);
		}
		return source;
	}

	/**
	 * 解决 replaceAll 替换字符串时对正则$ \ 特殊符号报错问题
	 * 
	 * @param pattern
	 *            正则表达式
	 * @param source
	 * @param newstr
	 * @return
	 */
	public static String replacePatternAll(String pattern, String source,
			String newstr) {
		long round = (new Date()).getTime();
		String p = "<!--Pattern" + round + "-->";
		source = source.replaceAll(pattern, p);
		source = replaceStrAll(p, source, newstr);
		return source;
	}

	/**
	 * 重命名
	 * 
	 * @param name
	 * @param small
	 *            为要给文件名添加的字符 如原名12.jpg 添加_50 结果为12_50.jpg
	 * @return
	 */
	public static String rename(String name, String small) {
		String smallname = small;
		String pic = "";
		if (name != null && name.length() > 0) {
			pic = name;
			if (pic.indexOf(smallname) < 0) {
				int index = pic.lastIndexOf(".");
				String ext = pic.substring(index);
				String basepath = pic.substring(0, index);
				pic = basepath + smallname + ext;
			}
		}
		return pic;
	}

	public static boolean checkZZS(String val) {
		String reg = "^[0-9]*[1-9][0-9]*$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(val);
		boolean b = m.matches();
		return b;
	}

	public static void main(String args[]) {
//		String str = "<!--list-->1<!--list-->2<!--list-->3<!--list-->4<!--list-->5<!--list-->";
//		str = replaceStrAll("<!--list-->", str, "$");
//		System.out.println(str);
		
		//String title = "安卓新版本升级|1.1";
		//System.out.println("====="+title.indexOf("|"));
	//	System.out.println(checkEmail("par_child@126.com"));
		
		String ss="5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5||5";
		String [] ssargs = ss.split("\\|\\|");
		System.out.println(ssargs.length);
	}
	
	public static int getRegCount(String s,String reg){
		int count=0;
		while(s.indexOf(reg)>0){   
			count++;   
			s=s.replace(reg,""); //将统计过的abc替换为空  然后继续循环}
		}
		
		return count;
	}
}
