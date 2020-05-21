package com.cenmw.store.manager.action;

import com.cenmw.base.BaseAction;
import com.cenmw.store.manager.service.StoreConfigManagerService;
import com.cenmw.store.po.StoreConfig;
import com.cenmw.store.po.StoreConfigVo;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;

public class StoreConfigManagerAction extends BaseAction {
	/**
	 * 消费设置 模块
	 */
	private StoreConfigManagerService storeConfigManagerService;
	private StoreConfigVo storeConfigVo;
	private String scores;

	/**
	 * 消费设置添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDINTEGRALCONFIG })
	public String save() {
		String msg = "修改成功！";
		StoreConfig ic = storeConfigManagerService.findStoreConfigInList(1);
		if (ic == null) {
			msg = "设置成功！";
		}
		if (scores != null && scores.length() > 0) {
			String[] scorearrs = scores.split(",");
			for (int i = 0; i < scorearrs.length; i++) {
				StoreConfig newic = storeConfigManagerService
						.findStoreConfigInList(i + 1);
				if (newic == null) {
					newic = new StoreConfig();
					newic.setType(i + 1);
					newic.setMoney(new Integer(scorearrs[i].trim()));
					storeConfigManagerService.saveStoreConfig(newic);
				} else {
					newic.setMoney(new Integer(scorearrs[i].trim()));
					storeConfigManagerService.updateStoreConfig(newic);
				}
			}
		}
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 消费设置查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.UPDATEINTEGRALCONFIG })
	public String info() {
		storeConfigVo = new StoreConfigVo();
		StoreConfig ic1 = storeConfigManagerService.findStoreConfigInList(1);
		if (ic1 == null) {
			storeConfigVo.setScore1(0);
			storeConfigVo.setScore2(0);
		} else {
			for (int i = 1; i <= 9; i++) {
				StoreConfig ic = storeConfigManagerService
						.findStoreConfigInList(i);
				if (i == 1) {
					storeConfigVo.setScore1(ic.getMoney());
				} else if (i == 2) {
					storeConfigVo.setScore2(ic.getMoney());
				}
			}
		}

		return SUCCESS;
	}

	public StoreConfigManagerService getStoreConfigManagerService() {
		return storeConfigManagerService;
	}

	public void setStoreConfigManagerService(
			StoreConfigManagerService storeConfigManagerService) {
		this.storeConfigManagerService = storeConfigManagerService;
	}

	public StoreConfigVo getStoreConfigVo() {
		return storeConfigVo;
	}

	public void setStoreConfigVo(StoreConfigVo storeConfigVo) {
		this.storeConfigVo = storeConfigVo;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

}
