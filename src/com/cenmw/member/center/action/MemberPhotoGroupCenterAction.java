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
import com.cenmw.member.center.service.MemberPhotoCenterService;
import com.cenmw.member.center.service.MemberPhotoGroupCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberPhotoGroup;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberPhotoGroupCenterAction extends BaseAction {
	/**
	 * 会员相册 模块
	 */
	private MemberPhotoGroupCenterService memberPhotoGroupCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberPhotoCenterService memberPhotoCenterService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberPhotoGroup memberPhotoGroup;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;
	private String msg = "";

	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员相册删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberPhotoGroup = memberPhotoGroupCenterService
						.getMemberPhotoGroup(new Integer(idarrs[i].trim()));
				memberPhotoGroup.setIsdel(new Integer(1));
				memberPhotoGroupCenterService
						.updateMemberPhotoGroup(memberPhotoGroup);
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
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		msg = "修改成功！";
		if (memberPhotoGroup.getId() == null) {
			msg = "添加成功！";
			memberPhotoGroup.setMid(memberInfo.getId());
			memberPhotoGroup.setIsdel(new Integer(0));
			memberPhotoGroup.setCtime(new Date());
			memberPhotoGroup.setSort(new Integer(0));
			memberPhotoGroupCenterService
					.saveMemberPhotoGroup(memberPhotoGroup);
		}
		memberPhotoGroupCenterService.updateMemberPhotoGroup(memberPhotoGroup);
		session.put("classmsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员相册查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberPhotoGroup = memberPhotoGroupCenterService
					.getMemberPhotoGroup(id);
			memberPhotoGroup.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberPhotoGroup.getMid()));
			String msg = (String) session.get("classmsg");
			if (msg != null && (msg.equals("添加成功！") || msg.equals("修改成功！"))) {
				classlist = memberPhotoGroupCenterService
						.findMemberPhotoGroupInList(memberInfo.getId());
				session.remove("classmsg");
			}
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
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberPhotoGroup where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id asc ";

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
		pageSize = 12;
		// 判断排序条件
		pageBean = memberPhotoGroupCenterService.findMemberPhotoGroupHQLList(
				hql, sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/photogrouplist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 好友相册列表查看功能
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId());
		if (fids.length() > 0) {
			String hql = " from MemberPhotoGroup where isdel=0 and mid in ("
					+ fids + ")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = "";
			sortstr = " id asc ";

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
			pageSize = 12;
			// 判断排序条件
			pageBean = memberPhotoGroupCenterService
					.findMemberPhotoGroupHQLList(hql, sortstr, map,
							currentPage, pageSize);
			pageBean.setAction("/membercenter/fphotogrouplist");
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
				MemberPhotoGroup mpg = (MemberPhotoGroup) pagelist.get(i);
				mpg.setMemberInfo(memberInfoCenterService.getMemberInfo(mpg
						.getMid()));
				mpg.setPicpath(memberPhotoCenterService.findMemberPhoto(mpg
						.getId()));
				mpg.setPcount(memberPhotoCenterService.findMemberPhotoCount(mpg
						.getId()));
				newlist.add(mpg);
			}
		}
		return newlist;
	}

	public MemberPhotoGroupCenterService getMemberPhotoGroupCenterService() {
		return memberPhotoGroupCenterService;
	}

	public void setMemberPhotoGroupCenterService(
			MemberPhotoGroupCenterService memberPhotoGroupCenterService) {
		this.memberPhotoGroupCenterService = memberPhotoGroupCenterService;
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

	public List getClasslist() {
		return classlist;
	}

	public void setClasslist(List classlist) {
		this.classlist = classlist;
	}

	public MemberPhotoCenterService getMemberPhotoCenterService() {
		return memberPhotoCenterService;
	}

	public void setMemberPhotoCenterService(
			MemberPhotoCenterService memberPhotoCenterService) {
		this.memberPhotoCenterService = memberPhotoCenterService;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

}
