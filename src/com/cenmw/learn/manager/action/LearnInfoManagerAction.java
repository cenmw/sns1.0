package com.cenmw.learn.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.manager.service.LearnClassManagerService;
import com.cenmw.learn.manager.service.LearnInfoManagerService;
import com.cenmw.learn.manager.service.LearnWhyManagerService;
import com.cenmw.learn.po.LearnClass;
import com.cenmw.learn.po.LearnInfo;
import com.cenmw.learn.po.LearnResultVO;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class LearnInfoManagerAction extends BaseAction {
	/**
	 * 学习中心 模块
	 */
	private LearnInfoManagerService learnInfoManagerService;
	private LearnClassManagerService learnClassManagerService;
	private LearnWhyManagerService learnWhyManagerService;
	private LearnInfo learnInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private List learnclasslist;

	private List resultlist = null;

	/**
	 * 学习中心删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteLEARN })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				learnInfo = learnInfoManagerService.getLearnInfo(new Integer(
						idarrs[i].trim()));
				learnInfo.setIsdel(new Integer(1));
				learnInfoManagerService.updateLearnInfo(learnInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 学习中心添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDLEARN })
	public String save() {
		String msg = "修改成功！";
		if (learnInfo.getId() == null) {
			msg = "添加成功！";
			learnInfo.setIsdel(new Integer(0));
			learnInfo.setCtime(new Date());
			learnInfoManagerService.saveLearnInfo(learnInfo);
			learnInfo.setSort(learnInfo.getId());
		}
		int cid = learnInfo.getCid().intValue();
		if (cid > 0) {
			LearnClass learnClass = learnClassManagerService.getLearnClass(cid);
			if (learnClass != null) {
				learnInfo.setClassname(learnClass.getTitle());
			}
		}
		learnInfoManagerService.updateLearnInfo(learnInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 学习中心查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLEARN })
	public String info() {
		learnclasslist = learnClassManagerService.findLearnClassInList();
		if (id > 0) {
			learnInfo = learnInfoManagerService.getLearnInfo(id);
			int knumber = learnInfo.getKnumber() == null ? 0 : learnInfo
					.getKnumber();
			String result = learnInfo.getResult() == null ? "" : learnInfo
					.getResult();
			String whyids = learnInfo.getWhyids() == null ? "" : learnInfo
					.getWhyids();
			String[] resultall = result.split("\\|\\|");
			String[] whyidsall = whyids.split("\\|\\|");

			// 查询原因列表
			List whylist = learnWhyManagerService.findLearnWhyInList();
			// 初始化结果列表
			resultlist = new ArrayList();

			if (knumber > 0) {
				if (resultall.length == 0) {
					// 新建操作
					for (int i = 0; i < knumber; i++) {
						LearnResultVO lr = new LearnResultVO();
						lr.setWhylist(whylist);
						resultlist.add(lr);
					}
				} else if (knumber == resultall.length) {
					// 仅仅修改值操作
					for (int i = 0; i < knumber; i++) {
						LearnResultVO lr = new LearnResultVO();
						lr.setResult(resultall[i]);
						lr.setWhyid(new Integer(whyidsall[i]));
						lr.setWhylist(whylist);
						resultlist.add(lr);
					}
				} else if (knumber < resultall.length) {
					// 仅仅修改值操作 当减少空个数时，值取得少的一方，对应不上的由管理者自己负责调整
					for (int i = 0; i < knumber; i++) {
						LearnResultVO lr = new LearnResultVO();
						lr.setResult(resultall[i]);
						lr.setWhyid(new Integer(whyidsall[i]));
						lr.setWhylist(whylist);
						resultlist.add(lr);
					}
				} else if (knumber > resultall.length) {
					// 仅仅修改值操作 当减少空个数时，值取得少的一方，对应不上的由管理者自己负责调整
					if (resultall.length == 1 && StringUtil.isEmpty(result)) {
						LearnResultVO lr = new LearnResultVO();
						lr.setResult(null);
						lr.setWhyid(null);
						lr.setWhylist(whylist);
						resultlist.add(lr);
					} else {
						for (int i = 0; i < resultall.length; i++) {
							LearnResultVO lr = new LearnResultVO();
							lr.setResult(resultall[i]);
							lr.setWhyid(new Integer(whyidsall[i]));
							lr.setWhylist(whylist);
							resultlist.add(lr);
						}
					}
					for (int i = 0; i < (knumber - resultall.length); i++) {
						LearnResultVO lr = new LearnResultVO();
						lr.setResult(null);
						lr.setWhyid(null);
						lr.setWhylist(whylist);
						resultlist.add(lr);
					}
				}
			}

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
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLEARN })
	public String list() {
		learnclasslist = learnClassManagerService.findLearnClassInList();
		String hql = " from LearnInfo where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " code asc ";

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
		pageSize = 20;
		// 判断排序条件
		pageBean = learnInfoManagerService.findLearnInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/learn/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public LearnInfoManagerService getLearnInfoManagerService() {
		return learnInfoManagerService;
	}

	public void setLearnInfoManagerService(
			LearnInfoManagerService learnInfoManagerService) {
		this.learnInfoManagerService = learnInfoManagerService;
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

	public LearnClassManagerService getLearnClassManagerService() {
		return learnClassManagerService;
	}

	public void setLearnClassManagerService(
			LearnClassManagerService learnClassManagerService) {
		this.learnClassManagerService = learnClassManagerService;
	}

	public int getSearchcid() {
		return searchcid;
	}

	public void setSearchcid(int searchcid) {
		this.searchcid = searchcid;
	}

	public List getLearnclasslist() {
		return learnclasslist;
	}

	public void setLearnclasslist(List learnclasslist) {
		this.learnclasslist = learnclasslist;
	}

	public List getResultlist() {
		return resultlist;
	}

	public void setResultlist(List resultlist) {
		this.resultlist = resultlist;
	}

	public LearnWhyManagerService getLearnWhyManagerService() {
		return learnWhyManagerService;
	}

	public void setLearnWhyManagerService(
			LearnWhyManagerService learnWhyManagerService) {
		this.learnWhyManagerService = learnWhyManagerService;
	}

}
