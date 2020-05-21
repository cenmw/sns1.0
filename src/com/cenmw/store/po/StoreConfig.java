package com.cenmw.store.po;

/**
 * 消费设置
 */
public class StoreConfig {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer type; // 消费的类型 1：学习消费  2：测试试卷消费   3:52周为家庭消费   4：52周为自己消费 
	private Integer money;  //金额
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

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}


}
