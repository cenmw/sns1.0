package com.cenmw.util;

import java.io.Serializable;

public class IndexBean implements Serializable {
	private String name;
	private String value;
	private String param;
	private String paramVal;
	private int isor;// 默认为and 1为or
	private boolean search = true;

	public IndexBean() {
	}

	public IndexBean(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public IndexBean(String name, String value, String param) {
		this.name = name;
		this.value = value;
		this.param = param;
	}

	public IndexBean(String name, String value, int isor) {
		this.name = name;
		this.value = value;
		this.isor = isor;
	}

	public IndexBean(String name, String value, String param, String paramVal) {
		super();
		this.name = name;
		this.value = value;
		this.param = param;
		this.paramVal = paramVal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIsor() {
		return isor;
	}

	public void setIsor(int isor) {
		this.isor = isor;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getParamVal() {
		return paramVal;
	}

	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

}
