package com.cenmw.consult.po;

import java.util.Date;

import com.cenmw.member.po.MemberInfo;

/**
 * 回答咨询
 */
public class ConsultReplyInfo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer cid;  //咨询id
	private String content;      //回答内容
	private Integer sort;        //排序
	private Integer isagree;     //是否认同 1：认同 
	private Integer isdel;       //是否删除
	private Date ctime;   //创建时间
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
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getIsagree() {
		return isagree;
	}
	public void setIsagree(Integer isagree) {
		this.isagree = isagree;
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
