package com.cenmw.admin.manager.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.admin.manager.service.ActionColumnManagerService;
import com.cenmw.admin.manager.service.ActionGroupManagerService;
import com.cenmw.admin.manager.service.ActionManagerService;
import com.cenmw.admin.manager.service.GroupManagerService;
import com.cenmw.admin.po.Action;
import com.cenmw.admin.po.ActionGroup;
import com.cenmw.admin.po.GroupManager;
import com.cenmw.admin.po.User;
import com.cenmw.base.BaseAction;
import com.cenmw.util.HqlBean;

public class ActionGroupManagerAction extends BaseAction {
	private ActionGroupManagerService actionGroupManagerService;
	private ActionManagerService actionManagerService;
	private GroupManagerService groupManagerService;
	private ActionColumnManagerService actionColumnManagerService;

	private ActionGroup actionGroup;

	private String action;
	private int id;
	private String actionname;

	private String backurl;

	private int groupid;
	private List spList;
	private List cloList;
	private String acts;

	public String saveactionlist() {
		if (acts != null && acts.length() > 0) {
			String[] arr = acts.split(",");
			User user = (User) session.get("adminInfo");
			for (int i = 0; i < arr.length; i++) {
				int actionid = Integer.valueOf(arr[i]);
				ActionGroup a = new ActionGroup();
				Action act = actionManagerService.getActionById(actionid);
				GroupManager g = groupManagerService
						.getGroupManagerById(groupid);
				a.setCreateid(user.getId());
				a.setCreatename(user.getUsername());
				a.setCtime(new Date());
				a.setAction(act.getAction());
				a.setActionname(act.getActionname());
				a.setGroupid(g.getGroupid());
				a.setGroupname(g.getGroupname());
				actionGroupManagerService.saveActionGroup(a);
			}
		}
		return null;
	}

	public String saveaction() {
		User user = (User) session.get("adminInfo");
		ActionGroup a = new ActionGroup();
		Action act = actionManagerService.getActionById(id);
		GroupManager g = groupManagerService.getGroupManagerById(groupid);
		a.setCreateid(user.getId());
		a.setCreatename(user.getUsername());
		a.setCtime(new Date());
		a.setAction(act.getAction());
		a.setActionname(act.getActionname());
		a.setGroupid(g.getGroupid());
		a.setGroupname(g.getGroupname());
		actionGroupManagerService.saveActionGroup(a);
		return null;
	}

	public String selectactionpage() {
		cloList = actionColumnManagerService.getActionColumnList();
		spList = actionGroupManagerService.selectactionpage(groupid);
		return SUCCESS;
	}

	public String actionbygroup() {
		pageBean = actionGroupManagerService.getActionByGroupid(groupid,
				currentPage, pageSize);
		pageBean.setAction("/manager/sys/actionbygroup");
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public String getActionGroupForPage() {
		Map<String, Object> map = new HashMap();
		map.put("action", new HqlBean(action, "=", "and", "String"));
		HqlBean hb = new HqlBean(actionname, "=", "and", "String");
		hb.setIshql(1);
		map.put("actionname", hb);
		pageBean = actionGroupManagerService.getActionGroupForPage(map, "id",
				"asc", currentPage, pageSize);
		pageBean.setAction("/manager/sys/getActionGroupForPage");
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public String showActionGroup() {
		if (id > 0) {
			actionGroup = actionGroupManagerService.getActionGroupById(id);
		} else {
			actionGroup = new ActionGroup();
			actionGroup.setAction(action);
			actionGroup.setActionname(actionname);
		}
		return SUCCESS;
	}

	public String saveActionGroup() {
		String msg = "";
		if (actionGroup.getId() == null) {
			User user = (User) session.get("adminInfo");
			actionGroup.setCreateid(user.getId());
			actionGroup.setCreatename(user.getUsername());
			actionGroup.setCtime(new Date());

			Map map = new HashMap();
			map.put("action", new HqlBean(actionGroup.getAction(), "=", "and",
					"String"));
			map.put("actionname", new HqlBean(actionGroup.getActionname(), "=",
					"and", "String"));
			List list = actionGroupManagerService.getActionGroupList(map, "id",
					"asc");
			if (list == null || list.isEmpty()) {
				actionGroupManagerService.saveActionGroup(actionGroup);
				msg = "添加成功！";
			} else {
				session.put("actionGroupMsg", "存在相同的组！");
				return INPUT;
			}
		} else {
			actionGroupManagerService.saveActionGroup(actionGroup);
			msg = "修改成功！";
		}
		session.put("actionGroupMsg", msg);
		return SUCCESS;
	}

	public String deleteActionGroup() {
		actionGroupManagerService.deleteActionGroup(id);
		session.put("actionGroupMsg", "删除成功！");
		return SUCCESS;
	}

	public ActionGroupManagerService getActionGroupManagerService() {
		return actionGroupManagerService;
	}

	public void setActionGroupManagerService(
			ActionGroupManagerService actionGroupManagerService) {
		this.actionGroupManagerService = actionGroupManagerService;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public ActionGroup getActionGroup() {
		return actionGroup;
	}

	public void setActionGroup(ActionGroup actionGroup) {
		this.actionGroup = actionGroup;
	}

	public String getBackurl() {
		return backurl;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public List getSpList() {
		return spList;
	}

	public void setSpList(List spList) {
		this.spList = spList;
	}

	public ActionManagerService getActionManagerService() {
		return actionManagerService;
	}

	public void setActionManagerService(
			ActionManagerService actionManagerService) {
		this.actionManagerService = actionManagerService;
	}

	public GroupManagerService getGroupManagerService() {
		return groupManagerService;
	}

	public void setGroupManagerService(GroupManagerService groupManagerService) {
		this.groupManagerService = groupManagerService;
	}

	public ActionColumnManagerService getActionColumnManagerService() {
		return actionColumnManagerService;
	}

	public void setActionColumnManagerService(
			ActionColumnManagerService actionColumnManagerService) {
		this.actionColumnManagerService = actionColumnManagerService;
	}

	public List getCloList() {
		return cloList;
	}

	public void setCloList(List cloList) {
		this.cloList = cloList;
	}

	public String getActs() {
		return acts;
	}

	public void setActs(String acts) {
		this.acts = acts;
	}

}
