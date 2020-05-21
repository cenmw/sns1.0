package com.cenmw.kindeditor.vo;

import java.util.List;

public class ImageDir {
	private String moveup_dir_path;
	private String current_dir_path;
	private String current_url;
	private Integer total_count;
	private List<ImageFile> file_list;

	public ImageDir(String moveup_dir_path, String current_dir_path,
			String current_url, Integer total_count, List<ImageFile> file_list) {
		super();
		this.moveup_dir_path = moveup_dir_path;
		this.current_dir_path = current_dir_path;
		this.current_url = current_url;
		this.total_count = total_count;
		this.file_list = file_list;
	}

	public String getMoveup_dir_path() {
		return moveup_dir_path;
	}

	public void setMoveup_dir_path(String moveup_dir_path) {
		this.moveup_dir_path = moveup_dir_path;
	}

	public String getCurrent_dir_path() {
		return current_dir_path;
	}

	public void setCurrent_dir_path(String current_dir_path) {
		this.current_dir_path = current_dir_path;
	}

	public String getCurrent_url() {
		return current_url;
	}

	public void setCurrent_url(String current_url) {
		this.current_url = current_url;
	}

	public Integer getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}

	public List<ImageFile> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<ImageFile> file_list) {
		this.file_list = file_list;
	}

}
