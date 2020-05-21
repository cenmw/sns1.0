package com.cenmw.admin.manager.action;

import java.util.Date;
import java.util.List;

import com.cenmw.admin.manager.service.GroupManagerService;
import com.cenmw.admin.po.GroupManager;
import com.cenmw.admin.po.User;
import com.cenmw.base.BaseAction;

public class GroupManagerAction extends BaseAction {
	private GroupManagerService groupManagerService;
	private GroupManager groupManager;
	private int id;
	private List gmList;
	private int groupid;

	private int status;

	public String showGroupManager() {
		if (id > 0) {
			groupManager = groupManagerService.getGroupManagerById(id);
		}
		return SUCCESS;
	}

	public String saveGroupManager() {
		String msg = "";
		if (groupManager.getGroupid() == null) {
			User user = (User) session.get("adminInfo");
			groupManager.setCreateid(user.getId());
			groupManager.setCreatename(user.getUsername());
			groupManager.setCtime(new Date());
			groupManagerService.saveGroupManager(groupManager);
			msg = "添加成功！";
		} else {
			groupManagerService.saveGroupManager(groupManager);
			msg = "修改成功！";
		}
		session.put("groupManagerMsg", msg);
		return SUCCESS;
	}

	public String getGroupManagerList() {
		gmList = groupManagerService.getGroupManagerList(null);
		if (status == 1) {
			return INPUT;
		}
		return SUCCESS;
	}

	public String deleteGroupManager() {
		groupManagerService.deleteGroupManagerById(id);

		session.put("groupManagerMsg", "删除成功！");
		return SUCCESS;
	}

	public GroupManagerService getGroupManagerService() {
		return groupManagerService;
	}

	public void setGroupManagerService(GroupManagerService groupManagerService) {
		this.groupManagerService = groupManagerService;
	}

	public GroupManager getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getGmList() {
		return gmList;
	}

	public void setGmList(List gmList) {
		this.gmList = gmList;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
