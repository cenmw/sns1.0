package com.cenmw.topic.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.topic.manager.service.TopicClassManagerService;
import com.cenmw.topic.manager.service.TopicInfoManagerService;
import com.cenmw.topic.manager.service.TopicLifeAdviceManagerService;
import com.cenmw.topic.po.TopicClass;
import com.cenmw.topic.po.TopicInfo;
import com.cenmw.topic.po.TopicResultVO;
import com.cenmw.learn.manager.service.LearnClassManagerService;
import com.cenmw.learn.manager.service.LearnWhyManagerService;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class TopicInfoManagerAction extends BaseAction {
	/**
	 * 测试中心 模块
	 */
	private TopicInfoManagerService topicInfoManagerService;
	private TopicClassManagerService topicClassManagerService;
	private LearnWhyManagerService learnWhyManagerService;
	private LearnClassManagerService learnClassManagerService; // 课程推荐
	private TopicLifeAdviceManagerService topicLifeAdviceManagerService; // 生活建议
	private TopicInfo topicInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private int searchtype = 0;
	private List topicclasslist;

	private List resultlist = null;

	/**
	 * 测试中心删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteTOPIC })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				topicInfo = topicInfoManagerService.getTopicInfo(new Integer(
						idarrs[i].trim()));
				topicInfo.setIsdel(new Integer(1));
				topicInfoManagerService.updateTopicInfo(topicInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 测试中心添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDTOPIC })
	public String save() {
		String msg = "修改成功！";
		if (topicInfo.getId() == null) {
			msg = "添加成功！";
			topicInfo.setIsdel(new Integer(0));
			topicInfo.setCtime(new Date());
			topicInfoManagerService.saveTopicInfo(topicInfo);
			topicInfo.setSort(topicInfo.getId());
		}
		int cid = topicInfo.getCid().intValue();
		if (cid > 0) {
			TopicClass topicClass = topicClassManagerService.getTopicClass(cid);
			if (topicClass != null) {
				topicInfo.setClassname(topicClass.getTitle());
			}
		}
		topicInfoManagerService.updateTopicInfo(topicInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 测试中心查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWTOPIC })
	public String info() {
		topicclasslist = topicClassManagerService.findTopicClassInList();
		if (id > 0) {
			topicInfo = topicInfoManagerService.getTopicInfo(id);
			int knumber = topicInfo.getKnumber() == null ? 0 : topicInfo
					.getKnumber();
			String result = topicInfo.getResult() == null ? "" : topicInfo
					.getResult();
			String whyids = topicInfo.getWhyids() == null ? "" : topicInfo
					.getWhyids();
			String lcids = topicInfo.getLcids() == null ? "" : topicInfo
					.getLcids();
			String ttaids = topicInfo.getTtaids() == null ? "" : topicInfo
					.getTtaids();
			String[] resultall = result.split("\\|\\|");
			String[] whyidsall = whyids.split("\\|\\|");
			String[] lcidsall = lcids.split("\\|\\|");
			String[] ttaidsall = ttaids.split("\\|\\|");

			// 查询原因列表
			List whylist = learnWhyManagerService.findLearnWhyInList();
			// 课程分类列表
			List learnclasslist = learnClassManagerService
					.findLearnClassInList();
			// 生活建议列表
			List lifeadvicelist = topicLifeAdviceManagerService
					.findTopicLifeAdviceInList();
			// 初始化结果列表
			resultlist = new ArrayList();

			if (knumber > 0) {
				if (resultall.length == 0 || StringUtil.isEmpty(result)) {
					// 新建操作
					for (int i = 0; i < knumber; i++) {
						TopicResultVO lr = new TopicResultVO();
						lr.setWhylist(whylist);
						lr.setLearnclasslist(learnclasslist);
						lr.setLifeadvicelist(lifeadvicelist);
						resultlist.add(lr);
					}
				} else if (knumber == resultall.length) {
					// 仅仅修改值操作
					for (int i = 0; i < knumber; i++) {
						TopicResultVO lr = new TopicResultVO();
						lr.setResult(resultall[i]);
						lr.setWhyid(new Integer(whyidsall[i]));
						lr.setWhylist(whylist);
						lr.setLcid(new Integer(lcidsall[i]));
						lr.setLearnclasslist(learnclasslist);
						lr.setLaid(new Integer(ttaidsall[i]));
						lr.setLifeadvicelist(lifeadvicelist);
						resultlist.add(lr);
					}
				} else if (knumber < resultall.length) {
					// 仅仅修改值操作 当减少空个数时，值取得少的一方，对应不上的由管理者自己负责调整
					for (int i = 0; i < knumber; i++) {
						TopicResultVO lr = new TopicResultVO();
						lr.setResult(resultall[i]);
						lr.setWhyid(new Integer(whyidsall[i]));
						lr.setWhylist(whylist);
						lr.setLcid(new Integer(lcidsall[i]));
						lr.setLearnclasslist(learnclasslist);
						lr.setLaid(new Integer(ttaidsall[i]));
						lr.setLifeadvicelist(lifeadvicelist);
						resultlist.add(lr);
					}
				} else if (knumber > resultall.length) {
					// 仅仅修改值操作 当减少空个数时，值取得少的一方，对应不上的由管理者自己负责调整
					if (resultall.length == 1 && StringUtil.isEmpty(result)) {
						TopicResultVO lr = new TopicResultVO();
						lr.setResult(null);
						lr.setWhyid(null);
						lr.setWhylist(whylist);
						lr.setLcid(null);
						lr.setLearnclasslist(learnclasslist);
						lr.setLaid(null);
						lr.setLifeadvicelist(lifeadvicelist);
						resultlist.add(lr);
					} else {
						for (int i = 0; i < resultall.length; i++) {
							TopicResultVO lr = new TopicResultVO();
							lr.setResult(resultall[i]);
							lr.setWhyid(new Integer(whyidsall[i]));
							lr.setWhylist(whylist);
							lr.setLcid(new Integer(lcidsall[i]));
							lr.setLearnclasslist(learnclasslist);
							lr.setLaid(new Integer(ttaidsall[i]));
							lr.setLifeadvicelist(lifeadvicelist);
							resultlist.add(lr);
						}
					}
					for (int i = 0; i < (knumber - resultall.length); i++) {
						TopicResultVO lr = new TopicResultVO();
						lr.setResult(null);
						lr.setWhyid(null);
						lr.setWhylist(whylist);
						lr.setLcid(null);
						lr.setLearnclasslist(learnclasslist);
						lr.setLaid(null);
						lr.setLifeadvicelist(lifeadvicelist);
						resultlist.add(lr);
					}
				}
			}
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
			topicInfo.setContenttype(new Integer(0));
			topicInfo.setState(new Integer(1));
			topicInfo.setSort(new Integer(0));
			topicInfo.setScore(new Integer(0));
			topicInfo.setPtime(new Date());
		}
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWTOPIC })
	public String list() {
		topicclasslist = topicClassManagerService.findTopicClassInList();
		String hql = " from TopicInfo where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , ctime desc , id desc ";

		if (searchtitle != null && searchtitle.length() > 0) {
			hql += " and title like '%" + searchtitle + "%'";
			HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchtitle", hqlBean);
			try {
				parameter += "&searchtitle="
						+ StringUtil.URLEncode(searchtitle);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (searchcid > 0) {
			hql += " and cid =" + searchcid;
			HqlBean hqlBean = new HqlBean(searchcid, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchcid", hqlBean);
			parameter += "&searchcid=" + searchcid;
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
		pageBean = topicInfoManagerService.findTopicInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/topic/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public TopicInfoManagerService getTopicInfoManagerService() {
		return topicInfoManagerService;
	}

	public void setTopicInfoManagerService(
			TopicInfoManagerService topicInfoManagerService) {
		this.topicInfoManagerService = topicInfoManagerService;
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

	public TopicClassManagerService getTopicClassManagerService() {
		return topicClassManagerService;
	}

	public void setTopicClassManagerService(
			TopicClassManagerService topicClassManagerService) {
		this.topicClassManagerService = topicClassManagerService;
	}

	public int getSearchcid() {
		return searchcid;
	}

	public void setSearchcid(int searchcid) {
		this.searchcid = searchcid;
	}

	public List getTopicclasslist() {
		return topicclasslist;
	}

	public void setTopicclasslist(List topicclasslist) {
		this.topicclasslist = topicclasslist;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}

	public LearnWhyManagerService getLearnWhyManagerService() {
		return learnWhyManagerService;
	}

	public void setLearnWhyManagerService(
			LearnWhyManagerService learnWhyManagerService) {
		this.learnWhyManagerService = learnWhyManagerService;
	}

	public LearnClassManagerService getLearnClassManagerService() {
		return learnClassManagerService;
	}

	public void setLearnClassManagerService(
			LearnClassManagerService learnClassManagerService) {
		this.learnClassManagerService = learnClassManagerService;
	}

	public TopicLifeAdviceManagerService getTopicLifeAdviceManagerService() {
		return topicLifeAdviceManagerService;
	}

	public void setTopicLifeAdviceManagerService(
			TopicLifeAdviceManagerService topicLifeAdviceManagerService) {
		this.topicLifeAdviceManagerService = topicLifeAdviceManagerService;
	}

	public List getResultlist() {
		return resultlist;
	}

	public void setResultlist(List resultlist) {
		this.resultlist = resultlist;
	}

}
