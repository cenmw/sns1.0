package com.cenmw.topic.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.topic.manager.service.TopicLifeAdviceManagerService;
import com.cenmw.topic.po.TopicLifeAdvice;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class TopicLifeAdviceManagerAction extends BaseAction {
	/**
	 * 生活建议 模块
	 */
	private TopicLifeAdviceManagerService topicLifeAdviceManagerService; 
	private TopicLifeAdvice topicLifeAdvice;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 生活建议删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteTOPICCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				topicLifeAdvice = topicLifeAdviceManagerService.getTopicLifeAdvice(new Integer(idarrs[i].trim()));
				topicLifeAdvice.setIsdel(new Integer(1));
				topicLifeAdviceManagerService.updateTopicLifeAdvice(topicLifeAdvice);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 生活建议添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDTOPICCLASS })
	public String save() {
		String msg = "修改成功！";
		if (topicLifeAdvice.getId() == null) {
			msg = "添加成功！";
			topicLifeAdvice.setIsdel(new Integer(0));
			topicLifeAdvice.setCtime(new Date());
			topicLifeAdviceManagerService.saveTopicLifeAdvice(topicLifeAdvice);
			topicLifeAdvice.setSort(topicLifeAdvice.getId());
		}else{
			topicLifeAdviceManagerService.updateTopicLifeAdvice(topicLifeAdvice);
		}
		
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 生活建议查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWTOPICCLASS })
	public String info() {
		if (id > 0) {
			topicLifeAdvice = topicLifeAdviceManagerService.getTopicLifeAdvice(id);
		}
		// 初始化信息
		if (topicLifeAdvice == null) {
			topicLifeAdvice = new TopicLifeAdvice();
			topicLifeAdvice.setIsdel(new Integer(0));
			topicLifeAdvice.setSort(new Integer(0));
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
		String hql = " from TopicLifeAdvice where isdel=0 ";
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
		pageBean = topicLifeAdviceManagerService.findTopicLifeAdviceHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/topiclifeadvice/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public TopicLifeAdviceManagerService getTopicLifeAdviceManagerService() {
		return topicLifeAdviceManagerService;
	}

	public void setTopicLifeAdviceManagerService(
			TopicLifeAdviceManagerService topicLifeAdviceManagerService) {
		this.topicLifeAdviceManagerService = topicLifeAdviceManagerService;
	}

	public TopicLifeAdvice getTopicLifeAdvice() {
		return topicLifeAdvice;
	}

	public void setTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		this.topicLifeAdvice = topicLifeAdvice;
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
