package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员举报
 */
public class MemberReport {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 举报的类型
	private String typename; // 举报的类型名称
	private Integer cid; // 举报类型的id
	private String classname; // 举报对象名称
	private Integer rid; // 被举报的会员id
	private Integer state; // 0:举报申请，1：申请通过，对会员锁定，2：释放会员
	private Integer isdel; // 是否删除
	private Date starttime; // 拘留开始时间
	private Date endtime; // 拘留结束时间
	private Date sftime; // 释放时间
	private Date ctime; // 创建时间
	private String content; // 内容
	private MemberInfo memberInfo; // 举报会员
	private MemberInfo rmemberInfo; // 被举报的会员
	private String url; // 查看原文链接
	private String curl; // 查看原文链接

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

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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

	public MemberInfo getRmemberInfo() {
		return rmemberInfo;
	}

	public void setRmemberInfo(MemberInfo rmemberInfo) {
		this.rmemberInfo = rmemberInfo;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Date getSftime() {
		return sftime;
	}

	public void setSftime(Date sftime) {
		this.sftime = sftime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		if (type == 2) {
			url = "/manager/memberblog/info?id=" + cid;
		} else if (type == 3) {
			url = "/manager/membercontent/info?id=" + cid;
		} else if (type == 4) {
			url = "/manager/memberphoto/info?id=" + cid;
		} else if (type == 5) {
			url = "/manager/membervedio/info?id=" + cid;
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCurl() {
		if (type == 2) {
			curl = "/membercenter/showbloginfo?id=" + cid;
		} else if (type == 3) {
			curl = "/membercenter/showcontentinfo?id=" + cid;
		} else if (type == 4) {
			curl = "/membercenter/showphotoinfo?id=" + cid;
		} else if (type == 5) {
			curl = "/membercenter/showvedioinfo?id=" + cid;
		}
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

}
