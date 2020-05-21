package com.cenmw.integral.po;

/**
 * 积分设置
 */
public class IntegralConfig {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer type; // 积分的类型 1：注册获取积分； 2: 每天等获取积分，每天最多五次登陆得积分 3：发咨询悬赏积分；
						  //4：回复咨询获得积分；5：完成任务获得积分；6:发表日志获得积分；
						  //7：发表文集获得积分；8：发表视频获得积分；9:发表相片获得积分。
	private Integer score;  //积分
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}


}
