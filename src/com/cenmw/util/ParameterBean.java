package com.cenmw.util;

import java.util.Date;

public class ParameterBean {
	private String name;
	private String op;
	private Date date;
	private String type;

	public ParameterBean() {
	}

	public ParameterBean(String name, String op) {
		this.name = name;
		this.op = op;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
