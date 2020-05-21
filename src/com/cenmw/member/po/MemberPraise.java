package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员赞
 */
public class MemberPraise {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 赞的类型 1:会员说说  2:会员日志  3:会员文集 4：会员相册 5:会员视频
	private String typename; // 赞的类型
	private Integer cid; // 赞类型的id
	private String classname; // 赞的类型名称
	private Integer isdel;        //是否删除
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
		if(type==1){
			typename="会员说说";
		}else if(type==2){
			typename="会员日记";
		}else if(type==3){
			typename="文章";
		}else if(type==4){
			typename="会员相册";
		}else if(type==5){
			typename="视频";
		}else if(type==6){
			typename="学习中心";
		}
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
