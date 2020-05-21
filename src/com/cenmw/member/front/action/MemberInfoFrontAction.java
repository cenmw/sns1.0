package com.cenmw.member.front.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.*;

public class MemberInfoFrontAction extends BaseAction {
	private MemberInfoFrontService memberInfoFrontService;
	private String account;
	private String email;
	private String password;
	private MemberInfo memberInfo;
	private String checkcode;

	private String memberInfoJSON;

	private String nickname;// 昵称
	private Integer sex;
	private String birthday;
	private String region;// 所在地
	private String introduction;// 自我介绍
	private String truename;// 联系人
	private String telephone;// 联系电话
	private String mobile;// 手机
	private String zipcode;// 邮编
	private String address;// 详细地址
	private String avatar;// 会员头像

	private Integer id;

	private int issave;

	private int provinceid;
	private int cityid;
	private int regionid;

	private String callback;

	private int top;

	private String gu;

	private String reg_error; // 注册提示信息
	private long msgsize = 0;// 收到的短消息个数
	private int isescape = 0; // 0否1是

	/**
	 * 找回密码返回验证码
	 * 
	 * @return
	 */
	public String findpwdcode() {
		return SUCCESS;
	}

	/**
	 * 保存找回密码修改
	 * 
	 * @return
	 */
	public String savefindpwd() {
		return SUCCESS;
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	public String saveredirecturl() {
		String nourl[] = { "/shop/member", "/member", "/front/member" };
		boolean t = true;
		for (int i = 0; i < nourl.length; i++) {
			String nu = nourl[i];
			if (gu.indexOf(nu) >= 0) {
				t = false;
				session.put("session_backurl", "/member/showMemberCenter");
				break;
			}
		}
		if (t) {
			// System.out.println("session_backurl:" + gu);
			session.put("session_backurl", gu);
		}
		return null;
	}

	/**
	 * 获取最新注册的会员 要求:必需通过邮箱验证,已填写手机号和详细地址
	 * 
	 * @return
	 */
	public String getNewRegMemberListToJSON() {
		if (top > 50) {
			top = 50;
		}
		List<MemberInfo> mlist = memberInfoFrontService
				.getNewRegMemberList(top);
		List nlist = new ArrayList();
		if (mlist != null) {
			for (MemberInfo m : mlist) {
				Map map = new LinkedHashMap();
				map.put("nickname", m.getAccount());
				String mobile = StringUtil.centerHidden(m.getMobile(), 4, '*');
				map.put("mobile", mobile);
				nlist.add(map);
			}
		}
		responseJSONAjax(JsonUtil.getJsonStrByVOArray(nlist));
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String showMemberInfo() {
		if (id != null && id > 0) {
			memberInfo = memberInfoFrontService.getMemberInfoById(id);
			if (memberInfo != null) {
				session.put(ConstantUtils.MEMBERINFO, memberInfo);
			} else {
				return INPUT;
			}
		} else {
			memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
			if (memberInfo == null) {
				return INPUT;
			}
		}
		return SUCCESS;
	}

	/**
	 * 完善消费者信息
	 * 
	 * @return
	 */
	public String perfectMemberInfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberInfo == null) {
			return INPUT;
		}
		// memberInfo.setNickname(nickname);
		memberInfo.setSex(sex);
		memberInfo.setBirthday(DateUtil.StringToDate(birthday));
		memberInfo.setIntroduction(introduction);
		memberInfo.setMobile(mobile);
		memberInfo.setAddress(address);
		memberInfo.setAvatar(avatar);
		memberInfoFrontService.updateMemberInfo(memberInfo, session);
		return SUCCESS;
	}

	/**
	 * 修改用户资料
	 * 
	 * @return
	 */
	public String updateMemberInfo() {
		memberInfoFrontService.updateMemberInfo(memberInfo, session);
		session.put(ConstantUtils.MEMBERINFO, memberInfo);
		return SUCCESS;
	}

	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	public String login() {
		memberInfo = memberInfoFrontService.loginMember(account, password);
		if (memberInfo == null) {
			return INPUT;
		}
		// 只允许消费者会员登录
		if (memberInfo.getType() != 0) {
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 用户登陆页面
	 * 
	 * @return
	 */
	public String memberlogin() {
		callback = callback == null ? "" : callback;
		if (callback.equals("")) {
			callback = "http://c.100tiao1.net/sjzindex";
		}
		return SUCCESS;
	}

	/**
	 * AJAX验证是否有用户信息
	 * 
	 * @return
	 */
	public String checkHaveMember() {
		memberInfoJSON = "";
		account = CookieUtils.getCookieValue(request, "M_account");
		password = CookieUtils.getCookieValue(request, "M_password");
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberInfo == null && StringUtil.notNull(account).length() > 0
				&& StringUtil.notNull(password).length() > 0) {
			memberInfo = memberInfoFrontService.loginUndecryMember(account,
					password);
		}
		if (memberInfo != null) {
			if (session.get(ConstantUtils.MEMBERINFO) == null) {
				session.put(ConstantUtils.MEMBERINFO, memberInfo);
			}
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("id:" + memberInfo.getId() + ",");
			sb.append("account:'" + memberInfo.getAccount() + "',");
			sb.append("password:'" + memberInfo.getPassword() + "',");
			sb.append("avatar:'" + memberInfo.getAvatar() + "'");
			sb.append("}");
			memberInfoJSON = sb.toString();
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(memberInfoJSON);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用户退出登陆
	 * 
	 * @return
	 */
	public String loginOut() {
		if (session.get(ConstantUtils.MEMBERINFO) != null) {
			memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
			int type = memberInfo.getType();
			session.remove(ConstantUtils.MEMBERINFO);
			CookieUtils.delCookie(request, response, "M_account");
			CookieUtils.delCookie(request, response, "M_password");
			/* 同时清除论坛用户登陆状态 */
//			CookieUtils.delCookie(request, response, "dnt");
		}
		/* 同时清除当前登录用户cookie */
		CookieUtils.delCookie(request, response, ConstantUtils.TOKEN);
		return SUCCESS;
	}

	/**
	 * 验证账号是否被注册
	 * 
	 * @return
	 */
	public String checkAccount() {
		int result = 0;// result 0 无相同用户 1有相同的用户
		if (memberInfoFrontService.getMemberInfoByAccount(account) != null) {
			result = 1;
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证昵称是否被注册
	 * 
	 * @return
	 */
	public String checkNickName() {
		int result = 0;// result 0 无相同昵称 1有相同的昵称
		if (memberInfoFrontService.getMemberInfoByNickName(nickname) != null) {
			result = 1;
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 验证手机是否被注册
	 * 
	 * @return
	 */
	public String checkMobile() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		int result = 0;// result 0 无相同用户 1有相同的用户
		if (memberInfoFrontService.getMemberInfoByMobile(mobile, memberInfo
				.getId().intValue()) != null) {
			result = 1;
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证注册邮箱是否被注册
	 * 
	 * @return
	 */
	public String checkEmail() {
		int result = 0;// result 0 没有被注册 1被注册
		if (memberInfoFrontService.getMemberInfoByEmail(email) != null) {
			result = 1;
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
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

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getMemberInfoJSON() {
		return memberInfoJSON;
	}

	public void setMemberInfoJSON(String memberInfoJSON) {
		this.memberInfoJSON = memberInfoJSON;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getIssave() {
		return issave;
	}

	public void setIssave(int issave) {
		this.issave = issave;
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getRegionid() {
		return regionid;
	}

	public void setRegionid(int regionid) {
		this.regionid = regionid;
	}

}
