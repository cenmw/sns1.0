package com.cenmw.member.front.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.front.service.MemberMessageFrontService;
import com.cenmw.member.po.MemberMessage;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberMessageFrontAction extends BaseAction {
	/**
	 * 会员收藏 模块
	 */
	private MemberMessageFrontService memberMessageFrontService;
	private MemberInfoFrontService memberInfoFrontService;
	private MemberMessage memberMessage;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchcontent = "";

	/**
	 * 会员收藏删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberMessage = memberMessageFrontService
						.getMemberMessage(new Integer(idarrs[i].trim()));
				memberMessage.setIsdel(new Integer(1));
				memberMessageFrontService.updateMemberMessage(memberMessage);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员收藏添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (memberMessage.getId() == null) {
			msg = "添加成功！";
			memberMessage.setIsdel(new Integer(0));
			memberMessage.setCtime(new Date());
			memberMessageFrontService.saveMemberMessage(memberMessage);
		}
		memberMessageFrontService.updateMemberMessage(memberMessage);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员收藏查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberMessage = memberMessageFrontService.getMemberMessage(id);
			memberMessage.setMemberInfo(memberInfoFrontService
					.getMemberInfoById(memberMessage.getMid()));
			memberMessage.setRmemberInfo(memberInfoFrontService
					.getMemberInfoById(memberMessage.getReviceid()));
		}
		// 初始化信息
		if (memberMessage == null) {
			memberMessage = new MemberMessage();
			memberMessage.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberMessage where isdel=0";
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
		pageBean = memberMessageFrontService.findMemberMessageHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/front/membermessage/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/front/gopage/gopage.html"));
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
				MemberMessage mmessage = (MemberMessage) pagelist.get(i);
				mmessage.setMemberInfo(memberInfoFrontService
						.getMemberInfoById(mmessage.getMid()));
				mmessage.setRmemberInfo(memberInfoFrontService
						.getMemberInfoById(mmessage.getReviceid()));
				newlist.add(mmessage);
			}
		}
		return newlist;
	}

	public MemberMessageFrontService getMemberMessageFrontService() {
		return memberMessageFrontService;
	}

	public void setMemberMessageFrontService(
			MemberMessageFrontService memberMessageFrontService) {
		this.memberMessageFrontService = memberMessageFrontService;
	}

	public MemberMessage getMemberMessage() {
		return memberMessage;
	}

	public void setMemberMessage(MemberMessage memberMessage) {
		this.memberMessage = memberMessage;
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

	public MemberInfoFrontService getMemberInfoByIdFrontService() {
		return memberInfoFrontService;
	}

	public void setMemberInfoFrontService(
			MemberInfoFrontService memberInfoFrontService) {
		this.memberInfoFrontService = memberInfoFrontService;
	}

	public String getSearchcontent() {
		return searchcontent;
	}

	public void setSearchcontent(String searchcontent) {
		this.searchcontent = searchcontent;
	}

}
