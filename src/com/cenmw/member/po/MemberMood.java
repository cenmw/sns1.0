package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员心情、咨询、作业
 */

public class MemberMood implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private String content; // 内容
	private Integer isdel;        //是否删除
	private Date ctime; // 创建时间
	private Integer rcid;   //转载对象id
	private MemberInfo memberInfo;
	private Integer viewnumber;   //查看次数
	
	//2015-12-17 
	private Integer type;   //类型  0:心情   1：作业
	private Integer qx; // 作业权限 0:所有人批改，1：仅好友批改，2：龙爸爸批改
	
	private int praisenumber;  //赞个数
	private MemberPraise memberPraise;
	private int rcnumber;  //被转载次数
	private int commentnumber;  //评论次数

	private String url;    //url
	
	/** default constructor */
	public MemberMood() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public int getPraisenumber() {
		return praisenumber;
	}

	public void setPraisenumber(int praisenumber) {
		this.praisenumber = praisenumber;
	}

	public Integer getRcid() {
		return rcid;
	}

	public void setRcid(Integer rcid) {
		this.rcid = rcid;
	}

	public int getRcnumber() {
		return rcnumber;
	}

	public void setRcnumber(int rcnumber) {
		this.rcnumber = rcnumber;
	}

	public Integer getViewnumber() {
		return viewnumber;
	}

	public void setViewnumber(Integer viewnumber) {
		this.viewnumber = viewnumber;
	}

	public int getCommentnumber() {
		return commentnumber;
	}

	public void setCommentnumber(int commentnumber) {
		this.commentnumber = commentnumber;
	}

	public MemberPraise getMemberPraise() {
		return memberPraise;
	}

	public void setMemberPraise(MemberPraise memberPraise) {
		this.memberPraise = memberPraise;
	}
	
	public String getUrl() {
		url = "/membercenter/showmoodinfo?id="+id;
		return url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getQx() {
		return qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

	public String getQxname() {
		String qxname = "所有人批改";
		if (qx == 0) {
			qxname = "所有人批改";
		} else if (qx == 1) {
			qxname = "仅好友批改";
		} else if (qx == 2) {
			qxname = "龙爸爸批改";
		}
		return qxname;
	}


}