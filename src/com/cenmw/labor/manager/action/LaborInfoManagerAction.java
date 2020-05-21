package com.cenmw.labor.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.labor.manager.service.LaborClassManagerService;
import com.cenmw.labor.manager.service.LaborInfoManagerService;
import com.cenmw.labor.manager.service.LaborReplyInfoManagerService;
import com.cenmw.labor.po.LaborClass;
import com.cenmw.labor.po.LaborInfo;
import com.cenmw.labor.po.LaborReplyInfo;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class LaborInfoManagerAction extends BaseAction {
	/**
	 * 活动信息 模块
	 */
	private LaborInfoManagerService laborInfoManagerService;
	private LaborClassManagerService laborClassManagerService;
	private LaborReplyInfoManagerService laborReplyInfoManagerService;
	private MemberInfoManagerService memberInfoManagerService;
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
			laborInfo = laborInfoManagerService.getLaborInfo(id);
		}
		if (replyid > 0) {
			LaborReplyInfo laborReplyInfo = laborReplyInfoManagerService
					.getLaborReplyInfo(replyid);
			if (laborReplyInfo != null) {
				laborReplyInfoManagerService
						.updateLaborReplyInfo(laborReplyInfo);
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
				laborInfo = laborInfoManagerService.getLaborInfo(new Integer(
						idarrs[i].trim()));
				laborInfo.setIsdel(new Integer(1));
				laborInfoManagerService.updateLaborInfo(laborInfo);
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
			laborInfoManagerService.saveLaborInfo(laborInfo);
			laborInfo.setSort(laborInfo.getId());
		}
		int cid = laborInfo.getCid().intValue();
		if (cid > 0) {
			LaborClass laborClass = laborClassManagerService.getLaborClass(cid);
			if (laborClass != null) {
				laborInfo.setClassname(laborClass.getTitle());
			}
		}
		laborInfoManagerService.updateLaborInfo(laborInfo);
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
		laborclasslist = laborClassManagerService.findLaborClassInList();
		if (id > 0) {
			laborInfo = laborInfoManagerService.getLaborInfo(id);
			laborInfo.setReplycount(laborReplyInfoManagerService
					.findLaborReplyInfoCount(laborInfo.getId()));
			laborInfo.setMemberInfo(memberInfoManagerService
					.getMemberInfo(laborInfo.getMid()));
			
			laborreplylist = laborReplyInfoManagerService
					.findLaborReplyInfoInList(id);
			laborreplylist = getnewrlist(laborreplylist);
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
		laborclasslist = laborClassManagerService.findLaborClassInList();
		String hql = " from LaborInfo where isdel=0 ";
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
		pageSize = 20;
		// 判断排序条件
		pageBean = laborInfoManagerService.findLaborInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/labor/list");
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
				LaborInfo cinfo = (LaborInfo) pagelist.get(i);
				cinfo.setReplycount(laborReplyInfoManagerService
						.findLaborReplyInfoCount(cinfo.getId()));
				cinfo.setMemberInfo(memberInfoManagerService
						.getMemberInfo(cinfo.getMid()));
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
				crinfo.setMemberInfo(memberInfoManagerService
						.getMemberInfo(crinfo.getMid()));
				newlist.add(crinfo);
			}
		}
		return newlist;
	}

	public LaborInfoManagerService getLaborInfoManagerService() {
		return laborInfoManagerService;
	}

	public void setLaborInfoManagerService(
			LaborInfoManagerService laborInfoManagerService) {
		this.laborInfoManagerService = laborInfoManagerService;
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

	public LaborClassManagerService getLaborClassManagerService() {
		return laborClassManagerService;
	}

	public void setLaborClassManagerService(
			LaborClassManagerService laborClassManagerService) {
		this.laborClassManagerService = laborClassManagerService;
	}

	public List getLaborclasslist() {
		return laborclasslist;
	}

	public void setLaborclasslist(List laborclasslist) {
		this.laborclasslist = laborclasslist;
	}

	public LaborReplyInfoManagerService getLaborReplyInfoManagerService() {
		return laborReplyInfoManagerService;
	}

	public void setLaborReplyInfoManagerService(
			LaborReplyInfoManagerService laborReplyInfoManagerService) {
		this.laborReplyInfoManagerService = laborReplyInfoManagerService;
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

	public MemberInfoManagerService getMemberInfoManagerService() {
		return memberInfoManagerService;
	}

	public void setMemberInfoManagerService(
			MemberInfoManagerService memberInfoManagerService) {
		this.memberInfoManagerService = memberInfoManagerService;
	}

}
