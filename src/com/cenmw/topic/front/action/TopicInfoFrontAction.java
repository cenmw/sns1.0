package com.cenmw.topic.front.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.topic.front.service.TopicInfoFrontService;
import com.cenmw.topic.po.TopicInfo;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class TopicInfoFrontAction extends BaseAction {
	/**
	 * 测试中心 模块
	 */
	private TopicInfoFrontService topicInfoFrontService; 
	private TopicInfo topicInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchtype = 0;


	/**
	 * 测试中心查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			topicInfo = topicInfoFrontService.getTopicInfo(id);
		}
		// 初始化信息
		if (topicInfo == null) {
			topicInfo = new TopicInfo();
			topicInfo.setIsdel(new Integer(0));
			topicInfo.setCid(new Integer(0));
			topicInfo.setTitle("");
			topicInfo.setDescription("");
			topicInfo.setContent("");
			topicInfo.setPicpath("");
			topicInfo.setState(new Integer(1));
			topicInfo.setSort(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from TopicInfo where isdel=0";
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

		// 判断排序条件
		pageBean = topicInfoFrontService.findTopicInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/topic/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/gopage/gopage.html"));
		return SUCCESS;
	}

	public TopicInfoFrontService getTopicInfoFrontService() {
		return topicInfoFrontService;
	}

	public void setTopicInfoFrontService(
			TopicInfoFrontService topicInfoFrontService) {
		this.topicInfoFrontService = topicInfoFrontService;
	}

	public TopicInfo getTopicInfo() {
		return topicInfo;
	}

	public void setTopicInfo(TopicInfo topicInfo) {
		this.topicInfo = topicInfo;
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
