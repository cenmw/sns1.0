package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberCompanyFriendCenterAction extends BaseAction {
	/**
	 * 会员好友申请 模块
	 */
	private MemberFriendCenterService memberFriendCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberFriend memberFriend;
	private MemberInfo memberInfo;
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
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberFriend = memberFriendCenterService
						.getMemberFriend(new Integer(idarrs[i].trim()));
				memberFriend.setIsdel(new Integer(1));
				memberFriendCenterService.updateMemberFriend(memberFriend);
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
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (memberFriend.getId() == null) {
			msg = "添加成功！";
			memberFriend.setMid(memberInfo.getId());
			memberFriend.setIsdel(new Integer(0));
			memberFriend.setCtime(new Date());
			memberFriendCenterService.saveMemberFriend(memberFriend);
		}
		memberFriendCenterService.updateMemberFriend(memberFriend);
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
			memberFriend = memberFriendCenterService.getMemberFriend(id);
			memberFriend.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberFriend.getMid()));
			memberFriend.setRmemberInfo(memberInfoCenterService
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
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberFriend where isdel=0 and mid="+memberInfo.getId();
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

		// 判断排序条件
		pageBean = memberFriendCenterService.findMemberFriendHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/companyfriendlist");
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
				MemberFriend mfriend = (MemberFriend) pagelist.get(i);
				mfriend.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mfriend.getMid()));
				mfriend.setRmemberInfo(memberInfoCenterService
						.getMemberInfo(mfriend.getFid()));
				newlist.add(mfriend);
			}
		}
		return newlist;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
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

	public MemberInfoCenterService getMemberInfoCenterService() {
		return memberInfoCenterService;
	}

	public void setMemberInfoCenterService(
			MemberInfoCenterService memberInfoCenterService) {
		this.memberInfoCenterService = memberInfoCenterService;
	}

	public String getSearchcontent() {
		return searchcontent;
	}

	public void setSearchcontent(String searchcontent) {
		this.searchcontent = searchcontent;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

}
