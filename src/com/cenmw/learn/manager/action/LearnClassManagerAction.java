package com.cenmw.learn.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.manager.service.LearnClassManagerService;
import com.cenmw.learn.po.LearnClass;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class LearnClassManagerAction extends BaseAction {
	/**
	 * 学习分类 模块
	 */
	private LearnClassManagerService learnClassManagerService; 
	private LearnClass learnClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 学习分类删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteLEARNCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				learnClass = learnClassManagerService.getLearnClass(new Integer(idarrs[i].trim()));
				learnClass.setIsdel(new Integer(1));
				learnClassManagerService.updateLearnClass(learnClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 学习分类添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDLEARNCLASS })
	public String save() {
		String msg = "修改成功！";
		if (learnClass.getId() == null) {
			msg = "添加成功！";
			learnClass.setIsdel(new Integer(0));
			learnClass.setCtime(new Date());
			learnClassManagerService.saveLearnClass(learnClass);
			learnClass.setSort(learnClass.getId());
		}else{
			learnClassManagerService.updateLearnClass(learnClass);
		}
		
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 学习分类查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLEARNCLASS })
	public String info() {
		if (id > 0) {
			learnClass = learnClassManagerService.getLearnClass(id);
		}
		// 初始化信息
		if (learnClass == null) {
			learnClass = new LearnClass();
			learnClass.setIsdel(new Integer(0));
			learnClass.setMid(new Integer(0));
			learnClass.setTitle("");
			learnClass.setDescription("");
			learnClass.setPicpath("");
			learnClass.setSort(new Integer(0));
			learnClass.setPicpath("/member/images/common/no_photo.png");
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
		String hql = " from LearnClass where isdel=0 and mid=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id desc ";

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
		pageBean = learnClassManagerService.findLearnClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/learnclass/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public LearnClassManagerService getLearnClassManagerService() {
		return learnClassManagerService;
	}

	public void setLearnClassManagerService(
			LearnClassManagerService learnClassManagerService) {
		this.learnClassManagerService = learnClassManagerService;
	}

	public LearnClass getLearnClass() {
		return learnClass;
	}

	public void setLearnClass(LearnClass learnClass) {
		this.learnClass = learnClass;
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
