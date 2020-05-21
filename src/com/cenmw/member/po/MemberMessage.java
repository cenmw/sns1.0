package com.cenmw.member.po;

import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;

/**
 * 会员 消息
 */

public class MemberMessage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid;      // 发消息会员id
	private Integer reviceid; //接收消息的会员id
	private String content;   //发送消息内容
	private Integer isopen;        //是否查阅
	private Integer isdel;        //是否删除
	private Date ctime;     //发消息的时间
	private MemberInfo memberInfo;      // 发消息会员
	private MemberInfo rmemberInfo;      // 接收消息的会员
	private String replacecontent;   //发送消息内容
	
	/** default constructor */
	public MemberMessage() {
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

	public Integer getReviceid() {
		return reviceid;
	}

	public void setReviceid(Integer reviceid) {
		this.reviceid = reviceid;
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

	public MemberInfo getRmemberInfo() {
		return rmemberInfo;
	}

	public void setRmemberInfo(MemberInfo rmemberInfo) {
		this.rmemberInfo = rmemberInfo;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public String getReplacecontent() {
		if(StringUtils.isNotBlank(content)){
			replacecontent = content.replaceAll("/usercenter/myzhouadd.html", "/membercenter/zhouinfo1");
		}
		return replacecontent;
	}

	public void setReplacecontent(String replacecontent) {
		this.replacecontent = replacecontent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}