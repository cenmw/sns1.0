package com.cenmw.admin.manager.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cenmw.admin.manager.service.GroupManagerService;
import com.cenmw.admin.manager.service.UserManagerService;
import com.cenmw.admin.po.User;
import com.cenmw.base.BaseAction;
import com.cenmw.util.HqlBean;
import com.cenmw.util.MD5;

public class UserManagerAction extends BaseAction {
	private UserManagerService userManagerService;
	private GroupManagerService groupManagerService;
	private List ulist;
	private int id;
	private User user;
	private String backurl;

	private String searchname;

	public String showUser() {
		if (id > 0) {
			user = userManagerService.getUserById(id);
			user.setGroupManager(groupManagerService.getGroupManagerById(user.getGroupid()));
		}
		return SUCCESS;
	}

	public String saveUser() {
		String msg = "";
		MD5 md5 = new MD5();
		if (user.getId() == null) {
			User u = (User) session.get("adminInfo");
			if (!userManagerService.getUserByUsername(user.getUsername())) {
				session.put("userMsg", "添加失败，存在相同的用户！");
				return INPUT;
			}
			user.setCreateid(u.getId());
			user.setCreatename(u.getCreatename());
			user.setCtime(new Date());

			

			String encryptedPassword = md5.getMD5ofStr(user.getPassword());

			user.setPassword(encryptedPassword);
			userManagerService.saveUser(user);
			msg = "添加成功！";
		} else {
			if (userManagerService.updateUser(user)) {
				msg = "修改成功！";
			} else {
				session.put("userMsg", "修改失败，存在相同的用户！");
				return INPUT;
			}
		}
		session.put("userMsg", msg);
		return SUCCESS;
	}

	public String deleteUser() {
		userManagerService.deleteUserById(id);
		session.put("userMsg", "删除成功！");
		return SUCCESS;
	}

	public String getUserForPage() {
		Map map = new HashMap();
		map.put("status", new HqlBean("0", "=", "and", "Integer"));
		pageBean = userManagerService
				.getUserForPage(map, currentPage, pageSize);
		pageBean.setAction("/manager/sys/getUserForPage");
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	/**
	 * 在组管理里，在相应的组里添加用户时，搜索用户
	 * 
	 * @return
	 */
	public String getUserForSearch() {
		Map map = new HashMap();
		map.put("status", new HqlBean("0", "=", "and", "Integer"));
		if (searchname != null && searchname.trim().length() > 0) {
			HqlBean hb1 = new HqlBean(searchname, "like", "and", "String");
			hb1.setParam("searchname");
			HqlBean hb2 = new HqlBean(searchname, "like", "or", "String");
			hb2.setIsparam(1);
			map.put("username", hb1);
			map.put("truename", hb2);
		}
		pageBean = userManagerService
				.getUserForPage(map, currentPage, pageSize);
		pageBean.setAction("/manager/sys/getUserForSearch");
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public UserManagerService getUserManagerService() {
		return userManagerService;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
	
	public static void main(String[] args){
		MD5 md5 = new MD5();  //74828970E550469E      AD9988176D9795D8E794286DCD5F94A2
		String code = md5.getMD5ofStr("74828970E550469E");
		System.out.println(code);
	}

	public List getUlist() {
		return ulist;
	}

	public void setUlist(List ulist) {
		this.ulist = ulist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBackurl() {
		return backurl;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	public GroupManagerService getGroupManagerService() {
		return groupManagerService;
	}

	public void setGroupManagerService(GroupManagerService groupManagerService) {
		this.groupManagerService = groupManagerService;
	}

}
