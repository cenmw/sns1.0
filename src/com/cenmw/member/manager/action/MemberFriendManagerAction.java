package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberFriendManagerService;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberFriendManagerAction extends BaseAction {
	/**
	 * 会员好友申请 模块
	 */
	private MemberFriendManagerService memberFriendManagerService;
	private MemberInfoManagerService memberInfoManagerService;
	private MemberFriend memberFriend;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchcontent = "";

	/**
	 * 会员好友申请删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERFRIEND })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberFriend = memberFriendManagerService
						.getMemberFriend(new Integer(idarrs[i].trim()));
				memberFriend.setIsdel(new Integer(1));
				memberFriendManagerService.updateMemberFriend(memberFriend);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员好友申请添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERFRIEND })
	public String save() {
		String msg = "修改成功！";
		if (memberFriend.getId() == null) {
			msg = "添加成功！";
			memberFriend.setIsdel(new Integer(0));
			memberFriend.setCtime(new Date());
			memberFriendManagerService.saveMemberFriend(memberFriend);
		}
		memberFriendManagerService.updateMemberFriend(memberFriend);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员好友申请查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERFRIEND })
	public String info() {
		if (id > 0) {
			memberFriend = memberFriendManagerService.getMemberFriend(id);
			memberFriend.setMemberInfo(memberInfoManagerService
					.getMemberInfo(memberFriend.getMid()));
			memberFriend.setRmemberInfo(memberInfoManagerService
					.getMemberInfo(memberFriend.getFid()));
		}
		// 初始化信息
		if (memberFriend == null) {
			memberFriend = new MemberFriend();
			memberFriend.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERFRIEND })
	public String list() {
		String hql = " from MemberFriend where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchcontent != null && searchcontent.length() > 0) {
			hql += " and content like '%" + searchcontent + "%'";
			HqlBean hqlBean = new HqlBean(searchcontent, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchcontent", hqlBean);
			try {
				parameter += "&searchcontent="
						+ StringUtil.URLEncode(searchcontent);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = memberFriendManagerService.findMemberFriendHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/manager/memberfriend/list");
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
				MemberFriend mfriend = (MemberFriend) pagelist.get(i);
				mfriend.setMemberInfo(memberInfoManagerService
						.getMemberInfo(mfriend.getMid()));
				mfriend.setRmemberInfo(memberInfoManagerService
						.getMemberInfo(mfriend.getFid()));
				newlist.add(mfriend);
			}
		}
		return newlist;
	}

	public MemberFriendManagerService getMemberFriendManagerService() {
		return memberFriendManagerService;
	}

	public void setMemberFriendManagerService(
			MemberFriendManagerService memberFriendManagerService) {
		this.memberFriendManagerService = memberFriendManagerService;
	}

	public MemberFriend getMemberFriend() {
		return memberFriend;
	}

	public void setMemberFriend(MemberFriend memberFriend) {
		this.memberFriend = memberFriend;
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

	public String getSearchcontent() {
		return searchcontent;
	}

	public void setSearchcontent(String searchcontent) {
		this.searchcontent = searchcontent;
	}

}
