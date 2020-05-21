package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员好友分组
 */

public class MemberFriendClass implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer type; //1：企业好友 0：好友
	private Integer mid; // 会员id
	private String title;  //好友分组
	private Integer sort;        //排序
	private Integer isdel;        //是否删除
	private Date ctime;    //创建时间
	private MemberInfo memberInfo;      // 会员

	/** default constructor */
	public MemberFriendClass() {
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