package com.cenmw.member.po;

import java.util.Date;

/**
 * 会员52周，书籍中的内容
 */

public class MemberDiary52Content implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer type;  // 0:与家庭有关  1：与自己有关
	private Integer zposition;  // 0:写日志之前，1：写日志之后
	private Integer zbegin; // 开始天数
	private Integer zend;   // 结束天数
	private String content;// 书本中的内容
	private Integer sort;  // 排序  默认都是0，越小越靠前
	private Integer isdel; // 是否删除
	private Date ctime; // 创建时间

	/** default constructor */
	public MemberDiary52Content() {
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getZbegin() {
		return zbegin;
	}

	public void setZbegin(Integer zbegin) {
		this.zbegin = zbegin;
	}

	public Integer getZend() {
		return zend;
	}

	public void setZend(Integer zend) {
		this.zend = zend;
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

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getZposition() {
		return zposition;
	}

	public void setZposition(Integer zposition) {
		this.zposition = zposition;
	}


}