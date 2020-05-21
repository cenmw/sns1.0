package com.tencent.weibo.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import weibo4j.util.BareBonesBrowserLaunch;

import com.tencent.weibo.beans.AccessToken;
import com.tencent.weibo.beans.Account;
import com.tencent.weibo.beans.OAuth;
import com.cenmw.util.JsonUtil;
import com.cenmw.util.StringUtil;
import com.cenmw.util.http.HttpClientUtil;

/**
 * OAuth2.0鉴权
 * 
 * @author LiangJiChao
 * 
 */
public class OAuth2Code {

	/**
	 * 发送获取code的连接地址
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getOpenUrl(String callback)
			throws UnsupportedEncodingException {
		String uri = OAuth.redirect_uri;
		if (StringUtil.notNullStr(callback)) {
			uri += "?callback=" + callback;
		}
		return "https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id="
				+ OAuth.APP_KEY + "&response_type=code&redirect_uri="
				+ URLEncoder.encode(uri, "UTF-8");
	}

	/**
	 * 获取access_token
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public AccessToken getToken(String code, String callback) throws Exception {
		String uri = OAuth.redirect_uri;
		if (StringUtil.notNullStr(callback)) {
			uri += "?callback=" + callback;
		}
		AccessToken at = new AccessToken();
		String result = HttpClientUtil.httpGet(
				"https://open.t.qq.com/cgi-bin/oauth2/access_token",
				"client_id=" + OAuth.APP_KEY + "&client_secret="
						+ OAuth.App_Secret + "&redirect_uri="
						+ URLEncoder.encode(uri, "UTF-8")
						+ "&grant_type=authorization_code&code=" + code);
		System.out.println("result==>" + result);
		String arr[] = result.split("&");
		at.setAccessToken(arr[0].split("=")[1]);
		return at;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 * @param openid
	 * @param clientip
	 * @return
	 * @throws Exception
	 */
	public Account getAccount(String accessToken, String openid, String clientip)
			throws Exception {
		String sendUrl = "https://open.t.qq.com/api/user/info";
		String queryStr = "oauth_consumer_key=" + OAuth.APP_KEY
				+ "&access_token=" + accessToken + "&openid=" + openid
				+ "&clientip=" + clientip
				+ "&oauth_version=2.a&scope=all&format=json";
		String result = HttpClientUtil.httpGet(sendUrl, queryStr);
		Map map = JsonUtil.getMapByJsonstr(result);
		Map dataMap = (Map) map.get("data");
		Account a = new Account();
		a.setIdolnum(dataMap.get("idolnum").toString());
		a.setName(dataMap.get("name").toString());
		a.setNick(dataMap.get("nick").toString());
		a.setHead(dataMap.get("head").toString());
		a.setSex(dataMap.get("sex").toString());// 1-男，2-女，0-未填写
		a.setOpenid(dataMap.get("openid").toString());
		return a;
	}

	public static void main(String args[]) throws Exception {
		OAuth2Code o = new OAuth2Code();
		BareBonesBrowserLaunch.openURL(o.getOpenUrl(""));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		String arr[] = in.split("&");
		String code = arr[0].split("=")[1];
		String openid = arr[1].split("=")[1];
		String tokenCode = o.getToken(code,"").getAccessToken();
		System.out.println("tokenCode==>>" + tokenCode);
		o.getAccount(tokenCode, openid, "");
	}
}
