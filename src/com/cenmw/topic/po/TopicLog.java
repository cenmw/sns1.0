package com.cenmw.topic.po;

import java.util.Date;

/**
 * 测试记录信息
 */
public class TopicLog {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 测试的会员id
	private Integer tid; // 测试课程id
	private String result; // 答题结果
	private Integer cnumber; // 正确个数
	private Integer enumber; // 错误个数
	private Integer correct; // 正确率
	private Integer isdel; // 是否删除 1：删除
	private Date ctime; // 创建时间

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

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getCnumber() {
		return cnumber;
	}

	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}

	public Integer getEnumber() {
		return enumber;
	}

	public void setEnumber(Integer enumber) {
		this.enumber = enumber;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
