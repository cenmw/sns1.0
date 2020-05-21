package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberReport;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberReportCenterAction extends BaseAction {
	/**
	 * 会员举报 模块
	 */
	private MemberReportCenterService memberReportCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberReport memberReport;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private int type; // 举报的类型
	private int cid;// 举报类型的id
	private Integer rid; // 被举报的会员id
	private String content; // 内容
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员举报删除功能
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
			String msg = "删除成功";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员举报添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "举报成功";
		if (type > 0 && cid > 0 && rid > 0 && content.length() > 0) {
			memberReport = new MemberReport();
			memberReport.setType(type);
			memberReport.setCid(cid);
			memberReport.setRid(rid);
			memberReport.setContent(content);
			memberReport.setMid(memberInfo.getId());
			memberReport.setState(new Integer(0));
			memberReport.setIsdel(new Integer(0));
			memberReport.setCtime(new Date());
			memberReportCenterService.saveMemberReport(memberReport);
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员举报查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		memberReport = memberReportCenterService.getMemberReport(memberInfo.getId().intValue(),cid,rid);
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberReport where state=0 and isdel=0";
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
		pageBean.setAction("/membercenter/policereportlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
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
				MemberReport mreport = (MemberReport) pagelist.get(i);
				mreport.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mreport.getMid()));
				mreport.setRmemberInfo(memberInfoCenterService
						.getMemberInfo(mreport.getRid()));
				newlist.add(mreport);
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
