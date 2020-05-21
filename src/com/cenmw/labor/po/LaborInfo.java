package com.cenmw.labor.po;

import java.util.Date;

import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.DateUtil;

/**
 * 活动
 */
public class LaborInfo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 企业会员id
	private Integer cid; // 活动分类id
	private String classname; // 活动分类名称
	private String title; // 活动名称
	private String keyword; // 关键词
	private String description; // 描述
	private String content; // 内容
	private Integer score; // 积分的分数
	private Integer isdel; // 是否删除
	private Integer sort; // 排序
	private Integer state; // 是否发布 1:审核通过 0：待审核 2：审核不通过 3：关闭
	private Date ctime; // 创建时间
	private Date starttime; // 开始时间
	private Date endtime; // 结束时间
	private String propaganda; // 宣传内容
	private Integer replycount; // 参加人数
	private Integer viewnumber; // 查看次数
	private MemberInfo memberInfo;
	private Integer isend; // 判断活动是否结束
	private String rids = ""; // 群发报名会员id串

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getPropaganda() {
		return propaganda;
	}

	public void setPropaganda(String propaganda) {
		this.propaganda = propaganda;
	}

	public Integer getReplycount() {
		return replycount;
	}

	public void setReplycount(Integer replycount) {
		this.replycount = replycount;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public Integer getViewnumber() {
		return viewnumber;
	}

	public void setViewnumber(Integer viewnumber) {
		this.viewnumber = viewnumber;
	}

	public Integer getIsend() {
		String begin = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd");
		isend = 0;
		if (endtime != null) {
			String end = DateUtil.getFormatDate(endtime, "yyyy-MM-dd");
			int days = DateUtil.countDays(begin, end);
			if (days > 0 || begin.equals(end)) {
				isend = 1;
			}
		} else {
			isend = 0;
		}
		return isend;
	}
	
	public String getStatename() {
		if(state == 0 ){
			return "待审核";
		}else if(state == 1 ){
			return "审核通过";
		}else if(state == 2 ){
			return "审核不通过";
		}else if(state == 3 ){
			return "关闭";
		}
		return "待审核";
	}
	
	public String getGqname() {
		int gq = getIsend();
		if(gq == 1 ){
			return "没过期";
		}else{
			return "已过期";
		}
	}

	public String getCode() {
		return ""+(100000+id);
	}

	public String getRids() {
		return rids;
	}

	public void setRids(String rids) {
		this.rids = rids;
	}
	
}
