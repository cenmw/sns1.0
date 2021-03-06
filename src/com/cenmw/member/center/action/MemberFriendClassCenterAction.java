package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberFriendClassCenterService;
import com.cenmw.member.po.MemberFriendClass;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberFriendClassCenterAction extends BaseAction {
	/**
	 * 会员好友申请 模块
	 */
	private MemberFriendClassCenterService memberFriendClassCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberFriendClass memberFriendClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员好友申请删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberFriendClass = memberFriendClassCenterService
						.getMemberFriendClass(new Integer(idarrs[i].trim()));
				memberFriendClass.setIsdel(new Integer(1));
				memberFriendClassCenterService
						.updateMemberFriendClass(memberFriendClass);
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
	public String save() {
		String msg = "修改成功！";
		if (memberFriendClass.getId() == null) {
			msg = "添加成功！";
			memberFriendClass.setIsdel(new Integer(0));
			memberFriendClass.setCtime(new Date());
			memberFriendClassCenterService
					.saveMemberFriendClass(memberFriendClass);
		}
		memberFriendClassCenterService
				.updateMemberFriendClass(memberFriendClass);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员好友申请查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberFriendClass = memberFriendClassCenterService
					.getMemberFriendClass(id);
			memberFriendClass.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberFriendClass.getMid()));
		}
		// 初始化信息
		if (memberFriendClass == null) {
			memberFriendClass = new MemberFriendClass();
			memberFriendClass.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberFriendClass where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

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
		// 判断排序条件
		pageBean = memberFriendClassCenterService
				.findMemberFriendClassHQLList(hql, sortstr, map, currentPage,
						pageSize);
		pageBean.setAction("/membercenter/friendclasslist");
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
				MemberFriendClass mfriendclass = (MemberFriendClass) pagelist
						.get(i);
				mfriendclass.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mfriendclass.getMid()));
				newlist.add(mfriendclass);
			}
		}
		return newlist;
	}

	public MemberFriendClassCenterService getMemberFriendClassCenterService() {
		return memberFriendClassCenterService;
	}

	public void setMemberFriendClassCenterService(
			MemberFriendClassCenterService memberFriendClassCenterService) {
		this.memberFriendClassCenterService = memberFriendClassCenterService;
	}

	public MemberFriendClass getMemberFriendClass() {
		return memberFriendClass;
	}

	public void setMemberFriendClass(MemberFriendClass memberFriendClass) {
		this.memberFriendClass = memberFriendClass;
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

}
