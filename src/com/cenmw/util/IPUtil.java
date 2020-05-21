package com.cenmw.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
	public static String getRealIP(HttpServletRequest request) {
		String ip = "";
		try {
			ip = StringUtil.notNull(request.getHeader("x-forwarded-for"));
			if (ip.equals("") || "unknown".equalsIgnoreCase(ip)) {
				ip = StringUtil.notNull(request.getHeader("Proxy-Client-IP"));
			}
			if (ip.equals("") || "unknown".equalsIgnoreCase(ip)) {
				ip = StringUtil
						.notNull(request.getHeader("WL-Proxy-Client-IP"));
			}
			if ((ip.equals("") || "unknown".equalsIgnoreCase(ip))) {
				ip = StringUtil.notNull(request.getRemoteAddr());
			}
		} catch (Exception e) {
		}
		return ip;
	}

	public static long computeIp(String sIp) {
		long iIp = 0;
		String[] sIpArr = sIp.trim().split("\\.");
		int iCount = sIpArr.length;
		for (int i = 0; i < iCount; i++) {
			try {
				iIp += Long.parseLong(sIpArr[i]) * (long) Math.pow(256, 4 - i);
			} catch (Exception ex) {
				iIp = 0;
			}
		}
		return iIp;
	}
}