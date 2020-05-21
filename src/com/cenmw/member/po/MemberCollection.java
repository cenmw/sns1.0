package com.cenmw.member.po;

import java.util.Date;
/**
 * 会员 收藏
 */

public class MemberCollection implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type;   //收藏类型  3:文章收藏  5:视频收藏  6:学习收藏
	private String typename;   //收藏类型
	private Integer cid;    //收藏对象的ID
	private String classname;    //收藏对象名称
	private Integer isdel;        //是否删除
	private Date ctime;     //收藏的时间
	private MemberInfo memberInfo;
	private String title;    //收藏对象名称
	private String description;  //收藏对象描述
	private Date octime;   //收藏对象创建时间
	private String oclassname;   //收藏对象创建时间
	private String opicpath;   //收藏对象图片
	private Integer viewnumber;   //查看次数
	private String url;   //查看原文链接
	
	/** default constructor */
	public MemberCollection() {
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

	public String getTypename() {
		if(type==3){
			typename="文章收藏";
		}else if(type==5){
			typename="视频收藏";
		}else if(type==6){
			typename="学习收藏";
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

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOctime() {
		return octime;
	}

	public void setOctime(Date octime) {
		this.octime = octime;
	}

	public String getOclassname() {
		return oclassname;
	}

	public void setOclassname(String oclassname) {
		this.oclassname = oclassname;
	}

	public String getOpicpath() {
		return opicpath;
	}

	public void setOpicpath(String opicpath) {
		this.opicpath = opicpath;
	}

	public Integer getViewnumber() {
		return viewnumber;
	}

	public void setViewnumber(Integer viewnumber) {
		this.viewnumber = viewnumber;
	}
	
	public String getUrl() {
		if(type==2){
			url = "/membercenter/showbloginfo?id="+cid;
		}else if(type==3){
			url = "/membercenter/showcontentinfo?id="+cid;
		}else if(type==4){
			url = "/membercenter/showphotoinfo?id="+cid;
		}else if(type==5){
			url = "/membercenter/showvedioinfo?id="+cid;
		}else if(type==6){
			url = "/membercenter/showlearninfo?id="+cid;
		}
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}