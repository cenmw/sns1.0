package com.cenmw.learn.front.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.front.service.LearnInfoFrontService;
import com.cenmw.learn.po.LearnInfo;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class LearnInfoFrontAction extends BaseAction {
	/**
	 * 学习中心 模块
	 */
	private LearnInfoFrontService learnInfoFrontService; 
	private LearnInfo learnInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchtype = 0;


	/**
	 * 学习中心查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			learnInfo = learnInfoFrontService.getLearnInfo(id);
		}
		// 初始化信息
		if (learnInfo == null) {
			learnInfo = new LearnInfo();
			learnInfo.setIsdel(new Integer(0));
			learnInfo.setCid(new Integer(0));
			learnInfo.setTitle("");
			learnInfo.setDescription("");
			learnInfo.setSource("");
			learnInfo.setContent("");
			learnInfo.setPicpath("");
			learnInfo.setContenttype(new Integer(0));
			learnInfo.setState(new Integer(1));
			learnInfo.setPtime(new Date());
			learnInfo.setSort(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from LearnInfo where isdel=0";
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
		pageBean = learnInfoFrontService.findLearnInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/learn/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/gopage/gopage.html"));
		return SUCCESS;
	}

	public LearnInfoFrontService getLearnInfoFrontService() {
		return learnInfoFrontService;
	}

	public void setLearnInfoFrontService(
			LearnInfoFrontService learnInfoFrontService) {
		this.learnInfoFrontService = learnInfoFrontService;
	}

	public LearnInfo getLearnInfo() {
		return learnInfo;
	}

	public void setLearnInfo(LearnInfo learnInfo) {
		this.learnInfo = learnInfo;
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
