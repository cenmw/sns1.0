package com.cenmw.topic.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.topic.manager.service.TopicClassManagerService;
import com.cenmw.topic.po.TopicClass;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class TopicClassManagerAction extends BaseAction {
	/**
	 * 测试分类 模块
	 */
	private TopicClassManagerService topicClassManagerService; 
	private TopicClass topicClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 测试分类删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteTOPICCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				topicClass = topicClassManagerService.getTopicClass(new Integer(idarrs[i].trim()));
				topicClass.setIsdel(new Integer(1));
				topicClassManagerService.updateTopicClass(topicClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 测试分类添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDTOPICCLASS })
	public String save() {
		String msg = "修改成功！";
		if (topicClass.getId() == null) {
			msg = "添加成功！";
			topicClass.setIsdel(new Integer(0));
			topicClass.setCtime(new Date());
			topicClassManagerService.saveTopicClass(topicClass);
			topicClass.setSort(topicClass.getId());
		}else{
			topicClassManagerService.updateTopicClass(topicClass);
		}
		
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 测试分类查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWTOPICCLASS })
	public String info() {
		if (id > 0) {
			topicClass = topicClassManagerService.getTopicClass(id);
		}
		// 初始化信息
		if (topicClass == null) {
			topicClass = new TopicClass();
			topicClass.setIsdel(new Integer(0));
			topicClass.setTitle("");
			topicClass.setDescription("");
			topicClass.setPicpath("");
			topicClass.setSort(new Integer(0));
			topicClass.setPicpath("/member/images/common/no_photo.png");
		} 
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWTOPICCLASS })
	public String list() {
		String hql = " from TopicClass where isdel=0 ";
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
		pageBean = topicClassManagerService.findTopicClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/topicclass/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public TopicClassManagerService getTopicClassManagerService() {
		return topicClassManagerService;
	}

	public void setTopicClassManagerService(
			TopicClassManagerService topicClassManagerService) {
		this.topicClassManagerService = topicClassManagerService;
	}

	public TopicClass getTopicClass() {
		return topicClass;
	}

	public void setTopicClass(TopicClass topicClass) {
		this.topicClass = topicClass;
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
