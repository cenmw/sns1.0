package com.cenmw.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ContentTypeUtils {
	public static Map<String, String> contenttypes = new HashMap<String, String>();
	static {
		ContentTypeUtils util = new ContentTypeUtils();
	}

	public ContentTypeUtils() {
		this.init();
	}

	private void init() {
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
				"/contenttype.properties");
		try {
			prop.load(in);
			for (Object obj : prop.keySet()) {
				contenttypes.put(obj.toString(), prop.getProperty(obj
						.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证文件类型是否符合要求
	 * 
	 * @param contentType
	 *            将要验证的文件类型
	 * @param checklist
	 *            符合要求的文件类型列表 类型用逗号分开
	 *            如:image/x-ms-bmp,image/gif,image/jpeg,image
	 *            /pjpeg,image/png,image
	 *            /x-png,image/bmp,application/msword,application
	 *            /x-msexcel,application/ms-excel
	 * @return
	 */
	public static boolean checkFileType(String contentType, String checklist) {
		List checktypelist = new ArrayList();
		String checkArr[] = checklist.split(",");
		for (Object obj : contenttypes.keySet()) {
			String k = obj.toString();
			for (int i = 0; i < checkArr.length; i++) {
				if (k.equalsIgnoreCase(checkArr[i])) {
					checktypelist.add(contenttypes.get(obj));
				}
			}
		}
		if (!checktypelist.isEmpty()) {
			for (Object obj : checktypelist) {
				String v = obj.toString();
				if (v.equalsIgnoreCase(contentType)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkImage(String contentType) {
		String v = "";
		for (Object obj : contenttypes.keySet()) {
			String k = obj.toString();
			if (k.equalsIgnoreCase("image")) {
				v = contenttypes.get(obj);
			}
		}
		String[] arr = v.split(",");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equalsIgnoreCase(contentType)) {
				return true;
			}
		}
		return false;
	}
}
