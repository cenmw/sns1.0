package com.cenmw.labor.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.labor.manager.service.LaborClassManagerService;
import com.cenmw.labor.po.LaborClass;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class LaborClassManagerAction extends BaseAction {
	/**
	 * 任务分类 模块
	 */
	private LaborClassManagerService laborClassManagerService;
	private LaborClass laborClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 任务分类删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteLABORCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				laborClass = laborClassManagerService
						.getLaborClass(new Integer(idarrs[i].trim()));
				laborClass.setIsdel(new Integer(1));
				laborClassManagerService.updateLaborClass(laborClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 任务分类添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDLABORCLASS })
	public String save() {
		String msg = "修改成功！";
		if (laborClass.getId() == null) {
			msg = "添加成功！";
			laborClass.setIsdel(new Integer(0));
			laborClass.setCtime(new Date());
			laborClassManagerService.saveLaborClass(laborClass);
			laborClass.setSort(laborClass.getId());
		} else {
			laborClassManagerService.updateLaborClass(laborClass);
		}

		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 任务分类查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLABORCLASS })
	public String info() {
		if (id > 0) {
			laborClass = laborClassManagerService.getLaborClass(id);
		}
		// 初始化信息
		if (laborClass == null) {
			laborClass = new LaborClass();
			laborClass.setIsdel(new Integer(0));
			laborClass.setTitle("");
			laborClass.setDescription("");
			laborClass.setSort(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLABORCLASS })
	public String list() {
		String hql = " from LaborClass where isdel=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id desc ";

		if (searchtitle != null && searchtitle.length() > 0) {
			hql += " and title like '%" + searchtitle + "%'";
			HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchtitle", hqlBean);
			try {
				parameter += "&searchtitle="
						+ StringUtil.URLEncode(searchtitle);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = laborClassManagerService.findLaborClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/laborclass/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public LaborClassManagerService getLaborClassManagerService() {
		return laborClassManagerService;
	}

	public void setLaborClassManagerService(
			LaborClassManagerService laborClassManagerService) {
		this.laborClassManagerService = laborClassManagerService;
	}

	public LaborClass getLaborClass() {
		return laborClass;
	}

	public void setLaborClass(LaborClass laborClass) {
		this.laborClass = laborClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

}
