package com.cenmw.member.po;

import java.util.Date;

import com.cenmw.util.TemplateUtils;

/**
 * 会员日志，文章
 */

public class MemberBlog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 1：日志 2：文章
	private Integer cid; // 日志，文章分类
	private String classname; // 日志，文章分类
	private String title; // 标题
	private String keyword; // 关键词
	private String description; // 描述
	private String content; // 内容
	private Integer sort; // 排序
	private Integer isdel; // 是否删除
	private Date ctime; // 创建时间
	private Integer viewnumber; // 查看次数
	private Integer rcid; // 转载id
	private Integer rcnumber; // 被转载次数
	private Integer qx; // 权限 0:所有人可见，1：仅好友可见，2：仅自己可见
	private int praisenumber; // 赞个数
	private MemberPraise memberPraise;
	private int commentnumber; // 评论次数
	private MemberInfo memberInfo;
	private String url; // url

	/** default constructor */
	public MemberBlog() {
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
		if (content != null && content.length() > 0) {
			String contentText = TemplateUtils.getText(content);
			description = contentText;
			if (contentText.length() > 160) {
				description = contentText.substring(0, 162) + "...";
			} else {
				description = contentText;
			}
		}
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

	public Integer getRcnumber() {
		return rcnumber;
	}

	public void setRcnumber(Integer rcnumber) {
		this.rcnumber = rcnumber;
	}

	public String getUrl() {
		if (type == 1) {
			url = "/membercenter/showbloginfo?id=" + id;
		} else if (type == 2) {
			url = "/membercenter/showcontentinfo?id=" + id;
		}
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