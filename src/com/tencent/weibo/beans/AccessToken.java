package com.tencent.weibo.beans;

import java.io.Serializable;

public class AccessToken implements Serializable {
	private String accessToken = "";
	private String expiresIn = "";
	private String refreshToken = "";

	public AccessToken() {
	}

	public AccessToken(String accessToken, String expiresIn, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
