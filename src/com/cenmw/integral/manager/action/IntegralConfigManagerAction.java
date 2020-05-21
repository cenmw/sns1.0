package com.cenmw.integral.manager.action;

import com.cenmw.base.BaseAction;
import com.cenmw.integral.manager.service.IntegralConfigManagerService;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.integral.po.IntegralConfigVo;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;

public class IntegralConfigManagerAction extends BaseAction {
	/**
	 * 积分设置 模块
	 */
	private IntegralConfigManagerService integralConfigManagerService;
	private IntegralConfigVo integralConfigVo;
	private String scores;

	/**
	 * 积分设置添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDINTEGRALCONFIG })
	public String save() {
		String msg = "修改成功！";
		IntegralConfig ic = integralConfigManagerService
				.findIntegralConfigInList(1);
		if (ic == null) {
			msg = "设置成功！";
		}
		if (scores != null && scores.length() > 0) {
			String[] scorearrs = scores.split(",");
			if (scorearrs.length == 6) {
				for (int i = 0; i < scorearrs.length; i++) {
					IntegralConfig newic = integralConfigManagerService
							.findIntegralConfigInList(i + 1);
					if (newic == null) {
						newic = new IntegralConfig();
						newic.setType(i + 1);
						newic.setScore(new Integer(scorearrs[i].trim()));
						integralConfigManagerService.saveIntegralConfig(newic);
					} else {
						newic.setScore(new Integer(scorearrs[i].trim()));
						integralConfigManagerService
								.updateIntegralConfig(newic);
					}
				}
			}
		}
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 积分设置查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.UPDATEINTEGRALCONFIG })
	public String info() {
		integralConfigVo = new IntegralConfigVo();
		IntegralConfig ic1 = integralConfigManagerService
				.findIntegralConfigInList(1);
		if (ic1 == null) {
			integralConfigVo.setScore1(0);
			integralConfigVo.setScore2(0);
			integralConfigVo.setScore3(0);
			integralConfigVo.setScore4(0);
			integralConfigVo.setScore5(0);
			integralConfigVo.setScore6(0);
		} else {
			for (int i = 1; i <= 9; i++) {
				IntegralConfig ic = integralConfigManagerService
						.findIntegralConfigInList(i);
				if (i == 1) {
					integralConfigVo.setScore1(ic.getScore());
				} else if (i == 2) {
					integralConfigVo.setScore2(ic.getScore());
				} else if (i == 3) {
					integralConfigVo.setScore3(ic.getScore());
				} else if (i == 4) {
					integralConfigVo.setScore4(ic.getScore());
				} else if (i == 5) {
					integralConfigVo.setScore5(ic.getScore());
				} else if (i == 6) {
					integralConfigVo.setScore6(ic.getScore());
				}
			}
		}
		return SUCCESS;
	}

	public IntegralConfigManagerService getIntegralConfigManagerService() {
		return integralConfigManagerService;
	}

	public void setIntegralConfigManagerService(
			IntegralConfigManagerService integralConfigManagerService) {
		this.integralConfigManagerService = integralConfigManagerService;
	}

	public IntegralConfigVo getIntegralConfigVo() {
		return integralConfigVo;
	}

	public void setIntegralConfigVo(IntegralConfigVo integralConfigVo) {
		this.integralConfigVo = integralConfigVo;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

}
