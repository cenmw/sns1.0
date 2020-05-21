package com.cenmw.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.xfire.util.Base64;

public class WebUtil {
	/**
	 * http://localhost/CMSWeb/ 返回 /CMSWeb/
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI();
	}

	/**
	 * 拼hql子条
	 * 
	 * @param newHql
	 * @param map
	 * @return
	 */
	public static StringBuffer getHqlParameter(StringBuffer newHql, Map map,
			boolean isOr) {
		String keyValue = "";
		for (Object key : map.keySet()) {
			String keyName = (String) key;
			String split = isOr == true ? "or" : "and";
			ParameterBean pb = (ParameterBean) map.get(key);
			keyValue = pb.getName();
			if (pb.getOp().trim().equals("like")) {
				keyValue = "'%" + keyValue + "%'";
			} else if (pb.getOp().trim().equals("<")
					|| pb.getOp().trim().equals("<=")
					|| pb.getOp().trim().equals(">")
					|| pb.getOp().trim().equals(">=")) {
				keyValue = "'" + keyValue + "'";
			}
			newHql.append(" " + split + " " + keyName + " " + pb.getOp() + " "
					+ keyValue + " ");
		}
		return newHql;
	}

	/**
	 * 拼链接参数
	 * 
	 * @param map
	 * @return
	 */
	public static String getParameters(Map map) {
		String parameters = "";
		if (map.size() > 0) {
			parameters = "&";
		}
		for (Object key : map.keySet()) {
			String keyName = (String) key;
			ParameterBean pb = (ParameterBean) map.get(key);
			if (keyName.indexOf("name") >= 0) {
				parameters += "searchname=" + pb.getName() + "&";
			} else {
				parameters += keyName + "=" + pb.getName() + "&";
			}
		}
		if (parameters.length() > 1) {
			parameters = parameters.substring(0, parameters.length() - 1);
		}
		return parameters;
	}
	
	// 转义
	public static String getBase64Encode(String name) {
		String res = "";
		if (!StringUtil.isEmpty(name)) {
			try {
				res = "@b" + Base64.encode(name.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				System.out.println("转义出错============");
			}
		}
		return res;
	}

	public static String getBase64priceEncode(String price) {
		String res = "";
		try {
			res = "@b" + Base64.encode(("¥" + price).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("转义出错============");
		}

		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(getBase64Encode("${pl.shopname}"));
	}
}
