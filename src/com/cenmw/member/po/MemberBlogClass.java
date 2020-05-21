package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员日志，文章 分类
 */

public class MemberBlogClass implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer type; //1：日志  2：文章
	private Integer mid; // 会员id
	private String title;  //日志，文章 分类
	private Integer isdel;        //是否删除
	private Integer sort;        //排序
	private Date ctime;    //创建时间
	private MemberInfo memberInfo;

	/** default constructor */
	public MemberBlogClass() {
	}

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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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