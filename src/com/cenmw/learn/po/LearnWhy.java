package com.cenmw.learn.po;

import java.util.Date;

/**
 * 错误原因
 */
public class LearnWhy {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title; //原因
	private Integer sort; // 排序
	private Integer isdel; // 是否删除 1：删除
	private Date ctime; // 创建时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
