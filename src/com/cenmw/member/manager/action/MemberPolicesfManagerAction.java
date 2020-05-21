package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberReportManagerService;
import com.cenmw.member.po.MemberReport;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberPolicesfManagerAction extends BaseAction {
	/**
	 * 会员释放 模块
	 */
	private MemberReportManagerService memberReportManagerService;
	private MemberInfoManagerService memberInfoManagerService;
	private MemberReport memberReport;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员释放删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERPOLICESF })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberReport = memberReportManagerService
						.getMemberReport(new Integer(idarrs[i].trim()));
				memberReport.setIsdel(new Integer(1));
				memberReportManagerService.updateMemberReport(memberReport);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员释放添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERPOLICESF })
	public String save() {
		String msg = "修改成功！";
		if (memberReport.getId() == null) {
			msg = "添加成功！";
			memberReport.setIsdel(new Integer(0));
			memberReport.setCtime(new Date());
			memberReportManagerService.saveMemberReport(memberReport);
		}
		memberReportManagerService.updateMemberReport(memberReport);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员释放查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERPOLICESF })
	public String info() {
		if (id > 0) {
			memberReport = memberReportManagerService.getMemberReport(id);
			memberReport.setMemberInfo(memberInfoManagerService
					.getMemberInfo(memberReport.getMid()));
			memberReport.setRmemberInfo(memberInfoManagerService
					.getMemberInfo(memberReport.getRid()));
		}
		// 初始化信息
		if (memberReport == null) {
			memberReport = new MemberReport();
			memberReport.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERPOLICESF })
	public String list() {
		String hql = " from MemberReport where state=2 and isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchtitle != null && searchtitle.length() > 0) {
			hql += " and classname like '%" + searchtitle + "%'";
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
		pageSize = 20;
		// 判断排序条件
		pageBean = memberReportManagerService.findMemberReportHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/manager/memberpolicesf/list");
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
				MemberReport mpolicesf = (MemberReport) pagelist.get(i);
				mpolicesf.setMemberInfo(memberInfoManagerService
						.getMemberInfo(mpolicesf.getMid()));
				mpolicesf.setRmemberInfo(memberInfoManagerService
						.getMemberInfo(mpolicesf.getRid()));
				newlist.add(mpolicesf);
			}
		}
		return newlist;
	}

	public MemberReportManagerService getMemberReportManagerService() {
		return memberReportManagerService;
	}

	public void setMemberReportManagerService(
			MemberReportManagerService memberReportManagerService) {
		this.memberReportManagerService = memberReportManagerService;
	}

	public MemberReport getMemberReport() {
		return memberReport;
	}

	public void setMemberReport(MemberReport memberReport) {
		this.memberReport = memberReport;
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

	public MemberInfoManagerService getMemberInfoManagerService() {
		return memberInfoManagerService;
	}

	public void setMemberInfoManagerService(
			MemberInfoManagerService memberInfoManagerService) {
		this.memberInfoManagerService = memberInfoManagerService;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

}
