package com.cenmw.comment.manager.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.im4java.core.IM4JavaException;

import com.cenmw.base.BaseAction;
import com.cenmw.util.DateUtil;
import com.cenmw.util.FileUtil;
import com.cenmw.util.Im4ImageUtil;
import com.cenmw.util.RootdirectoryPopUtil;

public class CommentFaceUploadManagerAction extends BaseAction {
	protected File filedata; // 上传文件
	private String filedataFileName; // 上传文件名
	private String filedataContentType;// 文件类型
	private String newDir;

	private String delPic;

	public String uploadImage() throws InterruptedException, IM4JavaException {
		String uploadBasePath = RootdirectoryPopUtil.rootdirectorys
				.get("UploadFilesBasePath");
		String ext = FileUtil.getExt(filedataFileName);
		ext = ext.toLowerCase();
		if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("bmp")
				|| ext.equals("gif") || ext.equals("png")) {

		} else {
			request.setAttribute("uperror", "请上传正确的图片格式！");
			return INPUT;
		}
		String realPath = request.getRealPath(uploadBasePath);
		String url = uploadBasePath;
		File tempFile = new File(realPath);
		if (!tempFile.exists()) {
			if (!tempFile.mkdirs()) {
				request.setAttribute("uperror", "上传失败");
				return INPUT;
			}
		}
		String newFileName = getNewFileName();
		String dateformat = DateUtil.getFormattedDate(new Date());
		String urlfile = realPath + "\\" + dateformat + "\\" + "\\"
				+ "face" + "\\" + newFileName + "." + ext;
		File dstFile = new File(urlfile);
		try {
			FileUtils.copyFile(filedata, dstFile);
			newDir = url + "/" + dateformat + "/" + "face/" + newFileName
					+ "." + ext;
			String srcPath = newDir;
			String smallPath = rename(srcPath, "_118×126");
			Im4ImageUtil.cropImageCenter(request.getRealPath(srcPath),
					request.getRealPath(smallPath), 118, 126);
			String bigPath = rename(srcPath, "_238×396");
			Im4ImageUtil.cropImageCenter(request.getRealPath(srcPath),
					request.getRealPath(bigPath), 238, 396);
			if (delPic != null && delPic.length() > 0) {
				FileUtil.delFile(request.getRealPath(delPic));
				FileUtil.delFile(request
						.getRealPath(rename(delPic, "_118×126")));
				FileUtil.delFile(request
						.getRealPath(rename(delPic, "_238×396")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private String rename(String name, String small) {
		String smallname = small;
		String pic = name;
		if (pic.indexOf(smallname) < 0) {
			int index = pic.lastIndexOf(".");
			String ext = pic.substring(index);
			String basepath = pic.substring(0, index);
			pic = basepath + smallname + ext;
		}
		return pic;
	}

	// 修改保存的文件名称
	private String getNewFileName() {
		// 组成一个新的文件名称
		return "" + System.currentTimeMillis();
	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	public String getNewDir() {
		return newDir;
	}

	public void setNewDir(String newDir) {
		this.newDir = newDir;
	}

	public String getDelPic() {
		return delPic;
	}

	public void setDelPic(String delPic) {
		this.delPic = delPic;
	}
}
