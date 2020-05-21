package com.cenmw.topic.po;

import com.cenmw.learn.po.LearnClass;

/**
 * 课程推荐记录
 */
public class TopicLearnClassLog {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer tlid; // 测试记录id
	private Integer lcid; // 课程推荐id
	private Integer number; // 本次学习记录同一个原因出现错误的次数
	private LearnClass learnClass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTlid() {
		return tlid;
	}

	public void setTlid(Integer tlid) {
		this.tlid = tlid;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LearnClass getLearnClass() {
		return learnClass;
	}

	public void setLearnClass(LearnClass learnClass) {
		this.learnClass = learnClass;
	}

	public Integer getLcid() {
		return lcid;
	}

	public void setLcid(Integer lcid) {
		this.lcid = lcid;
	}

}
