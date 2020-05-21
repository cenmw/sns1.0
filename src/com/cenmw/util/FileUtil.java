package com.cenmw.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	public static String getFileName(String path) {
		String filename = "";
		path = path.substring((path.lastIndexOf("/")) + 1,
				path.lastIndexOf("."));
		filename = path;
		return filename;
	}

	public static String getExt(String path) {
		String ext = "";
		path = path.substring((path.lastIndexOf(".")) + 1);
		ext = path;
		return ext;
	}
	/**
	 * 删除文件  
	 * @param filePath 本地目录 
	 */
	public static void delFile(String filePath) {
		delFile(new File(filePath));
	}

	public static void delFile(File file) {
		if (!file.exists()) { // 判断文件是否存在
			return;
		}
		boolean rs = file.delete(); // 调用delete()方法
	}

	/**
	 * 删除目录（空目录或者非空目录）
	 * 
	 * @param dir
	 */
	public static void deleteDir(File dir) {
		// 检查参数
		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return;
		}
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				file.delete(); // 删除目录
			} else if (file.isDirectory()) {
				deleteDir(file); // 递规的方式删除目录
			}
		}
		dir.delete(); // 删除目录本身
	}
	
	public static void main(String [] str){
		try {
			FileUtils.forceDelete(new File("I:\\webapp\\workspace\\100tiao1Client_2013\\WebRoot\\WEB-INF\\classes\\brand"));
			System.out.println("========del............");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
