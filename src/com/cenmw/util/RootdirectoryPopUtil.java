package com.cenmw.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RootdirectoryPopUtil {
	/**
	 * 系统自动存储项目根路径 WebLocalPath
	 */
	public static Map<String, String> rootdirectorys = new HashMap<String, String>();
	static {
		RootdirectoryPopUtil util = new RootdirectoryPopUtil();
	}

	public RootdirectoryPopUtil() {
		this.init();
	}

	private void init() {
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
				"/rootdirectory.properties");
		try {
			prop.load(in);
			for (Object obj : prop.keySet()) {
				rootdirectorys.put(obj.toString(),
						prop.getProperty(obj.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param relaPath
	 *            文件相对路径
	 * @return
	 */
	public static String getPicLocalPath(String relaPath) {
		String webLocalPath = RootdirectoryPopUtil.rootdirectorys.get(
				"WebLocalPath").toString();
		relaPath = relaPath.substring(1);
		relaPath = relaPath.replace("/", "\\");
		return webLocalPath + relaPath;
	}
}
