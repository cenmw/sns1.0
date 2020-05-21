package com.cenmw.learn.po;

/**
 * 错误原因记录
 */
public class LearnWhyLog {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer llid; // 学习记录id
	private Integer whyid; // 原因id
	private Integer number; // 本次学习记录同一个原因出现错误的次数
	private LearnWhy learnWhy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLlid() {
		return llid;
	}

	public void setLlid(Integer llid) {
		this.llid = llid;
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
