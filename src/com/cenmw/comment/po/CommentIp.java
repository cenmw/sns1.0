package com.cenmw.comment.po;

import java.util.Date;

public class CommentIp {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ip;
	private Date ctime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
}
