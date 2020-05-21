package com.cenmw.extapi.qq.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.cenmw.util.ConstantUtils;
import com.tencent.weibo.api.OAuth2Code;
import com.tencent.weibo.beans.AccessToken;
import com.tencent.weibo.beans.Account;
import com.cenmw.extapi.base.ExtapiBaseAction;
import com.cenmw.integral.front.service.IntegralConfigFrontService;
import com.cenmw.integral.front.service.IntegralInfoFrontService;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.member.center.service.MemberLogCenterService;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberLog;
import com.cenmw.util.IPUtil;
import com.cenmw.util.StringUtil;

public class QQLoginHandleAction extends ExtapiBaseAction {
	private MemberInfoFrontService memberInfoFrontService;
	private MemberLogCenterService memberLogCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private IntegralConfigFrontService integralConfigFrontService;

	private String account;
	private String password;
	private String email;
	private String callback;

	private String openUrl;

	private String avatar = "/member/images/common/avatar.gif";

	private String accessToken;
	private String openid;
	private String code;

	private final String qqUserSessionKey = "qqUserSessionKey";

	/**
	 * 获取QQ token 登录页面连接
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String index() throws UnsupportedEncodingException {
		OAuth2Code o2c = new OAuth2Code();
		openUrl = o2c.getOpenUrl(callback);
		return "qqLoginUrl";
	}

	/**
	 * 根据token获取用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkLogin() throws Exception {
		OAuth2Code o2c = new OAuth2Code();
		AccessToken at = o2c.getToken(code, callback);
		Account user = o2c.getAccount(at.getAccessToken(), openid,
				IPUtil.getRealIP(request));
		if (user == null) {
			return null;
		} else {
			MemberInfo m = memberInfoFrontService.getMemberInfoByQqUID(user
					.getOpenid());
			if (m == null) {// 如果没有消费者会员,应该跳转到注册页面进行消费者与QQ会员绑定
				session.put(qqUserSessionKey, user);
				openUrl = "/api/qq/reg.jsp";
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
		return SUCCESS;
	}

	/**
	 * 如果没有绑定QQ微博的消费者会员,进行相应信息的注册成功本站的消费者会员
	 * 
	 * @return
	 */
	public String reg() {
		Account user = (Account) session.get(qqUserSessionKey);
		if (user == null) {
			return null;
		}
		return SUCCESS;
	}

	/**
	 * 使用消费者会员登录并绑定QQ微博
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String login() throws UnsupportedEncodingException {
		MemberInfo memberInfo = memberInfoFrontService.loginMember(account, password);
		if (memberInfo == null) {
			responseHTMLAjax("0");
		} else if (StringUtil.notNullStr(memberInfo.getQq_uid())) {
			responseHTMLAjax("1");
		} else {
			Account user = (Account) session.get(qqUserSessionKey);
			memberInfo.setQq_uid(user.getOpenid());
			memberInfoFrontService.updateMemberInfo(memberInfo);
			// 添加 登陆记录表
			MemberLog memberLog = new MemberLog(memberInfo.getId(), 1, 0, "");
			memberLogCenterService.saveMemberLog(memberLog);
			memberInfo.setLastlogintime(new Date());
			memberInfoFrontService.updateMemberInfo(memberInfo);
			// 登陆获得积分
			IntegralConfig ic = integralConfigFrontService
					.findIntegralConfigInList(2);
			int curdatesum = integralInfoFrontService.getIntegralByDate(memberInfo
					.getId());
			if (ic != null && curdatesum < 5) {
				IntegralInfo integralInfo = new IntegralInfo(memberInfo.getId(),
						memberInfo.getAccount(), 2, 0, ic.getScore());
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

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getQqUserSessionKey() {
		return qqUserSessionKey;
	}

}
