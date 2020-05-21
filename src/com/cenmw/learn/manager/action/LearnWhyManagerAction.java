package com.cenmw.learn.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.manager.service.LearnWhyManagerService;
import com.cenmw.learn.po.LearnWhy;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class LearnWhyManagerAction extends BaseAction {
	/**
	 * 错误原因 模块
	 */
	private LearnWhyManagerService learnWhyManagerService; 
	private LearnWhy learnWhy;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 错误原因删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteLEARNCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				learnWhy = learnWhyManagerService.getLearnWhy(new Integer(idarrs[i].trim()));
				learnWhy.setIsdel(new Integer(1));
				learnWhyManagerService.updateLearnWhy(learnWhy);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 错误原因添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDLEARNCLASS })
	public String save() {
		String msg = "修改成功！";
		if (learnWhy.getId() == null) {
			msg = "添加成功！";
			learnWhy.setIsdel(new Integer(0));
			learnWhy.setCtime(new Date());
			learnWhyManagerService.saveLearnWhy(learnWhy);
			learnWhy.setSort(learnWhy.getId());
		}else{
			learnWhyManagerService.updateLearnWhy(learnWhy);
		}
		
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 错误原因查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLEARNCLASS })
	public String info() {
		if (id > 0) {
			learnWhy = learnWhyManagerService.getLearnWhy(id);
		}
		// 初始化信息
		if (learnWhy == null) {
			learnWhy = new LearnWhy();
			learnWhy.setIsdel(new Integer(0));
			learnWhy.setSort(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLEARNCLASS })
	public String list() {
		String hql = " from LearnWhy where isdel=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id asc ";

		if (searchtitle != null && searchtitle.length()>0) {
			hql += " and title like '%" + searchtitle + "%'";
			HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchtitle", hqlBean);
			try {
				parameter += "&searchtitle=" + StringUtil.URLEncode(searchtitle);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = learnWhyManagerService.findLearnWhyHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/learnwhy/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public LearnWhyManagerService getLearnWhyManagerService() {
		return learnWhyManagerService;
	}

	public void setLearnWhyManagerService(
			LearnWhyManagerService learnWhyManagerService) {
		this.learnWhyManagerService = learnWhyManagerService;
	}

	public LearnWhy getLearnWhy() {
		return learnWhy;
	}

	public void setLearnWhy(LearnWhy learnWhy) {
		this.learnWhy = learnWhy;
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
