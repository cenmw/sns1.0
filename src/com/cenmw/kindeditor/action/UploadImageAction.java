package com.cenmw.kindeditor.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.cenmw.base.BaseAction;
import com.cenmw.kindeditor.vo.UploadError;
import com.cenmw.util.DateUtil;
import com.cenmw.util.JsonUtil;
import com.cenmw.util.RootdirectoryPopUtil;
import com.cenmw.util.StringUtil;

public class UploadImageAction extends BaseAction {
	private String cid;
	private String foldername;
	/**
	 * 图片对象
	 */
	private File imgFile;

	/**
	 * 图片宽度
	 */
	private String imgWidth;

	/**
	 * 图片高度
	 */
	private String imgHeight;

	/**
	 * 图片对齐方式
	 */
	private String align;

	private String dir;

	/**
	 * 图片标题
	 */
	private String imgTitle;
	private String contentType;
	private String fileName;
	private String id;

	/**
	 * 新版 v4.1.1
	 * 
	 * @return
	 */
	public String uploadImages() {
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2");
		String message = "";
		String doFilePath = "";
		String url = "";
		// Struts2 请求 包装过滤器
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// 获得上传的文件名
		fileName = wrapper.getFileNames("imgFile")[0];
		imgFile = wrapper.getFiles("imgFile")[0];
		contentType = wrapper.getContentTypes("imgFile")[0];
		if (!checkFileType(fileName, extMap.get(dir))) {
			message = "请上传正确的文件格式(" + extMap.get(dir) + ")";
			responseHTMLAjax(getError(message));
			return null;
		}
		String tempPath = request.getRealPath("/uploadfiles");
		url = "/uploadfiles";
		if (StringUtil.notNull(foldername).length() > 0) {
			String arr[] = foldername.split(",");
			url = "";
			tempPath = request.getRealPath("/");
			for (int i = 0; i < arr.length; i++) {
				tempPath += "\\" + arr[i];
				url += "/" + arr[i];
			}
		}

		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			if (!tempFile.mkdirs()) {
				message = "上传失败";
				responseHTMLAjax(getError(message));
				return null;
			}
		}
		doFilePath = tempPath;
		String nfilename = generateFileName(fileName);

		String datestr = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd");

		doFilePath = doFilePath + "\\" + datestr + "\\" + dir + "\\"
				+ nfilename;
		url += "/" + datestr + "/" + dir + "/" + nfilename;
		File doFile = new File(doFilePath);
		try {
			FileUtils.copyFile(imgFile, doFile);
		} catch (IOException e) {
			message = "上传失败";
			e.printStackTrace();
			responseHTMLAjax(getError(message));
			return null;
		}

		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", url);
		String jsonStr = JsonUtil.getJsonStrByVO(obj);

		responseHTMLAjax(jsonStr);
		return null;
	}

	private String getError(String message) {
		UploadError eor = new UploadError(1, message);
		return JsonUtil.getJsonStrByVO(eor);
	}

	private boolean checkFileType(String filename, String typeStr) {
		String arr[] = typeStr.split(",");
		int position = fileName.lastIndexOf(".") + 1;
		String extension = fileName.substring(position).toLowerCase();
		for (String s : arr) {
			if (s.equals(extension)) {
				return true;
			}
		}
		return false;
	}

	public String uploadImage() {
		int error = 0;
		String message = "";
		String doFilePath = "";
		String url = "";

		if (error == 0) {
			// Struts2 请求 包装过滤器
			MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
			// 获得上传的文件名
			fileName = wrapper.getFileNames("imgFile")[0];
			imgFile = wrapper.getFiles("imgFile")[0];
			contentType = wrapper.getContentTypes("imgFile")[0];

			String uploadBasePath = RootdirectoryPopUtil.rootdirectorys
					.get("UploadFilesBasePath");
			String dateformat = DateUtil.getFormattedDate(new Date());

			String tempPath = request.getRealPath(uploadBasePath);
			url = uploadBasePath;

			File tempFile = new File(tempPath);
			if (!tempFile.exists()) {
				if (!tempFile.mkdirs()) {
					error = 1;
					message = "上传失败";
				}
			}
			String newname = generateFileName(fileName);
			doFilePath = tempPath;
			doFilePath = doFilePath + "\\" + dateformat + "\\image\\" + newname;
			url += "/" + dateformat + "/image/" + newname;
			File doFile = new File(doFilePath);
			try {
				FileUtils.copyFile(imgFile, doFile);
			} catch (IOException e) {
				error = 1;
				message = "上传失败";
				e.printStackTrace();
			}
		}
		String border = "0";
		String result = "<script type=\"text/javaScript\">parent.KE.plugin[\"image\"].insert(\""
				+ id
				+ "\",\""
				+ url
				+ "\",\""
				+ imgTitle
				+ "\",\""
				+ imgWidth
				+ "\",\""
				+ imgHeight
				+ "\",\""
				+ border
				+ "\",\""
				+ align
				+ "\");</script>";
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String generateFileName(String fileName) {
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		// 组成一个新的文件名称
		return "KE" + (System.currentTimeMillis()) + extension;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public void setImgFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setImgFileContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
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

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

}
