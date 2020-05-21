package com.cenmw.consult.po;

import java.util.Date;

/**
 * 咨询分类
 */
public class ConsultClass {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 咨询类型，0:学生会员创建的分类，1：企业会员创建的分类 2：管理员创建的分类
	private String title; // 分类名称
	private String keyword; // 关键词
	private String description; // 描述
	private String picpath; // 视频分类代表图
	private Integer isdel; // 是否删除
	private Integer sort; // 排序
	private Date ctime; // 创建时间

	public Integer getId() {
		return id;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getPicpath() {
		if (picpath == null || picpath.isEmpty() || picpath.length() == 0) {
			picpath = "/member/images/common/no_photo.png";
		}
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

}
