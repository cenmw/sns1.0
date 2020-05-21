package com.cenmw.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class IPConfigUtil {
	public static Map<String, String> iplinks = new HashMap<String, String>();
	static {
		IPConfigUtil util = new IPConfigUtil();
	}

	public IPConfigUtil() {
		this.init();
	}

	private void init() {
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
				"/IPConfig.properties");
		try {
			prop.load(in);
			for (Object obj : prop.keySet()) {
				iplinks.put(obj.toString(), prop.getProperty(obj.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
