package com.cenmw.store.po;

/**
 * 消费设置
 */
public class StoreConfigVo {
	private static final long serialVersionUID = 1L;
	private Integer score1; // 52周为家庭消费
	private Integer score2; // 52周为自己消费

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

}
