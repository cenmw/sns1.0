package com.cenmw.util;

import java.io.Serializable;

public class ProcBean implements Serializable {
	private String sqlKey;
	private String paramKey;
	private Object value;
	private String valueType;// 值类型 String Integer Date
	private String andOr;// 条件关系 and 或 or
	private String operate;// 关系比较符 = like > < >= <=
	private boolean sql = true;
	private boolean param = true;

	public ProcBean(String sqlKey, String paramKey, Object value,
			String valueType, String andOr, String operate) {
		this.sqlKey = sqlKey;
		this.paramKey = paramKey;
		this.value = value;
		this.valueType = valueType;
		this.andOr = andOr;
		this.operate = operate;
	}

	public ProcBean(String sqlKey, String paramKey, Object value,
			String valueType, String andOr, String operate, boolean sql,
			boolean param) {
		this.sqlKey = sqlKey;
		this.paramKey = paramKey;
		this.value = value;
		this.valueType = valueType;
		this.andOr = andOr;
		this.operate = operate;
		this.sql = sql;
		this.param = param;
	}

	public String getSqlKey() {
		return sqlKey;
	}

	public void setSqlKey(String sqlKey) {
		this.sqlKey = sqlKey;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getAndOr() {
		return andOr;
	}

	public void setAndOr(String andOr) {
		this.andOr = andOr;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public boolean isSql() {
		return sql;
	}

	public void setSql(boolean sql) {
		this.sql = sql;
	}

	public boolean isParam() {
		return param;
	}

	public void setParam(boolean param) {
		this.param = param;
	}

}
