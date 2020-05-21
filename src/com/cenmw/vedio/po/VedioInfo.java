package com.cenmw.vedio.po;

import java.util.Date;

import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberPraise;

/**
 * 视频
 */
public class VedioInfo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 视频类型，0:学生会员创建的视频，1：企业会员创建的视频 2：管理员创建的大讲堂视频
	private Integer cid; // 视频分类id
	private String classname; // 视频分类名称
	private String title; // 视频名称
	private String keyword; // 关键词
	private String description; // 描述
	private String content; // 内容
	private String author; // 作者
	private String source; // 来源
	private Date ptime; // 发布时间
	private String picpath; // 视频代表图
	private Integer sort; // 排序
	private Integer isdel; // 是否删除 1：删除
	private Integer state; // 是否发布 1:发布 0：草稿
	private Date ctime; // 创建时间
	private Integer viewnumber; // 查看次数
	private Integer rcid; // 转载id
	private Integer qx; // 权限 0:所有人可见，1：仅好友可见，2：仅自己可见
	private MemberInfo memberInfo;

	private int praisenumber; // 赞个数
	private MemberPraise memberPraise;
	private int rcnumber; // 被转载次数
	private int commentnumber; // 评论次数
	private String url; // url

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public Integer getViewnumber() {
		return viewnumber;
	}

	public void setViewnumber(Integer viewnumber) {
		this.viewnumber = viewnumber;
	}

	public int getPraisenumber() {
		return praisenumber;
	}

	public void setPraisenumber(int praisenumber) {
		this.praisenumber = praisenumber;
	}

	public MemberPraise getMemberPraise() {
		return memberPraise;
	}

	public void setMemberPraise(MemberPraise memberPraise) {
		this.memberPraise = memberPraise;
	}

	public int getRcnumber() {
		return rcnumber;
	}

	public void setRcnumber(int rcnumber) {
		this.rcnumber = rcnumber;
	}

	public int getCommentnumber() {
		return commentnumber;
	}

	public void setCommentnumber(int commentnumber) {
		this.commentnumber = commentnumber;
	}

	public Integer getRcid() {
		return rcid;
	}

	public void setRcid(Integer rcid) {
		this.rcid = rcid;
	}

	public String getUrl() {
		url = "/membercenter/showvedioinfo?id=" + id;
		return url;
	}

	public Integer getQx() {
		return qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

	public String getQxname() {
		String qxname = "任何人可见";
		if (qx == 0) {
			qxname = "任何人可见";
		} else if (qx == 1) {
			qxname = "仅好友可见";
		} else if (qx == 2) {
			qxname = "仅自己可见";
		}
		return qxname;
	}
}
