package com.cenmw.vedio.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.vedio.manager.service.VedioClassManagerService;
import com.cenmw.vedio.po.VedioClass;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class VedioClassManagerAction extends BaseAction {
	/**
	 * 视频分类 模块
	 */
	private VedioClassManagerService vedioClassManagerService; 
	private VedioClass vedioClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchtype = 0;

	/**
	 * 视频分类删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteVEDIOCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				vedioClass = vedioClassManagerService.getVedioClass(new Integer(idarrs[i].trim()));
				vedioClass.setIsdel(new Integer(1));
				vedioClassManagerService.updateVedioClass(vedioClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 视频分类添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDVEDIOCLASS })
	public String save() {
		String msg = "修改成功！";
		if (vedioClass.getId() == null) {
			msg = "添加成功！";
			vedioClass.setIsdel(new Integer(0));
			vedioClass.setCtime(new Date());
			vedioClassManagerService.saveVedioClass(vedioClass);
			vedioClass.setSort(vedioClass.getId());
		}
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 视频分类查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWVEDIOCLASS })
	public String info() {
		if (id > 0) {
			vedioClass = vedioClassManagerService.getVedioClass(id);
		}
		// 初始化信息
		if (vedioClass == null) {
			vedioClass = new VedioClass();
			vedioClass.setIsdel(new Integer(0));
			vedioClass.setMid(new Integer(0));
			vedioClass.setTitle("");
			vedioClass.setDescription("");
			vedioClass.setPicpath("");
			vedioClass.setSort(new Integer(0));
			vedioClass.setType(new Integer(2));
		} 
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWVEDIOCLASS })
	public String list() {
		String hql = " from VedioClass where isdel=0 and mid=0 and type=2 ";
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
		pageBean = vedioClassManagerService.findVedioClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/vedioclass/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public VedioClassManagerService getVedioClassManagerService() {
		return vedioClassManagerService;
	}

	public void setVedioClassManagerService(
			VedioClassManagerService vedioClassManagerService) {
		this.vedioClassManagerService = vedioClassManagerService;
	}

	public VedioClass getVedioClass() {
		return vedioClass;
	}

	public void setVedioClass(VedioClass vedioClass) {
		this.vedioClass = vedioClass;
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

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}


}
