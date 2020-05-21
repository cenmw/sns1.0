package com.cenmw.admin.manager.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.admin.manager.dao.ActionGroupManagerDao;
import com.cenmw.admin.manager.service.ActionManagerService;
import com.cenmw.admin.po.Action;
import com.cenmw.base.BaseAction;
import com.cenmw.util.HqlBean;

public class ActionManagerAction extends BaseAction {
	private ActionManagerService actionManagerService;
	private List alist;
	private int acid;
	private int id;
	private Action action;

	public String getActionList() {
		Map map = new HashMap();
		map.put("actioncolumnid", new HqlBean(acid, "=", "and", "Integer"));
		alist = actionManagerService.getActionList(map);
		return SUCCESS;
	}

	public String showAction() {
		action = null;
		if (id > 0) {
			action = actionManagerService.getActionById(id);
		}
		return SUCCESS;
	}

	public String saveAction() {
		String msg = "";
		if (action.getActionid()!=null&&action.getActionid() > 0) {
			
		} else {
			List actionList=actionManagerService.getActionListByAction(action.getAction());
			if(actionList!=null&&!actionList.isEmpty()){
				session.put("actionMsg", "添加失败,有相同的动作！");
				return INPUT;
			}
			actionManagerService.saveAction(action);
			msg = "添加成功！";
		}
		session.put("actionMsg", msg);
		return SUCCESS;
	}

	public String deleteAction() {
		if (id > 0) {
			Action a = actionManagerService.getActionById(id);
			if (a != null) {
				actionManagerService.deleteAction(a);
			}
		}
		session.put("actionMsg", "删除成功！");
		return SUCCESS;
	}

	public ActionManagerService getActionManagerService() {
		return actionManagerService;
	}

	public void setActionManagerService(
			ActionManagerService actionManagerService) {
		this.actionManagerService = actionManagerService;
	}

	public List getAlist() {
		return alist;
	}

	public void setAlist(List alist) {
		this.alist = alist;
	}

	public int getAcid() {
		return acid;
	}

	public void setAcid(int acid) {
		this.acid = acid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
