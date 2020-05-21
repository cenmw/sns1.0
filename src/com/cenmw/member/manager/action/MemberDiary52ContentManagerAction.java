package com.cenmw.member.manager.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberDiary52ContentManagerService;
import com.cenmw.member.po.MemberDiary52Content;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;


public class MemberDiary52ContentManagerAction extends BaseAction {
	/**
	 * 会员52周书籍内容 模块
	 */
	private MemberDiary52ContentManagerService memberDiary52ContentManagerService; 
	private MemberInfoManagerService memberInfoManagerService; 
	private MemberDiary52Content memberDiary52Content;
	private int id;
	private String ids;
	private String backUrl;

	/**
	 * 会员52周书籍内容删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERBLOG })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberDiary52Content = memberDiary52ContentManagerService.getMemberDiary52Content(new Integer(idarrs[i].trim()));
				memberDiary52Content.setIsdel(new Integer(1));
				memberDiary52ContentManagerService.updateMemberDiary52Content(memberDiary52Content);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员52周书籍内容添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERBLOG })
	public String save() {
		String msg = "修改成功！";
		if (memberDiary52Content.getId() == null) {
			msg = "添加成功！";
			memberDiary52Content.setSort(new Integer(0));
			memberDiary52Content.setIsdel(new Integer(0));
			memberDiary52Content.setCtime(new Date());
			memberDiary52ContentManagerService.saveMemberDiary52Content(memberDiary52Content);
		}
		memberDiary52ContentManagerService.updateMemberDiary52Content(memberDiary52Content);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员52周书籍内容查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOG })
	public String info() {
		if (id > 0) {
			memberDiary52Content = memberDiary52ContentManagerService.getMemberDiary52Content(id);
		}
		// 初始化信息
		if (memberDiary52Content == null) {
			memberDiary52Content = new MemberDiary52Content();
			memberDiary52Content.setType(0);
		} 
		return SUCCESS;
	}

	/**
	 * 会员52周书籍内容列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOG })
	public String list() {
		String hql = " from MemberDiary52Content where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort asc, id desc ";
		pageSize = 20;
		// 判断排序条件
		pageBean = memberDiary52ContentManagerService.findMemberDiary52ContentHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/zhoucontent/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public MemberDiary52ContentManagerService getMemberDiary52ContentManagerService() {
		return memberDiary52ContentManagerService;
	}

	public void setMemberDiary52ContentManagerService(
			MemberDiary52ContentManagerService memberDiary52ContentManagerService) {
		this.memberDiary52ContentManagerService = memberDiary52ContentManagerService;
	}

	public MemberDiary52Content getMemberDiary52Content() {
		return memberDiary52Content;
	}

	public void setMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		this.memberDiary52Content = memberDiary52Content;
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

}
