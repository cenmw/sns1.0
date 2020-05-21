package com.cenmw.consult.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.consult.manager.service.ConsultClassManagerService;
import com.cenmw.consult.po.ConsultClass;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class ConsultClassManagerAction extends BaseAction {
	/**
	 * 咨询分类 模块
	 */
	private ConsultClassManagerService consultClassManagerService; 
	private ConsultClass consultClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchtype = 0;

	/**
	 * 咨询分类删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteCONSULTCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				consultClass = consultClassManagerService.getConsultClass(new Integer(idarrs[i].trim()));
				consultClass.setIsdel(new Integer(1));
				consultClassManagerService.updateConsultClass(consultClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 咨询分类添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDCONSULTCLASS })
	public String save() {
		String msg = "修改成功！";
		if (consultClass.getId() == null) {
			msg = "添加成功！";
			consultClass.setIsdel(new Integer(0));
			consultClass.setCtime(new Date());
			consultClassManagerService.saveConsultClass(consultClass);
			consultClass.setSort(consultClass.getId());
		}else{
			consultClassManagerService.updateConsultClass(consultClass);
		}
		
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 咨询分类查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCONSULTCLASS })
	public String info() {
		if (id > 0) {
			consultClass = consultClassManagerService.getConsultClass(id);
		}
		// 初始化信息
		if (consultClass == null) {
			consultClass = new ConsultClass();
			consultClass.setIsdel(new Integer(0));
			consultClass.setMid(new Integer(0));
			consultClass.setTitle("");
			consultClass.setDescription("");
			consultClass.setSort(new Integer(0));
			consultClass.setType(new Integer(2));
			consultClass.setPicpath("/member/images/common/no_photo.png");
		} 
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCONSULTCLASS })
	public String list() {
		String hql = " from ConsultClass where isdel=0 and mid=0 and type=2 ";
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
		pageBean = consultClassManagerService.findConsultClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/consultclass/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public ConsultClassManagerService getConsultClassManagerService() {
		return consultClassManagerService;
	}

	public void setConsultClassManagerService(
			ConsultClassManagerService consultClassManagerService) {
		this.consultClassManagerService = consultClassManagerService;
	}

	public ConsultClass getConsultClass() {
		return consultClass;
	}

	public void setConsultClass(ConsultClass consultClass) {
		this.consultClass = consultClass;
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
