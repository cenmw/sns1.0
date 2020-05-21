package com.cenmw.extapi.sina.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.cenmw.util.ConstantUtils;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import com.cenmw.extapi.base.ExtapiBaseAction;
import com.cenmw.integral.front.service.IntegralConfigFrontService;
import com.cenmw.integral.front.service.IntegralInfoFrontService;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.member.center.service.MemberLogCenterService;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberLog;
import com.cenmw.util.StringUtil;

public class LoginHandleAction extends ExtapiBaseAction {
	private MemberInfoFrontService memberInfoFrontService;
	private MemberLogCenterService memberLogCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private IntegralConfigFrontService integralConfigFrontService;
	private String code;
	private String openUrl;
	private String accessToken;
	private String uid;

	private String account;
	private String password;
	private String email;
	private String avatar = "/member/images/common/avatar.gif";

	private String callback;

	private final String sinaUserSessionKey = "sinaUserSessionKey";

	/**
	 * 
	 * 获取新浪token
	 * 
	 * @return
	 * @throws WeiboException
	 * @throws UnsupportedEncodingException
	 */
	public String index() throws WeiboException, UnsupportedEncodingException {
		Oauth oauth = new Oauth();
		// 以新浪登录方式的连接
		String uri = WeiboConfig.getValue("redirect_URI").trim();
		if (StringUtil.notNullStr(callback)) {
			uri += "?callback=" + callback;
		}
		openUrl = WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
				+ WeiboConfig.getValue("client_ID").trim() + "&redirect_uri="
				+ URLEncoder.encode(uri, "UTF-8") + "&response_type=" + "code";// 获取token的登录页面连接,如果登录成功会得到带code参数的连接

		return "sinaLoginUrl";
	}

	/**
	 * 根据token获取新浪用户信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String checkLogin() throws UnsupportedEncodingException {
		Weibo weibo = new Weibo();
		weibo.setToken(accessToken);
		Users um = new Users();
		try {
			User user = um.showUserById(uid);
			if (user == null) {
				return null;
			} else {
				MemberInfo m = memberInfoFrontService
						.getMemberInfoBySinaUID(user.getId());// 根据新浪会员uid获取已绑定的消费者会员
				if (m == null) {// 如果没有消费者会员,应该跳转到注册页面进行消费者与新浪会员绑定
					session.put(sinaUserSessionKey, user);
					openUrl = "/api/sina/reg.jsp";
					if (StringUtil.notNullStr(callback)) {
						openUrl += "?callback="
								+ URLEncoder.encode(callback, "UTF-8");
					}
					return "reg";
				} else {
					callback = "/";
					account = URLEncoder.encode(m.getAccount(), "UTF-8");
					session.put(ConstantUtils.MEMBERINFO, m);
				}
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 如果没有绑定新浪的消费者会员,进行相应信息的注册成功本站的消费者会员
	 * 
	 * @return
	 */
	public String reg() {
		User user = (User) session.get(sinaUserSessionKey);
		if (user == null) {
			return null;
		}
		return SUCCESS;
	}

	/**
	 * 使用消费者会员登录并绑定新浪账号
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String login() throws UnsupportedEncodingException {
		MemberInfo memberInfo = memberInfoFrontService.loginMember(account,
				password);
		if (memberInfo == null) {
			responseHTMLAjax("0");
		} else if (StringUtil.notNullStr(memberInfo.getSina_uid())) {
			responseHTMLAjax("1");
		} else {
			User user = (User) session.get(sinaUserSessionKey);
			memberInfo.setSina_uid(user.getId());
			memberInfoFrontService.updateMemberInfo(memberInfo);
			// 添加 登陆记录表
			MemberLog memberLog = new MemberLog(memberInfo.getId(), 1, 0, "");
			memberLogCenterService.saveMemberLog(memberLog);
			memberInfo.setLastlogintime(new Date());
			memberInfoFrontService.updateMemberInfo(memberInfo);
			// 登陆获得积分
			IntegralConfig ic = integralConfigFrontService
					.findIntegralConfigInList(2);
			int curdatesum = integralInfoFrontService
					.getIntegralByDate(memberInfo.getId());
			if (ic != null && curdatesum < 5) {
				IntegralInfo integralInfo = new IntegralInfo(
						memberInfo.getId(), memberInfo.getAccount(), 2, 0, ic
								.getScore());
				integralInfoFrontService.saveIntegralInfo(integralInfo);
			}
			// 得到当前最新积分
			memberInfo = memberInfoFrontService.getMemberInfoById(memberInfo
					.getId());
			session.put(ConstantUtils.MEMBERINFO, memberInfo);
			responseHTMLAjax("2");
		}
		return null;
	}

	public MemberInfoFrontService getMemberInfoFrontService() {
		return memberInfoFrontService;
	}

	public void setMemberInfoFrontService(
			MemberInfoFrontService memberInfoFrontService) {
		this.memberInfoFrontService = memberInfoFrontService;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberLogCenterService getMemberLogCenterService() {
		return memberLogCenterService;
	}

	public void setMemberLogCenterService(
			MemberLogCenterService memberLogCenterService) {
		this.memberLogCenterService = memberLogCenterService;
	}

	public IntegralInfoFrontService getIntegralInfoFrontService() {
		return integralInfoFrontService;
	}

	public void setIntegralInfoFrontService(
			IntegralInfoFrontService integralInfoFrontService) {
		this.integralInfoFrontService = integralInfoFrontService;
	}

	public IntegralConfigFrontService getIntegralConfigFrontService() {
		return integralConfigFrontService;
	}

	public void setIntegralConfigFrontService(
			IntegralConfigFrontService integralConfigFrontService) {
		this.integralConfigFrontService = integralConfigFrontService;
	}

	public String getSinaUserSessionKey() {
		return sinaUserSessionKey;
	}

}
