package com.cenmw.admin.manager.action;

import com.cenmw.admin.manager.service.UserManagerService;
import com.cenmw.admin.po.User;
import com.cenmw.base.BaseAction;
import com.cenmw.util.MD5;

/**
 * @Package com.cenmw.admin.manager.action
 * @FileName UserPwdManagerAction.java
 * @create_time 2011-9-7 下午04:42:51
 * @author LiangJiChao
 * 
 */
public class UserPwdManagerAction extends BaseAction {
	private UserManagerService userManagerService;
	private String pwd;

	public UserManagerService getUserManagerService() {
		return userManagerService;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}

	public String updatepass() {
		User user = (User) session.get("adminInfo");
		MD5 md5 = new MD5();
		String encryptedPassword = md5.getMD5ofStr(pwd);
		user.setPassword(encryptedPassword);
		userManagerService.saveUser(user);
		session.put("adminInfo", user);
		return SUCCESS;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
