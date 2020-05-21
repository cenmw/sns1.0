package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员好友
 */

public class MemberFriend implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer type; //1：企业好友 0：好友
	private Integer cid;   //好友分类
	private String classname; // 好友分类名称
	private Integer mid; // 会员id
	private Integer fid;  //好友id
	private String content;  //好友申请留言
	private Integer isagree;  //是否同意 1：同意
	private Integer isdel;        //是否删除
	private Date ctime;    //申请好友的时间
	private MemberInfo memberInfo;      // 发消息会员
	private MemberInfo rmemberInfo;      // 接收消息的会员
	private MemberStatus rmemberStatus;      // 好友最新动态

	/** default constructor */
	public MemberFriend() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsagree() {
		return isagree;
	}

	public void setIsagree(Integer isagree) {
		this.isagree = isagree;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public MemberInfo getRmemberInfo() {
		return rmemberInfo;
	}

	public void setRmemberInfo(MemberInfo rmemberInfo) {
		this.rmemberInfo = rmemberInfo;
	}

	public MemberStatus getRmemberStatus() {
		return rmemberStatus;
	}

	public void setRmemberStatus(MemberStatus rmemberStatus) {
		this.rmemberStatus = rmemberStatus;
	}
	
}