package com.cenmw.member.po;

import java.util.Date;

// default package

/**
 * MemberLLJL entity. @author MyEclipse Persistence Tools 浏览记录
 */

public class MemberLLJL implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid;
	private String title;
	private String url;
	private Integer isdel;
	private Date ctime; // 创建时间
	

	/** default constructor */
	public MemberLLJL() {
	}

	/** minimal constructor */
	public MemberLLJL(Integer mid) {
		this.mid = mid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}