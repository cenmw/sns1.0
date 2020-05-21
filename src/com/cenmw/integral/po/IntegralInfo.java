package com.cenmw.integral.po;

import java.util.Date;

/**
 * 获取积分
 */
public class IntegralInfo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private String account;
	private Integer type; // 积分的类型 1：注册获取积分；
							// 2：发咨询悬赏积分；3：发表日志获得积分；4：发表文集获得积分；5：发表视频获得积分；6：发表相片获得积分；10:习惯养成
	private String typename;
	private Integer cid; // 积分的类型id
	private Integer score; // 积分的分数
	private Integer isdel; // 是否删除 1：删除
	private Date ctime; // 创建时间
	private Integer sumscore; // 总积分

	public Integer getId() {
		return id;
	}

	public IntegralInfo() {
	}

	/**
	 * 利用bean，赋值，减少其他地方代码。优化代码
	 * 
	 * @param mid
	 * @param type
	 */
	public IntegralInfo(Integer mid, String account, Integer type, Integer cid,
			Integer score) {
		this.mid = mid;
		this.account = account;
		this.type = type;
		if (type == 1) {
			this.typename = "注册获取积分";
		} else if (type == 2) {
			this.typename = "登陆获得积分";
		} else if (type == 3) {
			this.typename = "发表日志获得积分";
		} else if (type == 4) {
			this.typename = "发表文集获得积分";
		} else if (type == 5) {
			this.typename = "发表视频获得积分";
		} else if (type == 6) {
			this.typename = "发表相片获得积分";
		} else if (type == 7) {
			this.typename = "发表52周获得积分";
		}
		this.cid = cid;
		this.score = score;
		this.isdel = 0;
		this.ctime = new Date();
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getSumscore() {
		return sumscore;
	}

	public void setSumscore(Integer sumscore) {
		this.sumscore = sumscore;
	}

}
