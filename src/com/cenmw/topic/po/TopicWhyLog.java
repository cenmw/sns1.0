package com.cenmw.topic.po;

import com.cenmw.learn.po.LearnWhy;

/**
 * 错误原因记录
 */
public class TopicWhyLog {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer tlid; // 测试记录id
	private Integer whyid; // 原因id
	private Integer number; // 本次学习记录同一个原因出现错误的次数
	private LearnWhy learnWhy;

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

	public Integer getWhyid() {
		return whyid;
	}

	public void setWhyid(Integer whyid) {
		this.whyid = whyid;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LearnWhy getLearnWhy() {
		return learnWhy;
	}

	public void setLearnWhy(LearnWhy learnWhy) {
		this.learnWhy = learnWhy;
	}

}
