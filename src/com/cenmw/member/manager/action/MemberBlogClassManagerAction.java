package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberBlogClassManagerService;
import com.cenmw.member.po.MemberBlogClass;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberBlogClassManagerAction extends BaseAction {
	/**
	 * 会员日志分类 模块
	 */
	private MemberBlogClassManagerService memberBlogClassManagerService; 
	private MemberInfoManagerService memberInfoManagerService; 
	private MemberBlogClass memberBlogClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员日志分类删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERBLOGCLASS })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberBlogClass = memberBlogClassManagerService.getMemberBlogClass(new Integer(idarrs[i].trim()));
				memberBlogClass.setIsdel(new Integer(1));
				memberBlogClassManagerService.updateMemberBlogClass(memberBlogClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员日志分类添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERBLOGCLASS })
	public String save() {
		String msg = "修改成功！";
		if (memberBlogClass.getId() == null) {
			msg = "添加成功！";
			memberBlogClass.setIsdel(new Integer(0));
			memberBlogClass.setCtime(new Date());
			memberBlogClassManagerService.saveMemberBlogClass(memberBlogClass);
		}
		memberBlogClassManagerService.updateMemberBlogClass(memberBlogClass);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员日志分类查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOGCLASS })
	public String info() {
		if (id > 0) {
			memberBlogClass = memberBlogClassManagerService.getMemberBlogClass(id);
			memberBlogClass.setMemberInfo(memberInfoManagerService.getMemberInfo(memberBlogClass.getMid()));
		}
		// 初始化信息
		if (memberBlogClass == null) {
			memberBlogClass = new MemberBlogClass();
			memberBlogClass.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOGCLASS })
	public String list() {
		String hql = " from MemberBlogClass where type=1 and isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";
		pageSize = 20;
		if (searchtitle != null && searchtitle.length()>0) {
			hql += " and title like '%" + searchtitle + "%'";
			HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchtitle", hqlBean);
			try {
				parameter += "&searchtitle=" + StringUtil.URLEncode(searchtitle);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// 判断排序条件
		pageBean = memberBlogClassManagerService.findMemberBlogClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/memberblogclass/list");
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
				MemberBlogClass mblogclass = (MemberBlogClass) pagelist.get(i);
				mblogclass.setMemberInfo(memberInfoManagerService.getMemberInfo(mblogclass.getMid()));
				newlist.add(mblogclass);
			}
		}
		return newlist;
	}
	public MemberBlogClassManagerService getMemberBlogClassManagerService() {
		return memberBlogClassManagerService;
	}

	public void setMemberBlogClassManagerService(
			MemberBlogClassManagerService memberBlogClassManagerService) {
		this.memberBlogClassManagerService = memberBlogClassManagerService;
	}

	public MemberBlogClass getMemberBlogClass() {
		return memberBlogClass;
	}

	public void setMemberBlogClass(MemberBlogClass memberBlogClass) {
		this.memberBlogClass = memberBlogClass;
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
