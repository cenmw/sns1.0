package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberReport;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberPoliceCenterAction extends BaseAction {
	/**
	 * 警察局 模块
	 */
	private MemberReportCenterService memberReportCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberReport memberReport;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 警察局删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberReport = memberReportCenterService
						.getMemberReport(new Integer(idarrs[i].trim()));
				memberReport.setIsdel(new Integer(1));
				memberReportCenterService.updateMemberReport(memberReport);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 警察局添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (memberReport.getId() == null) {
			msg = "添加成功！";
			memberReport.setIsdel(new Integer(0));
			memberReport.setCtime(new Date());
			memberReportCenterService.saveMemberReport(memberReport);
		} else {
			if (memberReport.getState() == 2) {
				memberReport.setSftime(new Date());
			}
		}
		memberReportCenterService.updateMemberReport(memberReport);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 警察局查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberReport = memberReportCenterService.getMemberReport(id);
			memberReport.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberReport.getMid()));
			memberReport.setRmemberInfo(memberInfoCenterService
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
	 *拘留记录列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberReport where (state=1 or state=2) and isdel=0 and rid="+memberInfo.getId().intValue();
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

		// 判断排序条件
		pageBean = memberReportCenterService.findMemberReportHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/policelist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 好友拘留记录列表查看功能
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId());
		if (memberInfo.getType() == 1) {
			fids = memberFriendCenterService.findMemberAllCFriends(memberInfo.getId().intValue(),memberInfo.getType().intValue());
		}
		
		if (fids.length() > 0) {
			String hql = " from MemberReport where (state=1 or state=2) and isdel=0 and rid in ("
					+ fids + ")";
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

			// 判断排序条件
			pageBean = memberReportCenterService.findMemberReportHQLList(hql,
					sortstr, map, currentPage, pageSize);
			pageBean.setAction("/membercenter/policelist");
			backUrl = pageBean.getAction() + "?pageSize="
					+ pageBean.getPageSize() + "&currentPage="
					+ pageBean.getCurrentPage() + parameter;
			pageBean.setGopageTemplate(request
					.getRealPath("/member/center/gopage/gopage.html"));
			pageBean.setPageList(getnewlist(pageBean.getPageList()));
		}
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
				MemberReport mpolice = (MemberReport) pagelist.get(i);
				mpolice.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mpolice.getMid()));
				mpolice.setRmemberInfo(memberInfoCenterService
						.getMemberInfo(mpolice.getRid()));
				newlist.add(mpolice);
			}
		}
		return newlist;
	}

	public MemberReportCenterService getMemberReportCenterService() {
		return memberReportCenterService;
	}

	public void setMemberReportCenterService(
			MemberReportCenterService memberReportCenterService) {
		this.memberReportCenterService = memberReportCenterService;
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

	public MemberInfoCenterService getMemberInfoCenterService() {
		return memberInfoCenterService;
	}

	public void setMemberInfoCenterService(
			MemberInfoCenterService memberInfoCenterService) {
		this.memberInfoCenterService = memberInfoCenterService;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

}
