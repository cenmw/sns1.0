package com.cenmw.member.manager.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberDiary52ManagerService;
import com.cenmw.member.po.MemberDiary52;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;


public class MemberDiary52ManagerAction extends BaseAction {
	/**
	 * 会员52周 模块
	 */
	private MemberDiary52ManagerService memberDiary52ManagerService; 
	private MemberInfoManagerService memberInfoManagerService; 
	private MemberDiary52 memberDiary52;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员52周删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERBLOG })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberDiary52 = memberDiary52ManagerService.getMemberDiary52(new Integer(idarrs[i].trim()));
				memberDiary52.setIsdel(new Integer(1));
				memberDiary52ManagerService.updateMemberDiary52(memberDiary52);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员52周添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERBLOG })
	public String save() {
		String msg = "修改成功！";
		if (memberDiary52.getId() == null) {
			msg = "添加成功！";
			memberDiary52.setIsdel(new Integer(0));
			memberDiary52.setCtime(new Date());
			memberDiary52ManagerService.saveMemberDiary52(memberDiary52);
		}
		memberDiary52ManagerService.updateMemberDiary52(memberDiary52);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员52周查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOG })
	public String info() {
		if (id > 0) {
			memberDiary52 = memberDiary52ManagerService.getMemberDiary52(id);
			memberDiary52.setMemberInfo(memberInfoManagerService.getMemberInfo(memberDiary52.getMid()));
		}
		// 初始化信息
		if (memberDiary52 == null) {
			memberDiary52 = new MemberDiary52();
			memberDiary52.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员52周列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOG })
	public String list() {
		String hql = " from MemberDiary52 where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		pageSize = 20;
		// 判断排序条件
		pageBean = memberDiary52ManagerService.findMemberDiary52HQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/zhou/list");
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
				MemberDiary52 mblog = (MemberDiary52) pagelist.get(i);
				mblog.setMemberInfo(memberInfoManagerService.getMemberInfo(mblog.getMid()));
				newlist.add(mblog);
			}
		}
		return newlist;
	}
	public MemberDiary52ManagerService getMemberDiary52ManagerService() {
		return memberDiary52ManagerService;
	}

	public void setMemberDiary52ManagerService(
			MemberDiary52ManagerService memberDiary52ManagerService) {
		this.memberDiary52ManagerService = memberDiary52ManagerService;
	}

	public MemberDiary52 getMemberDiary52() {
		return memberDiary52;
	}

	public void setMemberDiary52(MemberDiary52 memberDiary52) {
		this.memberDiary52 = memberDiary52;
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
