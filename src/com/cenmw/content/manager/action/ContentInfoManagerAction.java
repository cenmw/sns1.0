package com.cenmw.content.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.content.manager.service.ContentInfoManagerService;
import com.cenmw.content.po.ContentInfo;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class ContentInfoManagerAction extends BaseAction {
	/**
	 * 基础信息 模块
	 */
	private ContentInfoManagerService contentInfoManagerService; 
	private ContentInfo contentInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchtype = 0;

	/**
	 * 基础信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteCONTENT })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				contentInfo = contentInfoManagerService.getContentInfo(new Integer(idarrs[i].trim()));
				contentInfo.setIsdel(new Integer(1));
				contentInfoManagerService.updateContentInfo(contentInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 基础信息添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDCONTENT })
	public String save() {
		String msg = "修改成功！";
		if (contentInfo.getId() == null) {
			msg = "添加成功！";
			contentInfo.setIsdel(new Integer(0));
			contentInfo.setCtime(new Date());
			contentInfoManagerService.saveContentInfo(contentInfo);
			contentInfo.setSort(contentInfo.getId());
		}
		int newtype = contentInfo.getType() == null ? 1 : contentInfo.getType()
				.intValue();
		switch (newtype) {
		case 1:
			contentInfo.setTypename("关于我们");
			break;
		case 2:
			contentInfo.setTypename("联系我们");
			break;
		case 3:
			contentInfo.setTypename("招聘信息");
			break;
		case 4:
			contentInfo.setTypename("友情链接");
			break;
		case 5:
			contentInfo.setTypename("客服帮助");
			break;
		case 6:
			contentInfo.setTypename("隐私说明");
			break;
		}
		contentInfoManagerService.updateContentInfo(contentInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 基础信息修改功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.UPDATECONTENT })
	public String info() {
		if (id > 0) {
			contentInfo = contentInfoManagerService.getContentInfo(id);
		}
		// 初始化信息
		if (contentInfo == null) {
			contentInfo = new ContentInfo();
			contentInfo.setIsdel(new Integer(0));
			contentInfo.setType(new Integer(1));
			contentInfo.setTitle("");
			contentInfo.setDescription("");
			contentInfo.setSource("");
			contentInfo.setContent("");
			contentInfo.setPicpath("");
			contentInfo.setContenttype(new Integer(0));
			contentInfo.setState(new Integer(1));
			contentInfo.setPtime(new Date());
			contentInfo.setSort(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCONTENT })
	public String list() {
		String hql = " from ContentInfo where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , ptime desc , id desc ";

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

		if (searchtype > 0) {
			hql += " and type =" + searchtype;
			HqlBean hqlBean = new HqlBean(searchtype, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchtype", hqlBean);
			parameter += "&searchtype=" + searchtype;
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = contentInfoManagerService.findContentInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/content/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public ContentInfoManagerService getContentInfoManagerService() {
		return contentInfoManagerService;
	}

	public void setContentInfoManagerService(
			ContentInfoManagerService contentInfoManagerService) {
		this.contentInfoManagerService = contentInfoManagerService;
	}

	public ContentInfo getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(ContentInfo contentInfo) {
		this.contentInfo = contentInfo;
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
