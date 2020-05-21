package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberLogManagerService;
import com.cenmw.member.po.MemberLog;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberLogManagerAction extends BaseAction {
	/**
	 * 会员访问日志 模块
	 */
	private MemberLogManagerService memberLogManagerService; 
	private MemberInfoManagerService memberInfoManagerService; 
	private MemberLog memberLog;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员访问日志删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERLOG })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberLog = memberLogManagerService.getMemberLog(new Integer(idarrs[i].trim()));
				memberLog.setIsdel(new Integer(1));
				memberLogManagerService.updateMemberLog(memberLog);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员访问日志添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERLOG })
	public String save() {
		String msg = "修改成功！";
		if (memberLog.getId() == null) {
			msg = "添加成功！";
			memberLog.setIsdel(new Integer(0));
			memberLog.setCtime(new Date());
			memberLogManagerService.saveMemberLog(memberLog);
		}
		memberLogManagerService.updateMemberLog(memberLog);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员访问日志查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERLOG })
	public String info() {
		if (id > 0) {
			memberLog = memberLogManagerService.getMemberLog(id);
			memberLog.setMemberInfo(memberInfoManagerService.getMemberInfo(memberLog.getMid()));
		}
		// 初始化信息
		if (memberLog == null) {
			memberLog = new MemberLog();
			memberLog.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员访问日志列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERLOG })
	public String list() {
		String hql = " from MemberLog where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchtitle != null && searchtitle.length()>0) {
			hql += " and classname like '%" + searchtitle + "%'";
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
		pageBean = memberLogManagerService.findMemberLogHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/memberlog/list");
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
				MemberLog mlog = (MemberLog) pagelist.get(i);
				mlog.setMemberInfo(memberInfoManagerService.getMemberInfo(mlog.getMid()));
				newlist.add(mlog);
			}
		}
		return newlist;
	}
	public MemberLogManagerService getMemberLogManagerService() {
		return memberLogManagerService;
	}

	public void setMemberLogManagerService(
			MemberLogManagerService memberLogManagerService) {
		this.memberLogManagerService = memberLogManagerService;
	}

	public MemberLog getMemberLog() {
		return memberLog;
	}

	public void setMemberLog(MemberLog memberLog) {
		this.memberLog = memberLog;
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
