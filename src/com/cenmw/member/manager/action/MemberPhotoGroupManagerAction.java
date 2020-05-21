package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberPhotoGroupManagerService;
import com.cenmw.member.po.MemberPhotoGroup;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberPhotoGroupManagerAction extends BaseAction {
	/**
	 * 会员相册 模块
	 */
	private MemberPhotoGroupManagerService memberPhotoGroupManagerService; 
	private MemberInfoManagerService memberInfoManagerService; 
	private MemberPhotoGroup memberPhotoGroup;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员相册删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERPHOTOGROUP })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberPhotoGroup = memberPhotoGroupManagerService.getMemberPhotoGroup(new Integer(idarrs[i].trim()));
				memberPhotoGroup.setIsdel(new Integer(1));
				memberPhotoGroupManagerService.updateMemberPhotoGroup(memberPhotoGroup);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员相册添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERPHOTOGROUP })
	public String save() {
		String msg = "修改成功！";
		if (memberPhotoGroup.getId() == null) {
			msg = "添加成功！";
			memberPhotoGroup.setIsdel(new Integer(0));
			memberPhotoGroup.setCtime(new Date());
			memberPhotoGroupManagerService.saveMemberPhotoGroup(memberPhotoGroup);
		}
		memberPhotoGroupManagerService.updateMemberPhotoGroup(memberPhotoGroup);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员相册查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERPHOTOGROUP })
	public String info() {
		if (id > 0) {
			memberPhotoGroup = memberPhotoGroupManagerService.getMemberPhotoGroup(id);
			memberPhotoGroup.setMemberInfo(memberInfoManagerService.getMemberInfo(memberPhotoGroup.getMid()));
		}
		// 初始化信息
		if (memberPhotoGroup == null) {
			memberPhotoGroup = new MemberPhotoGroup();
			memberPhotoGroup.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员相册列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERPHOTOGROUP })
	public String list() {
		String hql = " from MemberPhotoGroup where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

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
		pageSize = 20;
		// 判断排序条件
		pageBean = memberPhotoGroupManagerService.findMemberPhotoGroupHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/memberphotogroup/list");
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
				MemberPhotoGroup mblog = (MemberPhotoGroup) pagelist.get(i);
				mblog.setMemberInfo(memberInfoManagerService.getMemberInfo(mblog.getMid()));
				newlist.add(mblog);
			}
		}
		return newlist;
	}
	public MemberPhotoGroupManagerService getMemberPhotoGroupManagerService() {
		return memberPhotoGroupManagerService;
	}

	public void setMemberPhotoGroupManagerService(
			MemberPhotoGroupManagerService memberPhotoGroupManagerService) {
		this.memberPhotoGroupManagerService = memberPhotoGroupManagerService;
	}

	public MemberPhotoGroup getMemberPhotoGroup() {
		return memberPhotoGroup;
	}

	public void setMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		this.memberPhotoGroup = memberPhotoGroup;
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
