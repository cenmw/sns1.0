package com.cenmw.member.po;

import java.util.Date;

import com.cenmw.learn.po.LearnInfo;
import com.cenmw.topic.po.TopicInfo;

/**
 * 会员充值交易记录
 */

public class MemberStore implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer type; // 0：充值   1：学习消费  2：测试试卷消费   3:52周为自己消费    4：52周为家庭消费
	private String code; // 充值编号 20140110-2088002315459483-1390243202
	private Integer tid; // 消费 测试id 目前就只有测试需要交费
	private Double price; // 金额 消费为负数，充值为正数
	private Integer state; // 0:待确认 1：成功
	private Integer isdel; // 是否删除
	private Date ctime; // 交易时间
	private String account; // 真实姓名 或者 机构名称
	private String email;// 邮箱
	private LearnInfo learnInfo;
	private TopicInfo topicInfo;
	private MemberInfo memberInfo;
	private String title;

	/** default constructor */
	public MemberStore() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public TopicInfo getTopicInfo() {
		return topicInfo;
	}

	public void setTopicInfo(TopicInfo topicInfo) {
		this.topicInfo = topicInfo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public LearnInfo getLearnInfo() {
		return learnInfo;
	}

	public void setLearnInfo(LearnInfo learnInfo) {
		this.learnInfo = learnInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}