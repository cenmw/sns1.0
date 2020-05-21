package com.cenmw.kindeditor.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cenmw.base.BaseAction;
import com.cenmw.kindeditor.vo.ImageDir;
import com.cenmw.kindeditor.vo.ImageFile;
import com.cenmw.util.DateUtil;
import com.cenmw.util.FileSorter;
import com.cenmw.util.JsonUtil;
import com.cenmw.util.StringUtil;

public class BrowseImageListAction extends BaseAction {
	private String cid;// 自定义唯一目录
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/*
	 * 用此变量得到当前系统的目录,来浏览图片 用逗号分开 如:cns,uploadifles,channel
	 * 得到相对路径/cns/uploadfiles/channel
	 */
	private String foldername;

	private String dir;
	/*
	 * 如果此变量不为空的话,应以些变量为浏览图片的相对路径
	 */
	private String path;
	/*
	 * 设置浏览图片的排序方式
	 */
	private String order;

	/**
	 * 新版 v4.1.1
	 * 
	 * @return
	 */
	public String browseImageLists() {
		String abPath = "";// 浏览文件的绝对路径
		String currentDirPath = "";
		if (path != null && path.length() > 0) {
			currentDirPath = path;
			abPath = request.getRealPath(path);
		} else {
			String temp = "/";
			if (StringUtil.notNull(foldername).length() > 0) {
				String arr[] = foldername.split(",");
				for (int i = 0; i < arr.length; i++) {
					temp += arr[i] + "/";
				}
			}
			String datestr = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd");
			temp += datestr + "/" + dir + "/";
			currentDirPath = temp;
			abPath = request.getRealPath(temp);
		}
		File file = new File(abPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		ImageDir imgDir = null;
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if (StringUtil.notNull(order).length() > 0) {
				if (order.equals("NAME")) {
					Arrays.sort(subFiles, new FileSorter(FileSorter.TYPE_NAME));
				} else if (order.equals("SIZE")) {
					Arrays.sort(subFiles, new FileSorter(
							FileSorter.TYPE_SIZE_UP));
				} else if (order.equals("TYPE")) {
					Arrays.sort(subFiles, new FileSorter(
							FileSorter.TYPE_DEFAULT));
				} else if (order.equals("TIMEDOWN")) {
					Arrays.sort(subFiles, new FileSorter(
							FileSorter.TYPE_MODIFIED_DATE_DOWN));
				} else if (order.equals("TIMEUP")) {
					Arrays.sort(subFiles, new FileSorter(
							FileSorter.TYPE_MODIFIED_DATE_UP));
				}
			}

			String moveup_dir_path = "";
			String current_dir_path = currentDirPath;
			String current_url = currentDirPath;
			int total_count = subFiles.length;
			List<ImageFile> file_list = new ArrayList();

			for (int i = 0; i < subFiles.length; i++) {
				File f = subFiles[i];

				boolean is_dir = f.isDirectory();// 是否为目录
				boolean has_file = false;
				String dir_path = "";

				if (is_dir) {
					dir_path = f.getAbsolutePath();
					File rfile = new File(dir_path);
					if (rfile.listFiles().length > 0) {
						has_file = true;
					}
				}
				long filesize = 0;
				if (is_dir) {
					filesize = FileUtils.sizeOfDirectory(f);
				} else {
					filesize = f.length();
				}
				String filename = f.getName();
				String filetype = "";
				if (!is_dir) {
					filetype = getFileExt(filename).toLowerCase();
				}
				boolean is_photo = checkPhone(filetype);
				Date date = new Date(f.lastModified());
				String datetime = DateUtil.getFormatDate(date,
						"yyyy-MM-dd HH:mm:ss");
				ImageFile imgFile = new ImageFile(is_dir, has_file, filesize,
						dir_path, is_photo, filetype, filename, datetime);
				file_list.add(imgFile);
			}
			imgDir = new ImageDir(moveup_dir_path, current_dir_path,
					current_url, total_count, file_list);

		}
		String jsonStr = "";
		if (imgDir != null) {
			jsonStr = JsonUtil.getJsonStrByVO(imgDir);
		}
		responseJSONAjax(jsonStr);
		return null;
	}

	public String browseImageList() {
		StringBuilder sb = new StringBuilder();
		String abPath = "";// 浏览文件的绝对路径
		String currentDirPath = "";
		if (path != null && path.length() > 0) {
			currentDirPath = path;
			abPath = request.getRealPath(path);
		} else {
			String temp = "/";
			if (StringUtil.notNull(foldername).length() > 0) {
				String arr[] = foldername.split(",");
				for (int i = 0; i < arr.length; i++) {
					temp += arr[i] + "/";
				}
			}
			if (cid != null && cid.length() > 0) {
				temp += cid + "/";
			}
			currentDirPath = temp;
			System.out.println("temp:" + temp);
			abPath = request.getRealPath(temp);
		}
		File file = new File(abPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (!file.exists()) {
			logger.info("文件或目录不存在");
		} else {
			if (file.isDirectory()) {
				File[] subFiles = file.listFiles();

				sb.append("{");
				sb.append("\"moveup_dir_path\":\"\",");
				sb.append("\"current_dir_path\":\"" + currentDirPath + "\",");
				sb.append("\"current_url\":\"" + currentDirPath + "\",");
				sb.append("\"total_count\":\"" + subFiles.length + "\",");
				sb.append("\"file_list\":[");
				for (int i = 0; i < subFiles.length; i++) {
					File f = subFiles[i];
					boolean isdir = f.isDirectory();// 是否为目录
					boolean hasfile = false;// 如果f文件是目录,验证目录下是否有文件
					String dir_path = "";
					if (isdir) {
						dir_path = f.getAbsolutePath();
						File rfile = new File(dir_path);
						if (rfile.listFiles().length > 0) {
							hasfile = true;
						}
					}
					long filesize = 0;
					if (isdir) {
						filesize = FileUtils.sizeOfDirectory(f);
					} else {
						filesize = f.length();
					}

					String filename = f.getName();
					String filetype = getFileExt(filename).toLowerCase();
					boolean isphone = checkPhone(filetype);
					Date date = new Date(f.lastModified());
					String datetime = DateUtil.getFormatDate(date,
							"yyyy-MM-dd HH:mm:ss");
					if (i > 0) {
						sb.append(",");
					}
					sb.append("{\"is_dir\":" + isdir + ",\"has_file\":"
							+ hasfile + ",\"filesize\":" + filesize
							+ ",\"dir_path\":\"" + dir_path
							+ "\",\"is_photo\":" + isphone + ",\"filetype\":\""
							+ filetype + "\",\"filename\":\"" + filename
							+ "\",\"datetime\":\"" + datetime + "\"}");

				}
				sb.append("]}");

			}
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			System.out.println("sb:" + sb);
			out.print(sb.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getFileExt(String fileName) {
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		// 组成一个新的文件名称
		return extension.substring(1);
	}

	private boolean checkPhone(String ext) {
		boolean t = false;
		String imgstr = "bmp,jpg,jpeg,png,gif";
		String[] arr = imgstr.split(",");
		for (int i = 0; i < arr.length; i++) {
			if (ext.equalsIgnoreCase(arr[i])) {
				t = true;
			}
		}
		return t;
	}

	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}
