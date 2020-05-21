package com.cenmw.learn.po;

import java.util.List;

/**
 * 学习课程结果
 */
public class LearnResultVO {
	private static final long serialVersionUID = 1L;
	private String result; // 答题结果
	private Integer whyid; // 原因id
	private List whylist; // 原因列表

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getWhyid() {
		return whyid;
	}

	public void setWhyid(Integer whyid) {
		this.whyid = whyid;
	}

	public List getWhylist() {
		return whylist;
	}

	public void setWhylist(List whylist) {
		this.whylist = whylist;
	}

}
