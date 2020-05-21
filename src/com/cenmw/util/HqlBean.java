package com.cenmw.util;

import java.io.Serializable;

public class HqlBean implements Serializable {
	private Object value;
	private String op;// = like
	private String split;// and or
	private String type;// String Integer Date
	private String param;// 设置链接参数
	private int ishql;// 1 不加入hql语句
	private int isparam;// 1 不加入参数
	private String replacekey;// 替换的hql key
	private String paramVal;// 设置参数值

	public HqlBean(Object value, String type) {
		this.value = value;
		this.type = type;
		this.op = "=";
	}

	/**
	 * 
	 * @param value
	 *            value
	 * @param op
	 *            = like
	 * @param split
	 *            and or
	 * @param type
	 *            String Integer Date
	 */
	public HqlBean(Object value, String op, String split, String type) {
		this.value = value;
		this.op = op;
		this.split = split;
		this.type = type;
	}

	/**
	 * 
	 * @param value
	 * @param op
	 *            = like
	 * @param split
	 *            and or
	 * @param type
	 *            String Integer Date
	 * @param param
	 *            设置链接参数
	 */
	public HqlBean(Object value, String op, String split, String type,
			String param) {
		this.value = value;
		this.op = op;
		this.split = split;
		this.type = type;
		this.param = param;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getIshql() {
		return ishql;
	}

	public void setIshql(int ishql) {
		this.ishql = ishql;
	}

	public int getIsparam() {
		return isparam;
	}

	public void setIsparam(int isparam) {
		this.isparam = isparam;
	}

	public String getReplacekey() {
		return replacekey;
	}

	public void setReplacekey(String replacekey) {
		this.replacekey = replacekey;
	}

	public String getParamVal() {
		return paramVal;
	}

	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}

}
