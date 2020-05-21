package com.cenmw.topic.po;

/**
 * 生活建议记录
 */
public class TopicLifeAdviceLog {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer tlid;  // 测试记录id
	private Integer ttaid; // 生活建议id
	private Integer number; // 本次学习记录同一个原因出现错误的次数
	private TopicLifeAdvice topicLifeAdvice;

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

	public Integer getTtaid() {
		return ttaid;
	}

	public void setTtaid(Integer ttaid) {
		this.ttaid = ttaid;
	}

	public TopicLifeAdvice getTopicLifeAdvice() {
		return topicLifeAdvice;
	}

	public void setTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		this.topicLifeAdvice = topicLifeAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
