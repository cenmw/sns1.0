package com.cenmw.admin.manager.service;

import java.util.List;
import java.util.Map;

import com.cenmw.admin.manager.dao.ActionGroupManagerDao;
import com.cenmw.admin.manager.dao.ActionManagerDao;
import com.cenmw.admin.po.ActionGroup;
import com.cenmw.util.PageBean;

public class ActionGroupManagerService {
	private ActionGroupManagerDao actionGroupManagerDao;
	private ActionManagerDao actionManagerDao;
	public List selectactionpage(int groupid){
		List alist=getActionListByGroupid(groupid);
		return actionManagerDao.getActionListNoAction(alist);
	}
	
	public PageBean getActionByGroupid(int groupid,int currentPage,int pageSize){
		return actionGroupManagerDao.getActionByGroupid(groupid, currentPage, pageSize);
	}
	public List getActionListByGroupid(int groupid){
		return actionGroupManagerDao.getActionListByGroupid(groupid);
	}

	public void saveActionGroup(ActionGroup actionGroup) {
		actionGroupManagerDao.saveActionGroup(actionGroup);
	}

	public void updateActionGroup(ActionGroup actionGroup) {
		actionGroupManagerDao.updateActionGroup(actionGroup);
	}

	public ActionGroup getActionGroupById(int id) {
		return actionGroupManagerDao.getActionGroupById(id);
	}

	public void deleteActionGroup(int id) {
		actionGroupManagerDao.deleteActionGroupById(id);
	}

	public void deleteActionGroup(ActionGroup actionGroup) {
		actionGroupManagerDao.deleteActionGroup(actionGroup);
	}

	public List getActionGroupList(Map map, String ordername, String sort) {
		return actionGroupManagerDao.getActionGroupList(map, ordername, sort);
	}

	public PageBean getActionGroupForPage(Map map, String ordername,
			String sort, int cpage, int pageSize) {
		return actionGroupManagerDao.getActionGroupForPage(map, ordername,
				sort, cpage, pageSize);
	}

	public ActionGroupManagerDao getActionGroupManagerDao() {
		return actionGroupManagerDao;
	}

	public void setActionGroupManagerDao(
			ActionGroupManagerDao actionGroupManagerDao) {
		this.actionGroupManagerDao = actionGroupManagerDao;
	}

	public ActionManagerDao getActionManagerDao() {
		return actionManagerDao;
	}

	public void setActionManagerDao(ActionManagerDao actionManagerDao) {
		this.actionManagerDao = actionManagerDao;
	}

}
