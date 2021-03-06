package com.cenmw.learn.po;

import java.util.Date;

import com.cenmw.member.po.MemberInfo;

/**
 * 视频分类
 */
public class LearnClass {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private String title; // 分类名称
	private String keyword; // 关键词
	private String description; // 描述
	private String picpath; // 视频分类代表图
	private Integer sort; // 排序
	private Integer isdel; // 是否删除 1：删除
	private Date ctime; // 创建时间
	private MemberInfo memberInfo;

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

	public String getPicpath() {
		if (picpath == null || picpath.isEmpty() || picpath.length() == 0) {
			picpath = "/member/images/common/no_photo.png";
		}
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

}
