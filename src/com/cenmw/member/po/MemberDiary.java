package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员日记
 */

public class MemberDiary implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Date ptime;  // 日记时间
	private String fssq;  // 发生的事情是什么？
	private String hzbx;  // 孩子的表现是什么？
	private String bxjj;  // 表现积极的地方是什么？
	private String fzkj;  // 发展空间是什么？
	private String hzxy;  // 孩子需要的是什么？
	private String xymz;  // 您需要满足的是什么？
	private String wdgs;  // 我的感受是什么？
	private String nxxq;  // 我此时的内心需求是什么？
	private Integer isdel; // 是否删除
	private Date ctime; // 创建时间
	private Integer viewnumber; // 查看次数
	private Integer rcid; // 转载id
	private Integer rcnumber; // 被转载次数
	private Integer qx;    //权限 0:所有人可见，1：仅好友可见，2：仅自己可见
	private int praisenumber; // 赞个数
	private MemberPraise memberPraise;
	private int commentnumber; // 评论次数
	private MemberInfo memberInfo;
	private String url;    //url
	private Integer type;  //0:与孩子有关  1：与自己有关  2：吾日省身

	/** default constructor */
	public MemberDiary() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getQx() {
		return qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public String getFssq() {
		return fssq;
	}

	public void setFssq(String fssq) {
		this.fssq = fssq;
	}

	public String getHzbx() {
		return hzbx;
	}

	public void setHzbx(String hzbx) {
		this.hzbx = hzbx;
	}

	public String getBxjj() {
		return bxjj;
	}

	public void setBxjj(String bxjj) {
		this.bxjj = bxjj;
	}

	public String getFzkj() {
		return fzkj;
	}

	public void setFzkj(String fzkj) {
		this.fzkj = fzkj;
	}

	public String getWdgs() {
		return wdgs;
	}

	public void setWdgs(String wdgs) {
		this.wdgs = wdgs;
	}

	public String getNxxq() {
		return nxxq;
	}

	public void setNxxq(String nxxq) {
		this.nxxq = nxxq;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
	public String getQxname() {
		String qxname="任何人可见";
		if(qx == 0){
			qxname="任何人可见";
		}else if(qx == 1){
			qxname="仅好友可见";
		}else if(qx == 2){
			qxname="仅自己可见";
		}
		return qxname;
	}

	public String getHzxy() {
		return hzxy;
	}

	public void setHzxy(String hzxy) {
		this.hzxy = hzxy;
	}

	public String getXymz() {
		return xymz;
	}

	public void setXymz(String xymz) {
		this.xymz = xymz;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}