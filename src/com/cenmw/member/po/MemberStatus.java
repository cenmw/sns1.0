package com.cenmw.member.po;

import java.util.Date;

import com.cenmw.vedio.po.VedioClass;
import com.cenmw.vedio.po.VedioInfo;

/**
 * 会员动态
 */

public class MemberStatus implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 动态类型 1:会员说说 2:会员日志 3:会员文集 4：会员相册 5:会员视频
	private String typename; // 动态类型名称
	private Integer cid; // 动态对象的ID
	private String classname; // 动态对象的名称
	private Integer isdel; // 是否删除
	private Date ctime; // 发生动态的时间
	private Date ptime; // 最新动态时间，评论及时更新这个时间
	private MemberInfo memberInfo;
	private MemberMood memberMood; // 会员说说
	private MemberDiary memberDiary; // 会员日志，会员文集
	private MemberDiary52 memberDiary52; // 会员52周
	private MemberDiaryXGYC memberDiaryXGYC;  //习惯养成
	private MemberBlog memberBlog; // 会员文集
	private MemberPhoto memberPhoto; // 会员相片
	private VedioInfo vedioInfo; // 会员视频
	private MemberBlogClass memberBlogClass; // 会员日志，会员文集分类
	private MemberPhotoGroup memberPhotoGroup; // 会员相片分类
	private VedioClass vedioClass; // 会员视频分类
	private String title;
	private String content;
	private Integer qx; // 权限 0:所有人可见，1：仅好友可见，2：仅自己可见

	private Integer rcid; // 转载对象id
	private String url; // 动态对象的链接url
	private int praisenumber; // 赞个数
	private MemberPraise memberPraise;
	private int rcnumber; // 被转载次数
	private int commentnumber; // 评论次数

	/** default constructor */
	public MemberStatus() {
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

	public MemberMood getMemberMood() {
		return memberMood;
	}

	public void setMemberMood(MemberMood memberMood) {
		this.memberMood = memberMood;
	}

	public MemberBlog getMemberBlog() {
		return memberBlog;
	}

	public void setMemberBlog(MemberBlog memberBlog) {
		this.memberBlog = memberBlog;
	}

	public MemberPhoto getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(MemberPhoto memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public VedioInfo getVedioInfo() {
		return vedioInfo;
	}

	public void setVedioInfo(VedioInfo vedioInfo) {
		this.vedioInfo = vedioInfo;
	}

	public MemberBlogClass getMemberBlogClass() {
		return memberBlogClass;
	}

	public void setMemberBlogClass(MemberBlogClass memberBlogClass) {
		this.memberBlogClass = memberBlogClass;
	}

	public MemberPhotoGroup getMemberPhotoGroup() {
		return memberPhotoGroup;
	}

	public void setMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		this.memberPhotoGroup = memberPhotoGroup;
	}

	public VedioClass getVedioClass() {
		return vedioClass;
	}

	public void setVedioClass(VedioClass vedioClass) {
		this.vedioClass = vedioClass;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getRcid() {
		return rcid;
	}

	public void setRcid(Integer rcid) {
		this.rcid = rcid;
	}

	public String getUrl() {
		if (type == 1) {
			url = "/membercenter/showmoodinfo?id=" + cid;
		} else if (type == 2) {
			url = "/membercenter/showdiaryinfo?id=" + cid;
		} else if (type == 3) {
			url = "/membercenter/showcontentinfo?id=" + cid;
		} else if (type == 4) {
			url = "/membercenter/showphotoinfo?id=" + cid;
		} else if (type == 5) {
			url = "/membercenter/showvedioinfo?id=" + cid;
		} else if (type == 6) {
			url = "/membercenter/showlearninfo?id=" + cid;
		} else if (type == 7) {
			url = "/membercenter/showzhouinfo?id=" + cid;
		} else if (type == 9) {
			url = "/membercenter/showmyzyfinfo?id=" + cid;
		} else if (type == 10) {
			url = "/membercenter/showxgycinfo?id=" + cid;
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public MemberDiary getMemberDiary() {
		return memberDiary;
	}

	public void setMemberDiary(MemberDiary memberDiary) {
		this.memberDiary = memberDiary;
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
		} else if (qx == 3) {
			qxname = "仅龙爸爸可见";
		}
		return qxname;
	}

	public MemberDiary52 getMemberDiary52() {
		return memberDiary52;
	}

	public void setMemberDiary52(MemberDiary52 memberDiary52) {
		this.memberDiary52 = memberDiary52;
	}

	public MemberDiaryXGYC getMemberDiaryXGYC() {
		return memberDiaryXGYC;
	}

	public void setMemberDiaryXGYC(MemberDiaryXGYC memberDiaryXGYC) {
		this.memberDiaryXGYC = memberDiaryXGYC;
	}

}