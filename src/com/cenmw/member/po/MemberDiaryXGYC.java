package com.cenmw.member.po;

import java.util.Date;

/**
 * 习惯养成
 */

public class MemberDiaryXGYC implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private String ptime;  // 时间
	private Integer day; //第几天
	private Integer type;  //0:与孩子有关  1：与自己有关
	private String hzbx;  // 孩子的表现是什么？
	private String fqbx;  // 父亲的表现是什么？
	private String mqbx;  // 母亲的表现是什么？
	private String wdgs;  // 我的感受是什么？
	private Integer pdxg1; // 品德习惯
	private Integer pdxg2; // 品德习惯
	private Integer pdxg3; // 品德习惯
	private Integer pdxg4; // 品德习惯
	private Integer pdxg5; // 品德习惯
	private Integer pdxg6; // 品德习惯
	private Integer pdxg7; // 品德习惯
	private Integer pdxg8; // 品德习惯
	private Integer shxg1; // 生活习惯
	private Integer shxg2; // 生活习惯
	private Integer shxg3; // 生活习惯
	private Integer shxg4; // 生活习惯
	private Integer shxg5; // 生活习惯
	private Integer shxg6; // 生活习惯
	private Integer shxg7; // 生活习惯
	private Integer shxg8; // 生活习惯
	private Integer xxxg1; // 学习习惯
	private Integer xxxg2; // 学习习惯
	private Integer xxxg3; // 学习习惯
	private Integer xxxg4; // 学习习惯
	private Integer xxxg5; // 学习习惯
	private Integer xxxg6; // 学习习惯
	private Integer xxxg7; // 学习习惯
	private Integer xxxg8; // 学习习惯
	private Integer dsydxg1; // 读书与运动
	private Integer dsydxg2; // 读书与运动
	private Integer dsydxg3; // 读书与运动
	private Integer dsydxg4; // 读书与运动
	private Integer dsydxg5; // 读书与运动
	private Integer dsydxg6; // 读书与运动
	private Integer dsydxg7; // 读书与运动
	private Integer dsydxg8; // 读书与运动
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
	private int sumnumber; // 评分总数


	/** default constructor */
	public MemberDiaryXGYC() {
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

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getHzbx() {
		return hzbx;
	}

	public void setHzbx(String hzbx) {
		this.hzbx = hzbx;
	}

	public String getFqbx() {
		return fqbx;
	}

	public void setFqbx(String fqbx) {
		this.fqbx = fqbx;
	}

	public String getMqbx() {
		return mqbx;
	}

	public void setMqbx(String mqbx) {
		this.mqbx = mqbx;
	}

	public String getWdgs() {
		return wdgs;
	}

	public void setWdgs(String wdgs) {
		this.wdgs = wdgs;
	}

	public Integer getPdxg1() {
		return pdxg1;
	}

	public void setPdxg1(Integer pdxg1) {
		this.pdxg1 = pdxg1;
	}

	public Integer getPdxg2() {
		return pdxg2;
	}

	public void setPdxg2(Integer pdxg2) {
		this.pdxg2 = pdxg2;
	}

	public Integer getPdxg3() {
		return pdxg3;
	}

	public void setPdxg3(Integer pdxg3) {
		this.pdxg3 = pdxg3;
	}

	public Integer getPdxg4() {
		return pdxg4;
	}

	public void setPdxg4(Integer pdxg4) {
		this.pdxg4 = pdxg4;
	}

	public Integer getPdxg5() {
		return pdxg5;
	}

	public void setPdxg5(Integer pdxg5) {
		this.pdxg5 = pdxg5;
	}

	public Integer getPdxg6() {
		return pdxg6;
	}

	public void setPdxg6(Integer pdxg6) {
		this.pdxg6 = pdxg6;
	}

	public Integer getPdxg7() {
		return pdxg7;
	}

	public void setPdxg7(Integer pdxg7) {
		this.pdxg7 = pdxg7;
	}

	public Integer getPdxg8() {
		return pdxg8;
	}

	public void setPdxg8(Integer pdxg8) {
		this.pdxg8 = pdxg8;
	}

	public Integer getShxg1() {
		return shxg1;
	}

	public void setShxg1(Integer shxg1) {
		this.shxg1 = shxg1;
	}

	public Integer getShxg2() {
		return shxg2;
	}

	public void setShxg2(Integer shxg2) {
		this.shxg2 = shxg2;
	}

	public Integer getShxg3() {
		return shxg3;
	}

	public void setShxg3(Integer shxg3) {
		this.shxg3 = shxg3;
	}

	public Integer getShxg4() {
		return shxg4;
	}

	public void setShxg4(Integer shxg4) {
		this.shxg4 = shxg4;
	}

	public Integer getShxg5() {
		return shxg5;
	}

	public void setShxg5(Integer shxg5) {
		this.shxg5 = shxg5;
	}

	public Integer getShxg6() {
		return shxg6;
	}

	public void setShxg6(Integer shxg6) {
		this.shxg6 = shxg6;
	}

	public Integer getShxg7() {
		return shxg7;
	}

	public void setShxg7(Integer shxg7) {
		this.shxg7 = shxg7;
	}

	public Integer getShxg8() {
		return shxg8;
	}

	public void setShxg8(Integer shxg8) {
		this.shxg8 = shxg8;
	}

	public Integer getXxxg1() {
		return xxxg1;
	}

	public void setXxxg1(Integer xxxg1) {
		this.xxxg1 = xxxg1;
	}

	public Integer getXxxg2() {
		return xxxg2;
	}

	public void setXxxg2(Integer xxxg2) {
		this.xxxg2 = xxxg2;
	}

	public Integer getXxxg3() {
		return xxxg3;
	}

	public void setXxxg3(Integer xxxg3) {
		this.xxxg3 = xxxg3;
	}

	public Integer getXxxg4() {
		return xxxg4;
	}

	public void setXxxg4(Integer xxxg4) {
		this.xxxg4 = xxxg4;
	}

	public Integer getXxxg5() {
		return xxxg5;
	}

	public void setXxxg5(Integer xxxg5) {
		this.xxxg5 = xxxg5;
	}

	public Integer getXxxg6() {
		return xxxg6;
	}

	public void setXxxg6(Integer xxxg6) {
		this.xxxg6 = xxxg6;
	}

	public Integer getXxxg7() {
		return xxxg7;
	}

	public void setXxxg7(Integer xxxg7) {
		this.xxxg7 = xxxg7;
	}

	public Integer getXxxg8() {
		return xxxg8;
	}

	public void setXxxg8(Integer xxxg8) {
		this.xxxg8 = xxxg8;
	}

	public Integer getDsydxg1() {
		return dsydxg1;
	}

	public void setDsydxg1(Integer dsydxg1) {
		this.dsydxg1 = dsydxg1;
	}

	public Integer getDsydxg2() {
		return dsydxg2;
	}

	public void setDsydxg2(Integer dsydxg2) {
		this.dsydxg2 = dsydxg2;
	}

	public Integer getDsydxg3() {
		return dsydxg3;
	}

	public void setDsydxg3(Integer dsydxg3) {
		this.dsydxg3 = dsydxg3;
	}

	public Integer getDsydxg4() {
		return dsydxg4;
	}

	public void setDsydxg4(Integer dsydxg4) {
		this.dsydxg4 = dsydxg4;
	}

	public Integer getDsydxg5() {
		return dsydxg5;
	}

	public void setDsydxg5(Integer dsydxg5) {
		this.dsydxg5 = dsydxg5;
	}

	public Integer getDsydxg6() {
		return dsydxg6;
	}

	public void setDsydxg6(Integer dsydxg6) {
		this.dsydxg6 = dsydxg6;
	}

	public Integer getDsydxg7() {
		return dsydxg7;
	}

	public void setDsydxg7(Integer dsydxg7) {
		this.dsydxg7 = dsydxg7;
	}

	public Integer getDsydxg8() {
		return dsydxg8;
	}

	public void setDsydxg8(Integer dsydxg8) {
		this.dsydxg8 = dsydxg8;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public void setSumnumber(int sumnumber) {
		this.sumnumber = sumnumber;
	}

	public int getSumnumber() {
		int sumnumber = 0;
		sumnumber +=pdxg1;
		sumnumber +=pdxg2;
		sumnumber +=pdxg3;
		sumnumber +=pdxg4;
		sumnumber +=pdxg5;
		sumnumber +=pdxg6;
		sumnumber +=pdxg7;
		sumnumber +=pdxg8;
		sumnumber +=shxg1;
		sumnumber +=shxg2;
		sumnumber +=shxg3;
		sumnumber +=shxg4;
		sumnumber +=shxg5;
		sumnumber +=shxg6;
		sumnumber +=shxg7;
		sumnumber +=shxg8;
		sumnumber +=xxxg1;
		sumnumber +=xxxg2;
		sumnumber +=xxxg3;
		sumnumber +=xxxg4;
		sumnumber +=xxxg5;
		sumnumber +=xxxg6;
		sumnumber +=xxxg7;
		sumnumber +=xxxg8;
		sumnumber +=dsydxg1;
		sumnumber +=dsydxg2;
		sumnumber +=dsydxg3;
		sumnumber +=dsydxg4;
		sumnumber +=dsydxg5;
		sumnumber +=dsydxg6;
		sumnumber +=dsydxg7;
		sumnumber +=dsydxg8;
		return sumnumber;
	}

}