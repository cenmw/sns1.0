package com.cenmw.admin.manager.action;

import java.util.List;

import com.cenmw.admin.manager.service.ActionColumnManagerService;
import com.cenmw.admin.po.ActionColumn;
import com.cenmw.base.BaseAction;

public class ActionColumnManagerAction extends BaseAction {
	private ActionColumnManagerService actionColumnManagerService;
	private List actionColumnList;
	private int acid;
	private ActionColumn actionColumn;
	private int tag;

	public String showActionColumnList() {
//		CacheManager cacheManager = CacheManager.getInstance();
//		Cache cache = cacheManager.getCache("DISK_CACHE");
//		String eleKey = "actionColumnList";
//		Element element = cache.get(eleKey);
//		if (element == null) {
//			actionColumnList = actionColumnManagerService.getActionColumnList();
//			element = new Element(eleKey, (Serializable) actionColumnList);
//			cache.put(element);
//			cache.flush();
//		} else {
//			actionColumnList = (List) element.getValue();
//		}
		actionColumnList = actionColumnManagerService.getActionColumnList();
		if (tag > 0) {
			return INPUT;
		}
		return SUCCESS;
	}

	public String showActionColumn() {
		if (acid > 0) {
			actionColumn = actionColumnManagerService.getActionColumnById(acid);
		}
		return null;
	}

	public String saveActionColumn() {
		String msg = "";
		if (acid > 0) {
			actionColumnManagerService.saveActionColumn(actionColumn);
			msg = "修改成功！";
		} else {
			actionColumnManagerService.saveActionColumn(actionColumn);
			actionColumn.setSort(actionColumn.getActioncolumnid());
			actionColumnManagerService.updateActionColumn(actionColumn);
			msg = "添加成功！";
		}
		session.put("ActionColumnMsg", msg);
		return null;
	}

	public String deleteActionColumn() {
		actionColumnManagerService.deleteActionColumn(acid);
		session.put("ActionColumnMsg", "删除成功！");
		return null;
	}

	public ActionColumn getActionColumn() {
		return actionColumn;
	}

	public void setActionColumn(ActionColumn actionColumn) {
		this.actionColumn = actionColumn;
	}

	public int getAcid() {
		return acid;
	}

	public void setAcid(int acid) {
		this.acid = acid;
	}

	public ActionColumnManagerService getActionColumnManagerService() {
		return actionColumnManagerService;
	}

	public void setActionColumnManagerService(
			ActionColumnManagerService actionColumnManagerService) {
		this.actionColumnManagerService = actionColumnManagerService;
	}

	public void setActionColumnList(List actionColumnList) {
		this.actionColumnList = actionColumnList;
	}

	public List getActionColumnList() {
		return actionColumnList;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
}
