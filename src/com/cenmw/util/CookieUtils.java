package com.cenmw.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String key, String value, String path) {
		if (checkCookie(request, key, value)) {
			Cookie cookie1 = new Cookie(key, value);
			cookie1.setPath(path);
			cookie1.setMaxAge(7 * 24 * 60 * 60); // 存活期为7天
			response.addCookie(cookie1);
		} else {
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				String n = cookies[i].getName();
				String v = cookies[i].getValue();
				if (v.equals("")) {
					v = "0";
				}
				if (n.equals(key)) {
					String nm = "";
					nm = nm + value;
					cookies[i].setValue("" + nm);
					cookies[i].setPath(path);
					response.addCookie(cookies[i]);
				}
			}
		}
	}
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String key, String value, String path,int liveDay) {
		if (checkCookie(request, key, value)) {
			Cookie cookie1 = new Cookie(key, value);
			cookie1.setPath(path);
			cookie1.setMaxAge(liveDay * 24 * 60 * 60); // 存活期为7天
			response.addCookie(cookie1);
		} else {
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				String n = cookies[i].getName();
				String v = cookies[i].getValue();
				if (v.equals("")) {
					v = "0";
				}
				if (n.equals(key)) {
					String nm = "";
					nm = nm + value;
					cookies[i].setValue("" + nm);
					cookies[i].setPath(path);
					response.addCookie(cookies[i]);
				}
			}
		}
	}

	public static void delCookie(HttpServletRequest request,
			HttpServletResponse response, String key) {
		if (!checkCookie(request, key)) {
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				String n = cookies[i].getName();
				String v = cookies[i].getValue();
				if (v.equals("")) {
					v = "0";
				}
				if (n.equals(key)) {
					String nm = "";
					cookies[i].setValue("" + nm);
					cookies[i].setPath("/");
					response.addCookie(cookies[i]);
				}
			}
		}
	}

	/**
	 * 验证是否存在cookie
	 *
	 * @param request
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean checkCookie(HttpServletRequest request, String key,
			String value) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String n = cookies[i].getName();
				String v = cookies[i].getValue();
				if (n.equals(key)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String n = cookies[i].getName();
				String v = cookies[i].getValue();
				if (n.equals(key)) {
					return false;
				}
			}
		}
		return true;
	}

	public static String getCookieValue(HttpServletRequest request, String key) {
		String value = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String n = cookies[i].getName();
				String v = cookies[i].getValue();
				if (n.equals(key)) {
					value = v;
				}
			}
		}
		return value;
	}
}
