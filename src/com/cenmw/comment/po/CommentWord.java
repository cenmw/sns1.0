package com.cenmw.comment.po;

import java.util.Date;

public class CommentWord {
	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String words;  //屏蔽字
	private Date ctime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
}
