package com.cenmw.topic.po;

import java.util.List;

/**
 * 学习课程结果
 */
public class TopicResultVO {
	private static final long serialVersionUID = 1L;
	private String result; // 答题结果
	private Integer whyid; // 原因id
	private List whylist;  // 原因列表
	private Integer lcid;  // 课程推荐分类id
	private List learnclasslist;  // 课程分类列表
	private Integer laid;  // 生活建议id
	private List lifeadvicelist;  // 生活建议列表

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

	public Integer getLcid() {
		return lcid;
	}

	public void setLcid(Integer lcid) {
		this.lcid = lcid;
	}

	public List getLearnclasslist() {
		return learnclasslist;
	}

	public void setLearnclasslist(List learnclasslist) {
		this.learnclasslist = learnclasslist;
	}

	public Integer getLaid() {
		return laid;
	}

	public void setLaid(Integer laid) {
		this.laid = laid;
	}

	public List getLifeadvicelist() {
		return lifeadvicelist;
	}

	public void setLifeadvicelist(List lifeadvicelist) {
		this.lifeadvicelist = lifeadvicelist;
	}

}
