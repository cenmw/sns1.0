package com.cenmw.util.http;

public class HttpParameter implements java.io.Serializable,
		Comparable<HttpParameter> {
	private String name;
	private String value;

	public HttpParameter(String name, String value) {
		this.name = name;
		this.value = value;
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

	public int compareTo(HttpParameter param) {
		int compared;
		compared = this.name.compareTo(param.getName());
		if (0 == compared) {
			compared = this.value.compareTo(param.getValue());
		}
		return compared;
	}

}
