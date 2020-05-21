package com.cenmw.integral.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.integral.manager.service.IntegralInfoManagerService;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class IntegralInfoManagerAction extends BaseAction {
	/**
	 * 积分信息 模块
	 */
	private IntegralInfoManagerService integralInfoManagerService; 
	private IntegralInfo integralInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchaccount = "";
	private int searchtype = 0;

	/**
	 * 积分信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteINTEGRAL })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				integralInfo = integralInfoManagerService.getIntegralInfo(new Integer(idarrs[i].trim()));
				integralInfo.setIsdel(new Integer(1));
				integralInfoManagerService.updateIntegralInfo(integralInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 积分信息添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDINTEGRAL })
	public String save() {
		String msg = "修改成功！";
		if (integralInfo.getId() == null) {
			msg = "添加成功！";
			integralInfo.setIsdel(new Integer(0));
			integralInfo.setCtime(new Date());
			integralInfoManagerService.saveIntegralInfo(integralInfo);
		}
		int newtype = integralInfo.getType() == null ? 1 : integralInfo.getType()
				.intValue();
		switch (newtype) {
		case 1:
			integralInfo.setTypename("注册获取积分");
			break;
		case 2:
			integralInfo.setTypename("发咨询悬赏积分");
			break;
		case 3:
			integralInfo.setTypename("回复咨询获得积分");
			break;
		case 4:
			integralInfo.setTypename("完成任务获得积分");
			break;
		}
		integralInfoManagerService.updateIntegralInfo(integralInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 积分信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWINTEGRAL })
	public String info() {
		if (id > 0) {
			integralInfo = integralInfoManagerService.getIntegralInfo(id);
		}
		// 初始化信息
		if (integralInfo == null) {
			integralInfo = new IntegralInfo();
			integralInfo.setIsdel(new Integer(0));
			integralInfo.setType(new Integer(1));
		} 
		return SUCCESS;
	}

	/**
	 * 积分列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWINTEGRAL })
	public String list() {
		String hql = " from IntegralInfo where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchaccount != null && searchaccount.length()>0) {
			hql += " and account like '%" + searchaccount + "%'";
			HqlBean hqlBean = new HqlBean(searchaccount, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchaccount", hqlBean);
			try {
				parameter += "&searchaccount=" + StringUtil.URLEncode(searchaccount);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (searchtype > 0) {
			hql += " and type =" + searchtype;
			HqlBean hqlBean = new HqlBean(searchtype, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchtype", hqlBean);
			parameter += "&searchtype=" + searchtype;
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = integralInfoManagerService.findIntegralInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/integral/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlist(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				IntegralInfo iinfo = (IntegralInfo) pagelist.get(i);
				iinfo.setSumscore(integralInfoManagerService.getIntegralByMid(iinfo.getMid()));
				newlist.add(iinfo);
			}
		}
		return newlist;
	}
	
	public IntegralInfoManagerService getIntegralInfoManagerService() {
		return integralInfoManagerService;
	}

	public void setIntegralInfoManagerService(
			IntegralInfoManagerService integralInfoManagerService) {
		this.integralInfoManagerService = integralInfoManagerService;
	}

	public IntegralInfo getIntegralInfo() {
		return integralInfo;
	}

	public void setIntegralInfo(IntegralInfo integralInfo) {
		this.integralInfo = integralInfo;
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

	public String getSearchaccount() {
		return searchaccount;
	}

	public void setSearchaccount(String searchaccount) {
		this.searchaccount = searchaccount;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}


}
