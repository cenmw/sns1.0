package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员记录
 */

public class MemberLog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 记录的类型 1：登陆记录  2：退出记录  3：访问空间记录，4：访问日志记录 ，5：访问文章记录，6：访问空间记录。。
	private String typename; // 记录的类型 1：登陆记录  2：访问空间记录，3：访问日志记录 ，4：访问文章记录，5：访问空间记录。。
	private Integer cid; // 记录的类型id
	private String classname; // 记录的类型名称
	private Integer isdel;        //是否删除
	private Date ctime; // 记录时间
	private MemberInfo memberInfo;

	/** default constructor */
	public MemberLog() {
	}
	
	/**
	 * 利用bean，赋值，减少其他地方代码。优化代码
	 * @param mid
	 * @param type
	 */
	public MemberLog(Integer mid, Integer type,Integer cid,String classname) {
		this.mid = mid;
		this.type = type;
		if(type ==1){
			this.typename = "登陆";
		}else if(type ==2){
			this.typename = "退出";
		}else if(type ==3){
			this.typename = "访问空间";
		}
		this.cid = cid;
		this.classname = classname;
		this.isdel = 0;
		this.ctime = new Date();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	
}