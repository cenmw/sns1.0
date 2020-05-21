package com.cenmw.ckeditor.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.cenmw.base.BaseAction;
import com.cenmw.util.FileUtil;
import com.cenmw.util.StringUtil;

public class CkeditorAction extends BaseAction {
	private int id;
	private String foldername;// 多个文件名之间用逗号隔开（firstfoldername,secondfoldername）
	private Map imap;// 图片组
	private Map fmap;// flash组
	private String src;

	private File upload;
	private String contentType;// 上传文件的MIME类型
	private String fileName;// 上传文件的文件名，该文件名不包括文件的路径

	private String CKEditorFuncNum;

	public String deleteImage() {
		File file = new File(request.getRealPath(src));
		FileUtil.delFile(file);
		return SUCCESS;
	}

	public String deleteFlash() {
		File file = new File(request.getRealPath(src));
		FileUtil.delFile(file);
		return SUCCESS;
	}

	public String showImages() {
		String imagesPath = request.getRealPath("/uploadfiles/images");
		String url = "/uploadfiles/images";
		if (StringUtil.notNull(foldername).length() > 0) {
			String arr[] = foldername.split(",");
			String temp = "";
			for (int i = 0; i < arr.length; i++) {
				temp += "/" + arr[i];
			}
			imagesPath = request.getRealPath(temp);
			url = temp;
		}
		if (id > 0) {
			imagesPath += "\\" + id;
			url += "/" + id;
		}
		File root = new File(imagesPath);
		if (!root.exists()) {
			root.mkdirs();
		}
		File[] files = root.listFiles();
		imap = new HashMap<String, String>();
		for (File file : files) {
			url = url + "/" + file.getName();
			String ext = getExtention(file.getName());
			if (ext.equalsIgnoreCase(".png") || ext.equalsIgnoreCase(".jpg")
					|| ext.equalsIgnoreCase(".jpeg")
					|| ext.equalsIgnoreCase(".gif")
					|| ext.equalsIgnoreCase(".bmp"))
				imap.put(url, file.getName());
		}

		return SUCCESS;
	}

	public String showFlashs() {
		String flashsPath = request.getRealPath("/uploadfiles/flashs");
		String url = "/uploadfiles/flashs";
		if (StringUtil.notNull(foldername).length() > 0) {
			String arr[] = foldername.split(",");
			String temp = "";
			for (int i = 0; i < arr.length; i++) {
				temp += "/" + arr[i];
			}
			flashsPath = request.getRealPath(temp);
			url = temp;
		}
		if (id > 0) {
			flashsPath += "\\" + id;
			url += "/" + id;
		}
		File root = new File(flashsPath);
		if (!root.exists()) {
			root.mkdirs();
		}
		File[] files = root.listFiles();
		fmap = new HashMap<String, String>();
		for (File file : files) {
			url = url + "/" + file.getName();
			String ext = getExtention(file.getName());
			if (ext.equalsIgnoreCase(".swf"))
				fmap.put(url, file.getName());
		}

		return SUCCESS;
	}

	public String uploadImage() {
		// Struts2 请求 包装过滤器
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// 获得上传的文件名
		fileName = wrapper.getFileNames("upload")[0];
		upload = wrapper.getFiles("upload")[0];
		contentType = wrapper.getContentTypes("upload")[0];

		String tempPath = request.getRealPath("/uploadfiles/images/");
		String url = "/uploadfiles/images/";
		if (StringUtil.notNull(foldername).length() > 0) {
			String arr[] = foldername.split(",");
			String temp = "";
			for (int i = 0; i < arr.length; i++) {
				temp += "/" + arr[i];
			}
			tempPath = request.getRealPath(temp);
			url = temp;
		}
		if (id > 0) {
			tempPath += "\\" + id;
			url += "/" + id;
		}

		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			if (!tempFile.mkdirs()) {
				System.out.println("文件路径创建失败");
				return INPUT;
			}
		}
		String doFilePath = tempPath;
		doFilePath = doFilePath + "\\" + fileName;
		url += "/" + fileName;
		File doFile = new File(doFilePath);
		try {
			FileUtils.copyFile(upload, doFile);
		} catch (IOException e) {
			System.out.println("FileUtils.复制文件失败");
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
		try {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum + ",'" + url + "')</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String uploadFlash() {
		// Struts2 请求 包装过滤器
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// 获得上传的文件名
		fileName = wrapper.getFileNames("upload")[0];
		upload = wrapper.getFiles("upload")[0];
		contentType = wrapper.getContentTypes("upload")[0];

		String tempPath = request.getRealPath("/uploadfiles/flashs");
		String url = "/uploadfiles/flashs";
		if (StringUtil.notNull(foldername).length() > 0) {
			String arr[] = foldername.split(",");
			String temp = "";
			for (int i = 0; i < arr.length; i++) {
				temp += "/" + arr[i];
			}
			tempPath = request.getRealPath(temp);
			url = temp;
		}
		if (id > 0) {
			tempPath += "\\" + id;
			url += "/" + id;
		}

		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			if (!tempFile.mkdirs()) {
				System.out.println("文件路径创建失败");
				return INPUT;
			}
		}
		String doFilePath = tempPath;
		doFilePath = doFilePath + "\\" + fileName;
		url += "/" + fileName;
		File doFile = new File(doFilePath);
		try {
			FileUtils.copyFile(upload, doFile);
		} catch (IOException e) {
			System.out.println("FileUtils.复制文件失败");
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
		try {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum + ",'" + url + "')</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String upload() {
		// Struts2 请求 包装过滤器
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// 获得上传的文件名
		fileName = wrapper.getFileNames("upload")[0];
		upload = wrapper.getFiles("upload")[0];
		contentType = wrapper.getContentTypes("upload")[0];

		String tempPath = request.getRealPath("/uploadfiles");
		String url = "/uploadfiles";
		if (StringUtil.notNull(foldername).length() > 0) {
			String arr[] = foldername.split(",");
			String temp = "";
			for (int i = 0; i < arr.length; i++) {
				temp += "/" + arr[i];
			}
			tempPath = request.getRealPath(temp);
			url = temp;
		}
		if (id > 0) {
			tempPath += "\\" + id;
			url += "/" + id;
		}

		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			if (!tempFile.mkdirs()) {
				System.out.println("文件路径创建失败");
				return INPUT;
			}
		}
		String doFilePath = tempPath;
		doFilePath = doFilePath + "\\" + fileName;
		url += "/" + fileName;
		File doFile = new File(doFilePath);
		try {
			FileUtils.copyFile(upload, doFile);
		} catch (IOException e) {
			System.out.println("FileUtils.复制文件失败");
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
		try {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum + ",'" + url + "')</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getExtention(String filename) {
		int pos = filename.lastIndexOf(".");
		return filename.substring(pos);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	public Map getImap() {
		return imap;
	}

	public void setImap(Map imap) {
		this.imap = imap;
	}

	public Map getFmap() {
		return fmap;
	}

	public void setFmap(Map fmap) {
		this.fmap = fmap;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
