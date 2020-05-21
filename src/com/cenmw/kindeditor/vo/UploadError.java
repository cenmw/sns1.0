package com.cenmw.kindeditor.vo;

public class UploadError {
	private Integer error;
	private String message;

	public UploadError(Integer error, String message) {
		super();
		this.error = error;
		this.message = message;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
