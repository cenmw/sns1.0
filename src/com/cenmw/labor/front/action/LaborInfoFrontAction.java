package com.cenmw.labor.front.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.labor.front.service.LaborClassFrontService;
import com.cenmw.labor.front.service.LaborInfoFrontService;
import com.cenmw.labor.front.service.LaborReplyInfoFrontService;
import com.cenmw.labor.po.LaborClass;
import com.cenmw.labor.po.LaborInfo;
import com.cenmw.labor.po.LaborReplyInfo;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class LaborInfoFrontAction extends BaseAction {
	/**
	 * 活动信息 模块
	 */
	private LaborInfoFrontService laborInfoFrontService;
	private LaborClassFrontService laborClassFrontService;
	private LaborReplyInfoFrontService laborReplyInfoFrontService;
	private MemberInfoFrontService memberInfoFrontService;
	private LaborInfo laborInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private List laborclasslist; // 管理员自定义活动分类
	private List laborreplylist; // 活动回复信息
	private int replyid;

	/**
	 * 同意活动回复添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDLABORREPLY })
	public String savereply() {
		if (id > 0) {
			laborInfo = laborInfoFrontService.getLaborInfo(id);
		}
		if (replyid > 0) {
			LaborReplyInfo laborReplyInfo = laborReplyInfoFrontService
					.getLaborReplyInfo(replyid);
			if (laborReplyInfo != null) {
				laborReplyInfoFrontService.updateLaborReplyInfo(laborReplyInfo);
				// 并给当前会员添加积分操作
				// 预留。。。
			}
		}
		return SUCCESS;
	}

	/**
	 * 活动信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteLABOR })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				laborInfo = laborInfoFrontService.getLaborInfo(new Integer(
						idarrs[i].trim()));
				laborInfo.setIsdel(new Integer(1));
				laborInfoFrontService.updateLaborInfo(laborInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 活动信息添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDLABOR })
	public String save() {
		String msg = "修改成功！";
		if (laborInfo.getId() == null) {
			msg = "添加成功！";
			laborInfo.setIsdel(new Integer(0));
			laborInfo.setCtime(new Date());
			laborInfoFrontService.saveLaborInfo(laborInfo);
			laborInfo.setSort(laborInfo.getId());
		}
		int cid = laborInfo.getCid().intValue();
		if (cid > 0) {
			LaborClass laborClass = laborClassFrontService.getLaborClass(cid);
			if (laborClass != null) {
				laborInfo.setClassname(laborClass.getTitle());
			}
		}
		laborInfoFrontService.updateLaborInfo(laborInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 活动信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLABOR })
	public String info() {
		laborclasslist = laborClassFrontService.findLaborClassInList();
		if (id > 0) {
			laborInfo = laborInfoFrontService.getLaborInfo(id);
			laborreplylist = laborReplyInfoFrontService
					.findLaborReplyInfoInList(id);
			laborreplylist = getnewrlist(laborreplylist);
		}
		// 初始化信息
		if (laborInfo == null) {
			laborInfo = new LaborInfo();
			laborInfo.setIsdel(new Integer(0));
			laborInfo.setCid(new Integer(0));
			laborInfo.setTitle("");
			laborInfo.setDescription("");
			laborInfo.setContent("");
			laborInfo.setState(new Integer(1));
			laborInfo.setSort(new Integer(0));
			laborInfo.setScore(new Integer(5));
		}
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWLABOR })
	public String list() {
		laborclasslist = laborClassFrontService.findLaborClassInList();
		String hql = " from LaborInfo where isdel=0 and state=1 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id desc ";

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

		// 判断排序条件
		pageBean = laborInfoFrontService.findLaborInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/labor/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/gopage/gopage.html"));
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
				LaborInfo cinfo = (LaborInfo) pagelist.get(i);
				cinfo.setReplycount(laborReplyInfoFrontService
						.findLaborReplyInfoCount(cinfo.getId()));
				newlist.add(cinfo);
			}
		}
		return newlist;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewrlist(List list) {
		List newlist = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LaborReplyInfo crinfo = (LaborReplyInfo) list.get(i);
				crinfo.setMemberInfo(memberInfoFrontService
						.getMemberInfoById(crinfo.getMid()));
				newlist.add(crinfo);
			}
		}
		return newlist;
	}

	public LaborInfoFrontService getLaborInfoFrontService() {
		return laborInfoFrontService;
	}

	public void setLaborInfoFrontService(
			LaborInfoFrontService laborInfoFrontService) {
		this.laborInfoFrontService = laborInfoFrontService;
	}

	public LaborInfo getLaborInfo() {
		return laborInfo;
	}

	public void setLaborInfo(LaborInfo laborInfo) {
		this.laborInfo = laborInfo;
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

	public int getSearchcid() {
		return searchcid;
	}

	public void setSearchcid(int searchcid) {
		this.searchcid = searchcid;
	}

	public LaborClassFrontService getLaborClassFrontService() {
		return laborClassFrontService;
	}

	public void setLaborClassFrontService(
			LaborClassFrontService laborClassFrontService) {
		this.laborClassFrontService = laborClassFrontService;
	}

	public List getLaborclasslist() {
		return laborclasslist;
	}

	public void setLaborclasslist(List laborclasslist) {
		this.laborclasslist = laborclasslist;
	}

	public LaborReplyInfoFrontService getLaborReplyInfoFrontService() {
		return laborReplyInfoFrontService;
	}

	public void setLaborReplyInfoFrontService(
			LaborReplyInfoFrontService laborReplyInfoFrontService) {
		this.laborReplyInfoFrontService = laborReplyInfoFrontService;
	}

	public List getLaborreplylist() {
		return laborreplylist;
	}

	public void setLaborreplylist(List laborreplylist) {
		this.laborreplylist = laborreplylist;
	}

	public int getReplyid() {
		return replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

	public MemberInfoFrontService getMemberInfoFrontService() {
		return memberInfoFrontService;
	}

	public void setMemberInfoFrontService(
			MemberInfoFrontService memberInfoFrontService) {
		this.memberInfoFrontService = memberInfoFrontService;
	}

}
