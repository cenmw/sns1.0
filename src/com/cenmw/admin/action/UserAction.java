package com.cenmw.admin.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cenmw.admin.po.User;
import com.cenmw.admin.service.ActionGroupService;
import com.cenmw.admin.service.UserService;
import com.cenmw.base.BaseAction;

public class UserAction extends BaseAction {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private UserService userService;
	private ActionGroupService actionGroupService;
	private String username;
	private String password;
	private String checkcode;

	public String login() {
		String msg = "";
		User user = userService.getUser(username, password);
		if (user == null) {
			this.addFieldError("adminLoginError", "用户名或密码不正确");
			msg = "用户名或密码不正确！";
			session.put("adminMsg", msg);
			return INPUT;
		}
		if (session.get("rand") != null) {
			String rand = session.get("rand").toString();
			if (!rand.equalsIgnoreCase(checkcode)) {
				this.addFieldError("adminLoginError", "验证码不正确");
				msg = "验证码不正确！";
				session.put("adminMsg", msg);
				return INPUT;
			}
		} else {
			this.addFieldError("adminLoginError", "验证码不正确");
			msg = "验证码不正确！";
			session.put("adminMsg", msg);
			return INPUT;
		}

		session.put("adminInfo", user);
		return SUCCESS;
	}

	public String loginOut() {
		if (session.get("adminInfo") != null) {
			session.remove("adminInfo");
		}
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public ActionGroupService getActionGroupService() {
		return actionGroupService;
	}

	public void setActionGroupService(ActionGroupService actionGroupService) {
		this.actionGroupService = actionGroupService;
	}

}
