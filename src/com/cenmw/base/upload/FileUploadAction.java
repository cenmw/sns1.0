package com.cenmw.base.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.cenmw.base.BaseAction;
import com.cenmw.util.DateUtil;
import com.cenmw.util.RootdirectoryPopUtil;

public class FileUploadAction extends BaseAction {
	// 封装上传文件的属性
	private File excel;
	// 封装上传文件名称属性
	private String fileName;
	// 封装上传文件类型属性
	private String contentType;
	// 保存文件路径属性
	private String dir;
	// 保存文件名称属性
	private String targetFileName;

	private String customDir;// 自定义目录 （file1,file2,file3）
	private String customType;// 自定义上传类型 设置指定上传的根目录
								// 值为rootdirectory.properties的name名称

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public void setExcelFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setExcelContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public String fileUpload() throws Exception {
		String uploadBasePath = RootdirectoryPopUtil.rootdirectorys
				.get("UploadFilesBasePath");
		String dateformat = DateUtil.getFormattedDate(new Date());
		String realPath = ServletActionContext.getRequest().getRealPath(
				uploadBasePath);
		String url = uploadBasePath;
		File tempFile = new File(realPath);
		if (!tempFile.exists()) {
			if (!tempFile.mkdirs()) {
				System.out.println("文件路径创建失败");
				return INPUT;
			}
		}
		// 获得upload路径的实际目录
		String targetDirectory = realPath + "\\" + dateformat + "\\file\\";
		// 生成保存文件的文件名称
		targetFileName = generateFileName(fileName);
		// 保存文件的路径
		setDir(url + "/" + dateformat + "/file/" + targetFileName);
		// 建立一个目标文件
		File target = new File(targetDirectory, targetFileName);
		// 将临时文件复制到目标文件
		FileUtils.copyFile(excel, target, request);
		request.setAttribute("message", "上传成功");
		return SUCCESS;
	}

	private String jsonStr;

	public String getProgressBar() {
		UploadInfo up = (UploadInfo) session.get("uploadInfo");
		jsonStr = "";
		if (up != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("\"totalSize\":" + up.getTotalSize() + "");
			sb.append(",\"bytesRead\":" + up.getBytesRead() + "");
			sb.append(",\"elapsedTime\":" + up.getElapsedTime() + "");
			sb.append(",\"status\":\"" + up.getStatus() + "\"");
			sb.append(",\"fileIndex\":" + up.getFileIndex() + "");
			int s = 1;
			if (up.isInProgress()) {
				s = 0;
			}
			sb.append(",\"isInProgress\":" + s + "");
			sb.append("}");
			jsonStr = sb.toString();
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
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
		return (System.currentTimeMillis()) + extension;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCustomDir() {
		return customDir;
	}

	public void setCustomDir(String customDir) {
		this.customDir = customDir;
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public File getExcel() {
		return excel;
	}

}
